package org.websitebook.ws.domain.dao.transformer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.websitebook.ws.domain.dao.exceptions.FailConvertionException;

public class DaoTransformer {

	private static <T> List<Field> getFieldsAnnotated(Class<T> clazz, Class<? extends Annotation>  annotation) {
		return Arrays.asList(clazz.getDeclaredFields())
				.stream()
				.filter(field -> field.isAnnotationPresent(annotation))
				.collect(Collectors.toList());
	}
	
	private static <T> List<Field> getFieldColumnWithoutId(Class<T> clazz) {
		return getFieldsAnnotated(clazz, Column.class)
				.stream()
				.filter(field -> !field.isAnnotationPresent(Id.class))
				.collect(Collectors.toList());
	}

	private static <T> Field getFieldWithId(T entity) {
		return getFieldsAnnotated(entity.getClass(), Id.class).stream().findFirst().get();
	}
	
	private static void appendColumnToQuery(StringBuilder query, Field field, String columnPattern) {
		query.append(getColumnName(field));
		query.append(columnPattern);
	}
	
	private static void cleanQueryAppend(StringBuilder query) {
		query.delete(query.length() - 2, query.length());
	}
	
	private static String getColumnName(Field field) {
		return field.getAnnotation(Column.class).name();
	}

	public static <T> T convertToEntity(ResultSet resultSet, Class<T> clazz) throws FailConvertionException, SQLException {
		try {
			T entity = clazz.newInstance();
			for (Field field: getFieldsAnnotated(clazz, Column.class)) {
				field.setAccessible(true);
				field.set(entity, resultSet.getObject(getColumnName(field)));	
			}
			return entity;
		} catch (IllegalAccessException | InstantiationException e) {
			throw new FailConvertionException(e);
		}
	}

	public static <T> String buildCreateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			query.append("INSERT INTO ");
			query.append(table.name());
			query.append(" (");
			
			StringBuilder parameters = new StringBuilder();
			for (Field field: getFieldColumnWithoutId(clazz)) {
				appendColumnToQuery(query, field, ", ");
				parameters.append("?, ");
			}
			
			cleanQueryAppend(query);
			cleanQueryAppend(parameters);
			query.append(parameters.toString());
			query.append(")");
		}

		return query.toString();
	}

	public static <T> void setPreparedStatementCreateQuery(PreparedStatement preparedStatement, T entity) throws FailConvertionException, SQLException {

		try {
			int index = 0;
			for (Field field : getFieldColumnWithoutId(entity.getClass())) {
				preparedStatement.setObject(index++, field.get(entity));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new FailConvertionException(e);
		}
		
		
	}

	public static <T, K> void setEntityIdValue(T entity, K id) throws FailConvertionException {
		try {
			Field field = getFieldWithId(entity);
			field.setAccessible(true);
			field.set(entity, id);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new FailConvertionException(e);
		}
	}

	public static <T> String buildFindAllQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			query.append("SELECT * FROM ");
			query.append(table.name());
		}
		return query.toString();
	}

	public static <T> String buildFindByIdQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			query.append("SELECT * FROM ");
			query.append(table.name());
			query.append(" WHERE ");
			query.append(" id = ?");
		}

		return query.toString();
	}

	public static <T, K> void setPreparedStatementIdQuery(PreparedStatement preparedStatement, K id) throws SQLException {
		int index = 1;
		preparedStatement.setObject(index, id);
	}

	public static <T> String buildUpdateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			query.append("UPDATE ");
			query.append(table.name());
			query.append(" SET ");
			
			for (Field field: getFieldColumnWithoutId(clazz)) {
				appendColumnToQuery(query, field, " = ?, ");
			}
			cleanQueryAppend(query);
			query.append(" WHERE id = ?");
		}

		return query.toString();
	}

	public static <T> void setPreparedStatementUpdateQuery(PreparedStatement preparedStatement, T entity) 
			throws IllegalArgumentException, IllegalAccessException, SQLException {
		
		int index = 0;
		Field columnId = null;
		for (Field field : getFieldsAnnotated(entity.getClass(), Column.class)) {
			if (field.isAnnotationPresent(Id.class)) {
				columnId = field;
			} else {
				preparedStatement.setObject(index++, field.get(entity));
			}
		}
		
		if (columnId != null) {
			preparedStatement.setObject(index++, columnId.get(entity));
		}
	}

	public static <T> String buildDeleteQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			query.append("DELETE FROM ");
			query.append(table.name());
			query.append(" WHERE id = ?");
		}

		return query.toString();
	}

}