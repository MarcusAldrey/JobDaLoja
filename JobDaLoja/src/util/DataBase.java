package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	private Connection con;
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		if(con == null) {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:lavictoria.db");
		}
		return con;
	}	
}
