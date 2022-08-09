package org.websitebook.ws.domain.dao;

import org.websitebook.ws.domain.dao.exceptions.DBException;

public interface Dao<T, K> {
	
	T create(T entity) throws DBException;
	void update(T entity) throws DBException;
	void delete(T entity) throws DBException;
	
	
}
