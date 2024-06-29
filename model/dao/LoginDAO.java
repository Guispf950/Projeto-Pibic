package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Main;
import controller.MenuUsuarioController;
import controller.MenuUsuarioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginDAO {

	
	public String fazerLogin(String nome_user, String senha) {
		String sql1 = "SELECT usuario FROM usuario WHERE usuario = (?) && senha = (?);";
		
		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		Connection conn = null;

		String usuario = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, nome_user);
			pStatement1.setString(2, senha);
			rs1 = pStatement1.executeQuery();
			
			if (rs1.next()) {
				return "usuario";
			}  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}