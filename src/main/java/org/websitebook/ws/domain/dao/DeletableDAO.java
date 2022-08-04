package org.websitebook.ws.domain.dao;

import java.sql.SQLException;

public interface DeletableDAO<T> {

	boolean delete(T t) throws SQLException;
}
