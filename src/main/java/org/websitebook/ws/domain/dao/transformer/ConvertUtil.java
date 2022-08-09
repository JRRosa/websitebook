package org.websitebook.ws.domain.dao.transformer;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.websitebook.ws.domain.dao.exceptions.FailConvertionException;

public class ConvertUtil {

	public static <T> T convertToEntity(ResultSet resultSet, Class<T> clazz) throws FailConvertionException {
		try {
			T entity = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					field.setAccessible(true);
					field.set(entity, resultSet.getObject(column.name()));
				}
			}
			return entity;
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			throw new FailConvertionException(e);
		}
	}

	public static <T> String buildCreateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		query.append("INSERT INTO ");
		query.append(table.name());
		query.append(" (");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if (column != null && id == null) {
				query.append(column.name());
				query.append(", ");
			}
		}
		query.delete(query.length() - 2, query.length());
		query.append(") VALUES (");
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if (column != null && id == null) {
				query.append("?, ");
			}
		}
		query.delete(query.length() - 2, query.length());
		query.append(")");
		return query.toString();
	}

	public static <T> PreparedStatement buildCreatePreparedStatement(PreparedStatement preparedStatement, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
		T entity = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		int index = 1;
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if (column != null && id == null) {
				field.setAccessible(true);
				preparedStatement.setObject(index, field.get(entity));
				index++;
			}
		}
		return preparedStatement;
    }

	public static <T> String buildUpdateQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		query.append("UPDATE ");
		query.append(table.name());
		query.append(" SET ");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if (column != null && id == null) {
				query.append(column.name());
				query.append(" = ?, ");
			}
		}
		query.delete(query.length() - 2, query.length());
		query.append(" WHERE ");
		Id id = clazz.getAnnotation(Id.class);
		query.append(id.name());
		query.append(" = ?");
		return query.toString();
	}

	public static <T> PreparedStatement buildUpdatePreparedStatement(PreparedStatement preparedStatement, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
		T entity = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		int index = 1;
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if (column != null && id == null) {
				field.setAccessible(true);
				preparedStatement.setObject(index, field.get(entity));
				index++;
			}
		}
		for(Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				field.setAccessible(true);
				preparedStatement.setObject(index, field.get(entity));
				index++;
			}
		}
		
		return preparedStatement;
	}

	public static <T> String buildDeleteQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		query.append("DELETE FROM ");
		query.append(table.name());
		query.append(" WHERE ");
		Id id = clazz.getAnnotation(Id.class);
		query.append(id.name());
		query.append(" = ?");
		return query.toString();
	}

	public static <T> PreparedStatement buildDeletePreparedStatement(PreparedStatement preparedStatement, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
		T entity = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		int index = 1;
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				field.setAccessible(true);
				preparedStatement.setObject(index, field.get(entity));
				index++;
			}
		}
		
		return preparedStatement;
	}

	public static <T> String buildFindAll(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		Table table = clazz.getAnnotation(Table.class);
		query.append(table.name());
		return query.toString();
	}

	public static <T> String buildFindByIdQuery(Class<T> clazz) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		Table table = clazz.getAnnotation(Table.class);
		query.append(table.name());
		query.append(" WHERE ");
		Id id = clazz.getAnnotation(Id.class);
		query.append(id.name());
		query.append(" = ?");
		return query.toString();
	}

}
