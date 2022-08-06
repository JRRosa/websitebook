package org.websitebook.ws.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.websitebook.ws.domain.dao.constants.ConnectionSql;
import org.websitebook.ws.domain.dao.exceptions.DBException;

public abstract class AbstractDao<T, K> {

    private Connection connection;
    private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

    public T create(T entity, String sqlQuery) throws DBException {
        try {
        	preparedStatement = getQueryConfigurate(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return getEntity(resultSet);
            }
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);

        }
        return null;
    }

    public T update(T entity, String sqlQuery) throws DBException {
        try {
        	preparedStatement = getQueryConfigurate(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return getEntity(resultSet);
            }
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);

        }
        return null;
    }

    public void delete(K id, String sqlQuery) throws DBException {
        try {
        	return findById(id, sqlQuery);
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
    }

    public T findById(K id, String sqlQuery) throws DBException {
        try {

            preparedStatement = getQueryConfigurate(sqlQuery);
            preparedStatement.setLong(1, (Long) id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
        return resultSet;
    }
    
    public List<T> findAll(String sqlQuery) throws DBException {
        try {
            
            preparedStatement = getQueryConfigurate(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return getEntity(resultSet);
            }
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
        return null;
    }
       
    private PreparedStatement getQueryConfigurate(String sqlQuery) throws DBException | ClassNotFoundException {
        Class.forName(ConnectionSql.GET_DRIVER);
        connection = DriverManager.getConnection(ConnectionSql.URL, ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
        preparedStatement = connection.prepareStatement(sqlQuery);
        return preparedStatement;
    }

    protected abstract T getEntity(ResultSet resultSet) throws DBException;

}
