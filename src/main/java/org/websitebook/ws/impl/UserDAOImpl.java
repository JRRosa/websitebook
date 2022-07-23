package org.websitebook.ws.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.websitebook.ws.dao.DaoManager;
import org.websitebook.ws.dao.UserDAO;
import org.websitebook.ws.entities.User;

public class UserDAOImpl implements UserDAO {

	private StringBuilder sb = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private User user = null;
	
	@Override
	public User getById(int id) throws SQLException {
		
//		sb = new StringBuilder();
//		sb.append("SELECT id, email, password, first_name, last_name, gender_id, user_type_id FROM dbo.user WHERE id = ?");
//		
//		ps = DaoManager.getStatement(sb.toString());
//		
//		ps.setInt(1, id);
//		
//		rs = ps.executeQuery();
//		
//		if (rs.next()) {
//			
//			user = new User();
//			user.setId(rs.getLong("id"));
//			user.setEmail(rs.getString("email"));
//			user.setPassword(rs.getString("password"));
//			user.setFirstName(rs.getString("first_name"));
//			user.setLastName(rs.getString("last_name"));
//			user.setGender(rs.getInt("gender_id"));
//			user.setUserTypeId(rs.getInt("user_type_id"));
//		}
//		
//		DaoManager.connectionClose(rs);
		
		user = new User();
		user.setId(1);
		user.setEmail("jo@gmail.com");
		user.setPassword("12345");
		user.setFirstName("Jorge");
		user.setLastName("Reyes");
		user.setGender(1);
		user.setUserTypeId(1);
		
		
		return user;
	}

	@Override
	public List<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
