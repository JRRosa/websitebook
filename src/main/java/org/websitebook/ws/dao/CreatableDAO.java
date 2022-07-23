package org.websitebook.ws.dao;

import java.sql.SQLException;

public interface CreatableDAO<T> {

	int create(T t) throws SQLException;
}
