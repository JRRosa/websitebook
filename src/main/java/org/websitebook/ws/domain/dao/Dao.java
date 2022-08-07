package org.websitebook.ws.domain.dao;

import java.sql.SQLException;
import java.util.List;

import org.websitebook.ws.domain.dao.entities.User;
import org.websitebook.ws.domain.dao.exceptions.DBException;

public interface Dao<T, K> {
	
	T create(T entity) throws DBException;
	void update(T entity) throws DBException;
	void delete(T entity) throws DBException;
	
	
}
