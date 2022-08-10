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
import org.websitebook.ws.domain.dao.transformer.DaoTransformer;

public abstract class AbstractDao<T, K> {
    
    protected abstract Class<T> getTypeClass();
    
    private Connection getConnection() throws DBException, ClassNotFoundException, SQLException {
        Class.forName(ConnectionSql.GET_DRIVER);
        return DriverManager.getConnection(ConnectionSql.URL, ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
    }

    public T create(T entity) {
        try(Connection connection = getConnection()){
            String sqlString = DaoTransformer.buildCreateQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS)){
                DaoTransformer.setPreparedStatementCreateQuery(preparedStatement, entity);
                preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                    	int indexKey = 1; 
                        K id = (K) resultSet.getObject(indexKey);
                        entity.getClass().getMethod("setId", id.getClass()).invoke(entity, id);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<T> findAll() {
        List<T> entities = null;
        try(Connection connection = getConnection()){
            String sqlString = DaoTransformer.buildFindAllQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlString)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    entities = new ArrayList<>();
                    while (resultSet.next()) {
                        entities.add(DaoTransformer.convertToEntity(resultSet, getTypeClass()));
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
    }

    public T findById(K id) {
        T entity = null;
        try(Connection connection = getConnection()){
            String sqlString = DaoTransformer.buildFindByIdQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlString)){
                preparedStatement.setLong(1, (Long) id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        entity = DaoTransformer.convertToEntity(resultSet, getTypeClass());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void update(T entity) {
        try(Connection connection = getConnection()){
            String sqlString = DaoTransformer.buildUpdateQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlString)){
                DaoTransformer.setPreparedStatementUpdateQuery(preparedStatement, entity);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(K id) {
        try(Connection connection = getConnection()){
        	String sqlString = DaoTransformer.buildDeleteQuery(getTypeClass());
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlString)){
                preparedStatement.setLong(1, (Long) id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
