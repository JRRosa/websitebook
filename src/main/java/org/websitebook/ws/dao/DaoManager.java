package org.websitebook.ws.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoManager {

	private DaoManager() {}
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	
	private static String getConnectionString() {
		return "";
	}
	
	private static Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection(DaoManager.getConnectionString());
		return connection;
	}
	
	public static PreparedStatement getStatement(String sqlQuery) throws SQLException  {
		statement = DaoManager.getConnection().prepareStatement(sqlQuery);
		return statement;
	}
	
	public static void connectionClose(ResultSet restulSet) throws SQLException {
		restulSet.close();
		statement.close();
		connection.close();
	}
}
