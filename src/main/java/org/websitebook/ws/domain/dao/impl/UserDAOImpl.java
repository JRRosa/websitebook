package org.websitebook.ws.domain.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.domain.dao.UserDAO;
import org.websitebook.ws.domain.dao.constants.ConnectionSql;
import org.websitebook.ws.domain.dao.entities.User;
import org.websitebook.ws.domain.dao.exceptions.DBException;

public class UserDAOImpl implements UserDAO {

	private static final String GET_DRIVER = "org.postgresql.Driver";
	private static final String GET_USER_BY_ID = " SELECT id, email, password, first_name, last_name, gender_id, user_type_id FROM dbo.user WHERE id = ?";
	private static final String GET_ALL_USER = " SELECT id, email, password, first_name, last_name, gender_id, user_type_id FROM dbo.user ";
	private static final String DELETE_USER_BY_ID = " DELETE FROM dbo.user WHERE id = ? ";
	
	private static UserDAOImpl instance;
	private User user = null;
	private List<User> data = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
		
	private UserDAOImpl() {}
	
	public static UserDAOImpl getInstance() {
		if(instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	public User getById(Long id) {
		
		try {
			Class.forName(GET_DRIVER);
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(GET_USER_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
			
				user = new User();
				user.setId(rs.getLong("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(rs.getInt("gender_id"));
				user.setUserTypeId(rs.getInt("user_type_id"));
				data.add(user);
			
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 

		return user;
	}

	public List<User> getAll() {

		data = new ArrayList<>();
		
		try {
			Class.forName(GET_DRIVER);
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(GET_ALL_USER);
			rs = st.executeQuery();

			while (rs.next()) {
			
				user = new User();
				user.setId(rs.getLong("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(rs.getInt("gender_id"));
				user.setUserTypeId(rs.getInt("user_type_id"));
				data.add(user);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
		
		return new ArrayList<User>(data);
	}

	@Override
	public void delete(User t) throws DBException {
		
		try {
			Class.forName(GET_DRIVER);
						
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(DELETE_USER_BY_ID);
			st.setLong(1, t.getId());
			rs = st.executeQuery();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 

	}

	@Override
	public User create(User t) throws DBException {

		return null;
	}

	@Override
	public void update(User t) throws DBException {
		

	}
	

}

