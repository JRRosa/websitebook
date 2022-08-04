package org.websitebook.ws.domain.dao;

import java.sql.SQLException;

public interface CreatableDAO<T> {

	int create(T t) throws SQLException;
}
