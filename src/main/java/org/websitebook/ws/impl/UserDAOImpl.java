package org.websitebook.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.dao.UserDAO;
import org.websitebook.ws.entities.User;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;
	private User user = null;
	private List<User> data = null;
		
	private UserDAOImpl() {}
	
	public static UserDAOImpl getInstance() {
		if(instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	@Override
	public User getById(Long id) throws SQLException {
		user = new User(Long.parseLong("1"),"jo@gmail.com","12345","Jorge","Reyes", 1, 1);
		return user;
	}

	@Override
	public List<User> getAll() throws SQLException {
		data = new ArrayList<>();
		
		data.add(new User(Long.parseLong("1"),"jo@gmail.com","12345","Jorge","Reyes", 1, 1));
		data.add(new User(Long.parseLong("2"),"jo@gmail.com","2345","Jairo","Reyes", 2, 2));
		data.add(new User(Long.parseLong("3"),"jo@gmail.com","2345","Jairo","Reyes", 2, 2));
				
		return new ArrayList<User>(data);
	}
	

}

