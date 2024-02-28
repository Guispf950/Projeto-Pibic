package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Main;
import controller.MenuUsuarioController;
import controller.MenuAdministradorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginDAO {

	public void fazerLogin(String email_user, String senha, Scene tela) {
		String sql1 = "SELECT usuario FROM aluno WHERE usuario = (?) && senha = (?);";
		String sql2 = "SELECT usuario FROM administrador WHERE usuario = (?) && senha = (?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;

		Connection conn = null;
		boolean adm_user = false;
		boolean usuario_user = false;

		String usuario = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, email_user);
			pStatement1.setString(2, senha);
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				usuario_user = true;
				usuario = rs1.getString("usuario");

			}

			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, email_user);
			pStatement2.setString(2, senha);
			rs2 = pStatement2.executeQuery();
			if (rs2.next()) {
				adm_user = true;
				usuario = rs2.getString("usuario");

			}

			if (usuario_user) {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuAlunoView.fxml"));
				Parent parent;
				try {

					parent = loader.load();
					MenuUsuarioController tela1 = loader.getController();
					tela1.lblUser.setText(usuario);
					Scene scene = new Scene(parent);

					Main.getStage().setScene(scene);

				} catch (IOException e) {

					e.printStackTrace();
				}

			} else if (adm_user) {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuAdministradorView.fxml"));
				Parent parent;
				try {
					parent = loader.load();
					MenuAdministradorController tela1 = loader.getController();
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

	public String fazerLogin(String nome_user, String senha) {
		String sql1 = "SELECT usuario FROM usuario WHERE usuario = (?) && senha = (?);";
		String sql2 = "SELECT usuario FROM administrador WHERE usuario = (?) && senha = (?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;

		Connection conn = null;

		String usuario = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, nome_user);
			pStatement1.setString(2, senha);
			
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, nome_user);
			pStatement2.setString(2, senha);
			
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				System.out.println("TO AQUI EM USUARIO");
				return "usuario";
			} else {
				rs2 = pStatement2.executeQuery();
					if (rs2.next()) {
						System.out.println("TO AQUI EM ADM");
						return "adm";
					}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}