package org.websitebook.ws.domain.dao;

import java.util.List;

import org.websitebook.ws.domain.dao.entities.User;
import org.websitebook.ws.domain.dao.exceptions.DBException;

public interface UserDao extends Dao<User, Long> {
	
	List<User> findAll() throws DBException;
	User findById(Long id) throws DBException;
	
}