package model.bo;

import javax.swing.JOptionPane;

import javafx.scene.Scene;
import model.dao.LoginDAO;

public class LoginBO {

	

	public String fazerlogin(String nome_user, String senha) {
		if(!nome_user.isEmpty() && !senha.isEmpty()) {
			return new LoginDAO().fazerLogin(nome_user, senha );
			
		} else 
			JOptionPane.showMessageDialog(null, "Preencha todos os campos ","Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		return "";
	}
}