package org.websitebook.ws.domain.dao;

import java.sql.SQLException;

public interface UpdatableDAO<T> {
	
	int update(T t) throws SQLException;
	
}
