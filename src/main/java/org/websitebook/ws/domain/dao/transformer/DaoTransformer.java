package org.websitebook.ws.domain.dao.transformer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.domain.dao.exceptions.FailConvertionException;

public class DaoTransformer {

	private static <T> Boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotation) {
		return field.getAnnotation(annotation) != null;
	}

	private static <T> Field[] getFieldsAnnotatedWith(Field[] fields, Class<? extends Annotation> annotation) {
		List<Field> fieldsList = new ArrayList();
		for (Field field : fields) {
			if (field.getAnnotation(annotation) != null) {
				fieldsList.add(field);
			}
		}
		return fieldsList.toArray(new Field[fieldsList.size()]);
	}

	private static <T> void setPreparedStatementFieldValue(Field field, PreparedStatement preparedStatement, int index,
			T entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, SQLException {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("get");
		stringBuilder.append(field.getName().substring(0, 1).toUpperCase());
		stringBuilder.append(field.getName().substring(1));
		preparedStatement.setObject(index, entity.getClass().getMethod(stringBuilder.toString()).invoke(entity));
	}

	private static <T> String getIdFieldName(T entity) {

		for (Field field : getFieldsAnnotatedWith(entity.getClass().getDeclaredFields(), Id.class)) {
			return field.getName();
		}

		return null;
	}

	public static <T> T convertToEntity(ResultSet resultSet, Class<T> clazz) throws FailConvertionException {
		try {
			T entity = clazz.newInstance();
			for (Field field : getFieldsAnnotatedWith(clazz.getDeclaredFields(), Column.class)) {
				field.setAccessible(true);
				field.set(entity, resultSet.getObject(field.getAnnotation(Column.class).name()));
			}
			return entity;
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			throw new FailConvertionException(e);
		}
	}

	public static <T> String buildCreateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			query.append("INSERT INTO ");
			query.append(table.name());
			query.append(" (");
			Field[] fields = getFieldsAnnotatedWith(clazz.getDeclaredFields(), Column.class);
			for (Field field : fields) {
				if (!isAnnotationPresent(field, Id.class)) {
					query.append(field.getAnnotation(Column.class).name());
					query.append(", ");
				}
			}
			query.delete(query.length() - 2, query.length());
			query.append(") VALUES (");
			for (Field field : fields) {
				if (!isAnnotationPresent(field, Id.class)) {
					query.append("?, ");
				}
			}
			query.delete(query.length() - 2, query.length());
			query.append(")");

		}

		return query.toString();
	}

	public static <T> void setPreparedStatementCreateQuery(PreparedStatement preparedStatement, T entity)
			throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, NoSuchFieldException {

		int index = 1;
		for (Field field : getFieldsAnnotatedWith(entity.getClass().getDeclaredFields(), Column.class)) {
			if (!isAnnotationPresent(field, Id.class)) {
				setPreparedStatementFieldValue(field, preparedStatement, index, entity);
				index++;
			}
		}
	}

	public static <T, K> void setEntityIdValue(T entity, K id)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		String fieldName = getIdFieldName(entity);
		Field field = entity.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(entity, id);

	}

	public static <T> String buildFindAllQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			query.append("SELECT * FROM ");
			query.append(table.name());
		}
		return query.toString();
	}

	public static <T> String buildFindByIdQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			query.append("SELECT * FROM ");
			query.append(table.name());
			query.append(" WHERE ");
			query.append(" id = ?");
		}

		return query.toString();
	}

	public static <T, K> void setPreparedStatementIdQuery(PreparedStatement preparedStatement, K id)
			throws SQLException {
		int index = 1;
		preparedStatement.setObject(index, id);
	}

	public static <T> String buildUpdateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			query.append("UPDATE ");
			query.append(table.name());
			query.append(" SET ");
			Field[] fields = getFieldsAnnotatedWith(clazz.getDeclaredFields(), Column.class);
			for (Field field : fields) {
				if (!isAnnotationPresent(field, Id.class)) {
					query.append(field.getAnnotation(Column.class).name());
					query.append(" = ?, ");
				}
			}
			query.delete(query.length() - 2, query.length());
			query.append(" WHERE ");
			query.append(" id = ?");

		}

		return query.toString();
	}

	public static <T> void setPreparedStatementUpdateQuery(PreparedStatement preparedStatement, T entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, SQLException, NoSuchFieldException {

		int index = 1;
		Field[] fields = getFieldsAnnotatedWith(entity.getClass().getDeclaredFields(), Column.class);
		for (Field field : fields) {
			if (!isAnnotationPresent(field, Id.class)) {
				setPreparedStatementFieldValue(field, preparedStatement, index, entity);
				index++;
			}
		}
		for (Field field : getFieldsAnnotatedWith(fields, Id.class)) {
			setPreparedStatementFieldValue(field, preparedStatement, index, entity);
			index++;
		}

	}

	public static <T> String buildDeleteQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			query.append("DELETE FROM ");
			query.append(table.name());
			query.append(" WHERE ");
			query.append(" id = ?");
		}

		return query.toString();
	}

}