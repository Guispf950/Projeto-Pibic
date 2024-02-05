package model.bo;

import javax.swing.JOptionPane;

import javafx.scene.Scene;
import model.dao.LoginDAO;

public class LoginBO {

	public void fazerlogin(String email_user, String senha, Scene tela) {
		if(!email_user.isEmpty() && !senha.isEmpty()) {
			new LoginDAO().fazerLogin(email_user, senha, tela);
			
		} else 
			JOptionPane.showMessageDialog(null, "Preencha todos os campos ","Erro no cadastro", JOptionPane.ERROR_MESSAGE);
	}

	public boolean fazerlogin(String nome_user, String senha) {
		if(!nome_user.isEmpty() && !senha.isEmpty()) {
			return new LoginDAO().fazerLogin(nome_user, senha );
			
		} else 
			JOptionPane.showMessageDialog(null, "Preencha todos os campos ","Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}