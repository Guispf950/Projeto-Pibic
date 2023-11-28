package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.bo.LoginBO;

public class LoginController implements Initializable {

	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private CheckBox checkBoxMostrarSenha;
	@FXML
	private TextField txtEmailOuUser;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Label lblEsqueceuSenha;
	@FXML
	private Button bttnEntrar;
	@FXML
	private Label lblCadastrar;
	@FXML
	private TextField txtSenhaVisivel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtSenhaVisivel.setVisible(false);
		checkBoxMostrarSenha.setOnAction(e -> onCheckBoxMostrarSenhaAction());
	}

	@FXML
	private void onbttnEntrarAction() throws IOException {
		String nome_email = txtEmailOuUser.getText();
		String senha = null;
		if(checkBoxMostrarSenha.isSelected()) {
			senha = txtSenhaVisivel.getText(); 
		}
		senha = txtSenha.getText();
		Parent login = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		Scene tela = new Scene(login);
		new LoginBO().fazerlogin(nome_email, senha, tela);
	}

	@FXML
	private void onLblEsqueceuSenhaAction() {

	}

	@FXML
	private void onLblCadastrarAction() {

	}
	 

	@FXML
	private void onCheckBoxMostrarSenhaAction() {
		if (checkBoxMostrarSenha.isSelected()) {
			 txtSenhaVisivel.setText(txtSenha.getText());
			 txtSenhaVisivel.setVisible(true);
			 txtSenha.setPromptText(null);
		} else if(txtSenhaVisivel.isVisible()){	 
			 txtSenha.setText(txtSenhaVisivel.getText());
			 txtSenha.setVisible(true);
			 txtSenhaVisivel.setVisible(false);
			System.out.println("oi");
		}

	}

}