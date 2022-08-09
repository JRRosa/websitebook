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
import org.websitebook.ws.domain.dao.transformer.ConvertUtil;

public abstract class AbstractDao<T, K> {
    
    protected abstract Class<T> getTypeClass();
    // private abstract String getCreateQuery();
    // protected abstract String getFindByIdQuery();
    protected abstract String getFindAllQuery();
    // protected abstract String getUpdateQuery();
    // protected abstract String getDeleteQuery();
    
    private Connection getConnection() throws DBException, ClassNotFoundException, SQLException {
        Class.forName(ConnectionSql.GET_DRIVER);
        return DriverManager.getConnection(ConnectionSql.URL, ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
    }

    public T create(T entity) {
        try(Connection connection = getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(ConvertUtil.buildCreateQuery(getTypeClass()), PreparedStatement.RETURN_GENERATED_KEYS)){
                preparedStatement = ConvertUtil.buildCreatePreparedStatement(preparedStatement, getTypeClass());
                preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        K id = (K) resultSet.getObject(1);
                        entity.setId(id);
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
                        entities.add(ConvertUtil.convertToEntity(resultSet, getTypeClass()));
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
            try(PreparedStatement preparedStatement = connection.prepareStatement(ConvertUtil.buildFindByIdQuery(getTypeClass()))) {
                preparedStatement.setLong(1, (Long) id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        entity = ConvertUtil.convertToEntity(resultSet, getTypeClass());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /*public T update(T entity) {
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
    } */

}
