package org.websitebook.ws.domain.dao;

import java.util.List;

import org.websitebook.ws.domain.dao.entities.User;

public interface UserDao extends Dao<User, Long> {
	
	List<User> findAll();
	User findById(Long id);
	
}