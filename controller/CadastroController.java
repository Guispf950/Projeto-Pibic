package controller;

import java.io.IOException; 
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bo.UsuarioBO;
import model.bo.LoginBO;
 

public class CadastroController implements Initializable {

	@FXML
	private AnchorPane anchorPane1;

	@FXML
	private TextField txtNomeCompleto;
	@FXML
	private TextField txtSenhaVisivel;
	@FXML
	private TextField txtSenhaPassword;
	@FXML
	private TextField txtDataNasc;
	@FXML
	private TextField txtNomeUser;
	@FXML
	private TextField txtDicaSenha;
	@FXML
	private TextField txtConfirmarSenhaVisivel;
	@FXML
	private TextField txtConfirmarSenhaPassword;
	@FXML
	private Label lblEsqueceuSenha;
	@FXML
	private Button bttnCadastrar;
	@FXML
	private Label lblAcesseAqui;
	@FXML
	private ImageView cadeadoFechado;
	@FXML
	private ImageView cadeadoAberto;
	@FXML
	private CheckBox cbMasculino;
	@FXML
	private CheckBox cbFeminino;

	private Stage cadastroStage;
	
	private int telasAbertasCadastro;
	
	private LoginController loginController;
 

	

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cadeadoAberto.setVisible(false);
		txtSenhaVisivel.setVisible(false);
		txtConfirmarSenhaVisivel.setVisible(false); 

	}

	public void setCadastroStage(Stage cadastroStage) {
		this.cadastroStage = cadastroStage;
	}

	@FXML
	private void onbttnCadastrarAction() throws IOException {
		String nomeCompleto = txtNomeCompleto.getText();
		String senha = null;
		String confirmaSenha  = null;
		char sexo = 0 ;
		if (cadeadoAberto.isVisible()) {
			senha = txtSenhaVisivel.getText();
			confirmaSenha  = txtConfirmarSenhaVisivel.getText();
		} else {
			senha = txtSenhaPassword.getText();
			confirmaSenha  = txtConfirmarSenhaPassword.getText();
		}
		if(cbMasculino.isSelected()) {
			sexo = 'M';	
		} else if(cbFeminino.isSelected()) {
			sexo = 'F';
		}
		 
		String dataNasc = txtDataNasc.getText();
		String nomeUser = txtNomeUser.getText();
		String dicaSenha = txtDicaSenha.getText();
		
		boolean cadastro = new UsuarioBO().cadastrarAluno(nomeCompleto, senha, confirmaSenha, dataNasc, nomeUser, dicaSenha, sexo);
		System.out.println(cadastro);
		if(cadastro) {
			cadastroStage.close();
			Parent login = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene tela = new Scene(login);
			loginController.setTelaCadastroAberta(telasAbertasCadastro-1); //lógica para nao abrir mais de 1 tela de cadastro
			
		} 
		
	}

	@FXML
	private void onlblAcesseAquiAction() {
		cadastroStage.close();

	}

	@FXML
	private void onLblEsqueceuSenhaAction() {

	}
	@FXML
	private void onCbMasculinoAction() {
		if(cbMasculino.isSelected()) {
			cbFeminino.setSelected(false);
		}
	}
	@FXML
	private void onCbFemininoAction() {
		if(cbFeminino.isSelected()) {
			cbMasculino.setSelected(false);
		}
	}

	@FXML
	private void onLblcadeadoFechado() {
		cadeadoFechado.setVisible(false);
		cadeadoAberto.setVisible(true);

		txtSenhaVisivel.setText(txtSenhaPassword.getText());
		txtSenhaVisivel.setVisible(true);
		txtSenhaPassword.setPromptText(null);

		txtConfirmarSenhaVisivel.setText(txtConfirmarSenhaPassword.getText());
		txtConfirmarSenhaVisivel.setVisible(true);
		txtConfirmarSenhaPassword.setPromptText(null);

	}

	@FXML
	private void onLblcadeadoAberto() {
		cadeadoAberto.setVisible(false);
		cadeadoFechado.setVisible(true);

		txtSenhaPassword.setText(txtSenhaVisivel.getText());
		txtSenhaPassword.setVisible(true);
		txtSenhaVisivel.setVisible(false);

		txtConfirmarSenhaPassword.setText(txtConfirmarSenhaVisivel.getText());
		txtConfirmarSenhaPassword.setVisible(true);
		txtConfirmarSenhaVisivel.setVisible(false);
	}
	
	
	public int getTelasAbertasCadastro() {
		return telasAbertasCadastro;
	}

	public void setTelasAbertasCadastro(int telasAbertasCadastro) {
		this.telasAbertasCadastro = telasAbertasCadastro;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
}
/*
 * 
 * public void cadastrar(String nome, String sobrenome, String email, String
 * user, String senha, String confirmarSenha, String teste_data, String
 * nomeCachorro, boolean cargo_aluno, boolean cargo_prof, String comidaFav,
 * TelaCadastro tela) {
 * 
 * 
 * if (cargo_aluno == true) { new AlunoBO().cadastrarAluno(nome, sobrenome,
 * email, user, senha, confirmarSenha, teste_data, nomeCachorro, comidaFav,
 * tela); } else if (cargo_prof == true) { new
 * ProfessorBO().cadastrarProfessor(nome, sobrenome, email, user, senha,
 * confirmarSenha, teste_data, nomeCachorro, comidaFav, tela); } else if
 * (cargo_aluno == false && cargo_prof == false) {
 * JOptionPane.showMessageDialog(null, "Selecione entre Aluno e Professor",
 * "Erro no cadastro", JOptionPane.ERROR_MESSAGE);}
 * 
 * }
 */
