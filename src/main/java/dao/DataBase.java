package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	private static DataBase uniqueInstance;
	private DataBase() {
		
	}
	
	public static DataBase getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new DataBase();
		}
		return uniqueInstance;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applicationvote", "root","");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Erreur de pilote : " + ex.getMessage());
		}
		catch(SQLException ex) {
			System.out.println("Erreur SQL : " + ex.getMessage());
		}
		return conn;
	}
}
