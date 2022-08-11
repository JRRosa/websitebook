package org.websitebook.ws.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.domain.dao.constants.ConnectionSql;
import org.websitebook.ws.domain.dao.exceptions.DBException;
import org.websitebook.ws.domain.dao.exceptions.FailConvertionException;
import org.websitebook.ws.domain.dao.transformer.DaoTransformer;

public abstract class AbstractDao<T, K> {
    
    protected abstract Class<T> getTypeClass();
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(ConnectionSql.GET_DRIVER);
        return DriverManager.getConnection(ConnectionSql.URL, ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
    }

    public T create(T entity) throws DBException {
        try(Connection connection = getConnection()){
            String createQuery = DaoTransformer.buildCreateQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS)){
                DaoTransformer.setPreparedStatementCreateQuery(preparedStatement, entity);
                preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        DaoTransformer.setEntityIdValue(entity, resultSet.getObject(1));
                    }
                }
            }
        } catch (FailConvertionException | ClassNotFoundException | SQLException e) {
        	throw new DBException(e);
        }
        return entity;
    }

    public List<T> findAll() throws DBException {
        List<T> entities = null;
        try(Connection connection = getConnection()){
            String findAllQuery = DaoTransformer.buildFindAllQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    entities = new ArrayList<>();
                    while (resultSet.next()) {
                        entities.add(DaoTransformer.convertToEntity(resultSet, getTypeClass()));
                    }
                }
            }
            
        } catch (FailConvertionException | SQLException | ClassNotFoundException e) {
        	throw new DBException(e);
        }

        return entities;
    }

    public T findById(K id) throws DBException {
        T entity = null;
        try(Connection connection = getConnection()){
            String findByQuery = DaoTransformer.buildFindByIdQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(findByQuery)){
                DaoTransformer.setPreparedStatementIdQuery(preparedStatement, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        entity = DaoTransformer.convertToEntity(resultSet, getTypeClass());
                    }
                }
            }
        } catch (FailConvertionException | SQLException | ClassNotFoundException e) {
        	throw new DBException(e);
        }
        return entity;
    }

    public void update(T entity) throws DBException {
        try(Connection connection = getConnection()){
            String updateQuery = DaoTransformer.buildUpdateQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                DaoTransformer.setPreparedStatementUpdateQuery(preparedStatement, entity);
                preparedStatement.executeUpdate();
            }
        } catch (IllegalArgumentException | IllegalAccessException | SQLException | ClassNotFoundException e) {
        	throw new DBException(e);
        }
    }

    public void delete(K id) throws DBException {
        try(Connection connection = getConnection()){
        	String deleteQuery = DaoTransformer.buildDeleteQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                DaoTransformer.setPreparedStatementIdQuery(preparedStatement, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
        	throw new DBException(e);
        }
    }

}
