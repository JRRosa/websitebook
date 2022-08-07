package org.websitebook.ws.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.domain.dao.constants.ConnectionSql;
import org.websitebook.ws.domain.dao.convert.ConvertUtil;
import org.websitebook.ws.domain.dao.exceptions.DBException;

public abstract class AbstractDao<T, K> {
    
    protected abstract Class<T> getTypeClass();
    protected abstract String getCreateQuery();
    protected abstract String getFindByIdQuery();
    protected abstract String getFindAllQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    
    private Connection getConnection() throws DBException, ClassNotFoundException, SQLException {
        Class.forName(ConnectionSql.GET_DRIVER);
        return DriverManager.getConnection(ConnectionSql.URL, ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
    }

    public T create(T entity) {
        try(Connection connection = getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery())){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        entity = ConvertUtil.convert(resultSet, getTypeClass());
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
            try(PreparedStatement preparedStatement = connection.prepareStatement(getFindAllQuery())){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    entities = new ArrayList<>();
                    while (resultSet.next()) {
                        entities.add(ConvertUtil.convert(resultSet, getTypeClass()));
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
            try(PreparedStatement preparedStatement = connection.prepareStatement(getFindByIdQuery())){
                preparedStatement.setLong(1, (Long) id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        entity = ConvertUtil.convert(resultSet, getTypeClass());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public T update(T entity) {
        try(Connection connection = getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())){
                preparedStatement.setObject(1, entity);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void delete(K id) {
        try(Connection connection = getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())){
                preparedStatement.setLong(1, (Long) id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
