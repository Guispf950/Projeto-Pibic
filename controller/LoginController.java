package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import application.Main;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bo.LoginBO;
import servicos.Servicos;

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

	private Stage primaryStage;

	int telaCadastroAberta = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtSenhaVisivel.setVisible(false);
		checkBoxMostrarSenha.setOnAction(e -> onCheckBoxMostrarSenhaAction());
	}

	@FXML
	private void onbttnEntrarAction() throws IOException {
		String nome_email = txtEmailOuUser.getText();
		String senha = null;
		if (checkBoxMostrarSenha.isSelected()) {
			senha = txtSenhaVisivel.getText();
		} else {
			senha = txtSenha.getText();
		}

		String nomeUser = txtEmailOuUser.getText();
		String login = new LoginBO().fazerlogin(nome_email, senha);
		
		
		if(login.equals("usuario")) {
			 Servicos.chamarTela("/view/MenuUsuarioView.fxml", nomeUser, MenuUsuarioController.class);
			
		}  else JOptionPane.showMessageDialog(null, "Dados Incorretos  ");
	}

	@FXML
	private void onLblEsqueceuSenhaAction() {

	}

	@FXML
	private void onLblCadastrarAction() {
		if (telaCadastroAberta == 0) {
			try {
				telaCadastroAberta++;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastrarUsuario.fxml"));
				Parent root = loader.load();

				Scene newScene = new Scene(root, 546, 671);
				CadastroController cadastroController = loader.getController();
				cadastroController.setTelasAbertasCadastro(telaCadastroAberta);
				cadastroController.setLoginController(this); //logica para nao abrir mais de uma janela de cadastro
				Stage cadastroStage = new Stage();
				cadastroController.setCadastroStage(cadastroStage);

				cadastroStage.setTitle("Tela de Cadastro");
				cadastroStage.setScene(newScene);
				cadastroStage.setResizable(false);

				// Mantém a janela principal aberta
				cadastroStage.initOwner(primaryStage);
				cadastroStage.initModality(Modality.APPLICATION_MODAL);

				cadastroStage.show();
			} catch (IOException e) {
				e.printStackTrace(); // Lida com exceção de carregamento do FXML
			}
		}

	}

	@FXML
	private void onCheckBoxMostrarSenhaAction() {
		if (checkBoxMostrarSenha.isSelected()) {
			txtSenhaVisivel.setText(txtSenha.getText());
			txtSenhaVisivel.setVisible(true);
			txtSenha.setPromptText(null);
		} else if (txtSenhaVisivel.isVisible()) {
			txtSenha.setText(txtSenhaVisivel.getText());
			txtSenha.setVisible(true);
			txtSenhaVisivel.setVisible(false);
			System.out.println("oi");
		}

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public int getTelaCadastroAberta() {
		return telaCadastroAberta;
	}

	public void setTelaCadastroAberta(int telaCadastroAberta) {
		this.telaCadastroAberta = telaCadastroAberta;
	}
}