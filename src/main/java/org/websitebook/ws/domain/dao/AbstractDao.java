package org.websitebook.ws.domain.dao;

import java.util.List;

import org.websitebook.ws.domain.dao.exceptions.DBException;

public abstract class AbstractDao<T, K> {

    protected abstract Class<T> getEntityClass();

    public T create(T entity) throws DBException {
        try {
            
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);

        }
        return null;
    }

    public T update(T entity) throws DBException {
        try {
            
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);

        }
        return null;
    }

    public void delete(K id) throws DBException {
        try {
            
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
    }

    public T findById(K id) throws DBException {
        try {
            
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
        return null;
    }
    
    public List<T> findAll() throws DBException {
        try {
            
        } catch (Exception e) {
        	throw new DBException(e.getMessage(), e);
        }
        return null;
    }
       
}
