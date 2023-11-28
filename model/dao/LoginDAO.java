package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Main;
import controller.MenuAlunoController;
import controller.MenuProfessorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginDAO {

	public void fazerLogin(String email_user, String senha, Scene tela) {
		String sql1 = "SELECT usuario FROM aluno WHERE usuario = (?) && senha = (?);";
		String sql2 = "SELECT usuario FROM professor WHERE usuario = (?) && senha = (?);";
		String sql3 = "SELECT usuario FROM aluno WHERE email = (?) && senha = (?);";
		String sql4 = "SELECT usuario FROM professor WHERE email = (?) && senha = (?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		PreparedStatement pStatement4 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null; 
		ResultSet rs4 = null;
	
		Connection conn = null;
		boolean professor_user = false;
		boolean professor_email = false; 
		boolean aluno_user = false;
		boolean aluno_email = false;
		String usuario = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, email_user);
			pStatement1.setString(2, senha);
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				aluno_user = true;
				usuario = rs1.getString("usuario");

			}

			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, email_user);
			pStatement2.setString(2, senha);
			rs2 = pStatement2.executeQuery();
			if (rs2.next()) {
				professor_user = true;
				usuario = rs2.getString("usuario");

			}

			pStatement3 = conn.prepareStatement(sql3);
			pStatement3.setString(1, email_user);
			pStatement3.setString(2, senha);
			rs3 = pStatement3.executeQuery();
			if (rs3.next()) {
				aluno_email = true; 
				usuario = rs3.getString("usuario");

			}

			pStatement4 = conn.prepareStatement(sql4);
			pStatement4.setString(1, email_user);
			pStatement4.setString(2, senha);
			rs4 = pStatement4.executeQuery();
			if (rs4.next()) {
				professor_email = true;
				usuario = rs4.getString("usuario");

			}



			if (aluno_user || aluno_email) {
				 
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuAlunoView.fxml"));
				Parent parent;
				try {
					 
					 parent = loader.load();
					 MenuAlunoController tela1 = loader.getController();
					 tela1.lblUser.setText(usuario);
					 Scene scene = new Scene(parent);
					 
					 Main.getStage().setScene(scene);
				    
				} catch (IOException e) {
				    
				    e.printStackTrace();
				}
				
			} else if (professor_user || professor_email) {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuProfessorView.fxml"));
				Parent parent;
				try {
				    parent = loader.load();
				    MenuProfessorController tela1 = loader.getController();
				    tela1.getLblUser().setText(usuario);
				    Scene scene = new Scene(parent);
				    Main.getStage().setScene(scene);
				} catch (IOException e) {
				    
				    e.printStackTrace();
				}
				
				
				
			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}