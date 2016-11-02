package com.mnr.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static final String sqliteUrl = "jdbc:sqlite:users.db";//in resource folder
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		Class.forName("org.sqlite.JDBC");
		
		return DriverManager.getConnection("jdbc:sqlite::resource:users.db");
		
	}
	
}
