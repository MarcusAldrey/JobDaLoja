package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	private static Connection con;
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		if(con == null) {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/Users/DIOGO PC/Desktop/La Victoria.db");
		}
		return con;
	}	
}
