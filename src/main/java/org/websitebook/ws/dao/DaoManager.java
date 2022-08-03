package org.websitebook.ws.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.websitebook.ws.constants.ConnectionSql;

public class DaoManager {

	private static DaoManager instance;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	private DaoManager() {}
	
	public static DaoManager getInstance() {
		if(instance == null) {
			instance = new DaoManager();
		}
		return instance;
	}
		

	
}
