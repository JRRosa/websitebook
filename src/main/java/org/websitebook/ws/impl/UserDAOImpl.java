package org.websitebook.ws.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.websitebook.ws.constants.ConnectionSql;
import org.websitebook.ws.dao.UserDAO;
import org.websitebook.ws.entities.User;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;
	private User user = null;
	private List<User> data = null;
	private StringBuilder sqlQuery = null;
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

	@Override
	public User getById(Long id) throws SQLException {

		sqlQuery = new StringBuilder();
		sqlQuery.append(" SELECT id, email, password, first_name, last_name, gender_id, user_type_id FROM dbo.user WHERE id = ?");
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(sqlQuery.toString());
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

	@Override
	public List<User> getAll() throws SQLException {

		data = new ArrayList<>();
		sqlQuery = new StringBuilder();
		sqlQuery.append(" SELECT id, email, password, first_name, last_name, gender_id, user_type_id FROM dbo.user");
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(sqlQuery.toString());
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
		
		return new ArrayList<User>(data);
	}

	@Override
	public boolean delete(User t) throws SQLException {

		sqlQuery = new StringBuilder();
		sqlQuery.append(" DELETE FROM dbo.user WHERE id = ?");
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(sqlQuery.toString());
			st.setLong(1, t.getId());
			rs = st.executeQuery();
			return true;

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

		return false;
	}
	

}

