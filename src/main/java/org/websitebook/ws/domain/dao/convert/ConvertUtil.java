package org.websitebook.ws.domain.dao.convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConvertUtil {
	public static <T> T convert(ResultSet resultSet, Class<T> clazz) {
		T entity = null;
		try {
			entity = clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				Column column = field.getAnnotation(Column.class); 
				if(column != null) {
					field.setAccessible(true);
					String name = column.name();
					if(Long.class.getSimpleName().equals(field.getType().getSimpleName())) {
						Long value = resultSet.getLong(name);
						field.set(entity, value);
					}

					if(String.class.getSimpleName().equals(field.getType().getSimpleName())) {
						String value = resultSet.getString(name);
						field.set(entity, value);
					}

					if(Integer.class.getSimpleName().equals(field.getType().getSimpleName())) {
						Integer value = resultSet.getInt(name);
						field.set(entity, value);
					}

				}
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	} 

}
