package org.websitebook.ws.dao;

import java.sql.SQLException;

public interface DeletableDAO<T> {

	boolean delete(T t) throws SQLException;
}
