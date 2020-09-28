package org.o7planning.simplewebapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	
	public static Connection getMySQLConnection() 
		throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "LinuxTest";
		String userName = "root";
		String password = "password";
		
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName,
			String userName, String password) throws SQLException,
			ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Europe/Berlin";
		
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		
		return conn;
	}
}
