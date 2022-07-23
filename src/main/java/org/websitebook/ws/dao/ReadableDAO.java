package org.websitebook.ws.dao;

import java.sql.SQLException;
import java.util.List;

public interface ReadableDAO<T> {
	
	T getById(int id) throws SQLException;
	
	List<T> getAll() throws SQLException;

}