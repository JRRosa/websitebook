package org.websitebook.ws.domain.dao;

import java.util.List;

import org.websitebook.ws.domain.dao.entities.User;

public interface UserDAO extends Dao<User, Integer> {
		
	List<User> findAll();
	
}