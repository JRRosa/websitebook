package org.websitebook.ws.dao;

public interface DAO<T> extends CreatableDAO<T>, ReadableDAO<T>, UpdatableDAO<T>, DeletableDAO<T>{
	
}
