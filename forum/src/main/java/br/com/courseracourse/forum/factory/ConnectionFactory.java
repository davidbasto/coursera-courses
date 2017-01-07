package br.com.courseracourse.forum.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String CONNECTION_STRING = "jdbc:h2:tcp://localhost/~/coursera";
	
	private static final String USER = "sa";

	private static final String PASSWORD = "";

	static {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
	}
}
