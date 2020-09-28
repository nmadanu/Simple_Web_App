package org.o7planning.simplewebapp.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	
	public static Connection getConnection()
		throws ClassNotFoundException, SQLException{
		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		}catch(Exception e) {
			
		}
	}
	
	public static void rollbackQuitely(Connection conn) {
		try {
			conn.rollback();
		}catch(Exception e) {
			
		}
	}
}
