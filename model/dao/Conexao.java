package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pibic", "root", "root");

		}catch (SQLException e) { 
			e.printStackTrace();
		} 
		
		return conn;
	}
}  