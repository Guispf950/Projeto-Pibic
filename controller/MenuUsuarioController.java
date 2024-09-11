package controller;

import java.awt.Desktop; 
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.bo.QuizQuestaoBO;
import servicos.Servicos;
 

public class MenuUsuarioController implements Initializable {

	@FXML
	private ImageView fotoPerfil;
	@FXML
	private ImageView quiz;
	@FXML
	private Label lblUser;
	@FXML
	private Button bttnJogos;
	@FXML
	private Button bttnJogosAnteriores;
	@FXML
	private Button bttnConfiguracoes;
	@FXML
	private Label bttnSair;
	@FXML
	private Button bttnQuiz;
	@FXML
	private Button bttnAcerteAPalavra;
	@FXML
	private Button bttnOutros;
	@FXML
	private Button bttnFlashcards;
	@FXML
	private Button bttnAcerteAPalavraAction;
	 

	public Label getLblUser() {
		return lblUser;
	}

	public void setLblUser(Label lblnewUser) {
		lblUser = lblnewUser;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), bttnQuiz);
		scaleTransition1.setToX(1.1); // Fator de escala horizontal
		scaleTransition1.setToY(1.1); // Fator de escala vertical

		// Configure os eventos de mouse para iniciar e interromper a transição
		bttnQuiz.setOnMouseEntered(event -> {
			scaleTransition1.playFromStart();
		});

		bttnQuiz.setOnMouseExited(event -> {
			scaleTransition1.stop();
			bttnQuiz.setScaleX(1); // Restaurar a escala horizontal original
			bttnQuiz.setScaleY(1); // Restaurar a escala vertical original
		});

		ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(200), bttnAcerteAPalavra);
		scaleTransition2.setToX(1.1); // Fator de escala horizontal
		scaleTransition2.setToY(1.1); // Fator de escala vertical

		// Configure os eventos de mouse para iniciar e interromper a transição
		bttnAcerteAPalavra.setOnMouseEntered(event -> {
			scaleTransition2.playFromStart();
		});

		bttnAcerteAPalavra.setOnMouseExited(event -> {
			scaleTransition2.stop();
			bttnAcerteAPalavra.setScaleX(1); // Restaurar a escala horizontal original
			bttnAcerteAPalavra.setScaleY(1); // Restaurar a escala vertical original
		});

		ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(200), bttnOutros);
		scaleTransition3.setToX(1.1); // Fator de escala horizontal
		scaleTransition3.setToY(1.1); // Fator de escala vertical

		// Configure os eventos de mouse para iniciar e interromper a transição
		bttnOutros.setOnMouseEntered(event -> {
			scaleTransition3.playFromStart();
		});

		bttnOutros.setOnMouseExited(event -> {
			scaleTransition3.stop();
			bttnOutros.setScaleX(1); // Restaurar a escala horizontal original
			bttnOutros.setScaleY(1); // Restaurar a escala vertical original
		});

		ScaleTransition scaleTransition4 = new ScaleTransition(Duration.millis(200), bttnFlashcards);
		scaleTransition4.setToX(1.1); // Fator de escala horizontal
		scaleTransition4.setToY(1.1); // Fator de escala vertical

		// Configure os eventos de mouse para iniciar e interromper a transição
		bttnFlashcards.setOnMouseEntered(event -> {
			scaleTransition4.playFromStart();
		});

		bttnFlashcards.setOnMouseExited(event -> {
			scaleTransition4.stop();
			bttnFlashcards.setScaleX(1); // Restaurar a escala horizontal original
			bttnFlashcards.setScaleY(1); // Restaurar a escala vertical original
		});
		bttnFlashcards.setOnMouseClicked(event -> {
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashCardView.fxml"));
			    Parent parent;
			    try {
			        parent = loader.load();
			        Scene scene = new Scene(parent);
			        Main.getStage().setScene(scene);

			         
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		});
	}

	@FXML
	public void onBttnQuizAction() {			
			String nomeUser = lblUser.getText();
			String idQuiz = "QUIZ1"; // INSERIR O CODIGO DO JOGO AQUI
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizView.fxml"));
			Parent parent = loader.load();	
			
			

			Scene scene = new Scene(parent);
			new QuizQuestaoBO().visualizarQuestaoTelaMenu(idQuiz, nomeUser, scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void onBttnOutrosAction() {
		 String linkForms = "https://forms.gle/QpZABsBGxx4nrHWu7"; //adcionar o link do formulario
		 Servicos.chamarLink(linkForms);
		 String link = "https://poki.com/br?campaign=20614019420&adgroup=155263514338&extensionid=&targetid=kwd-434269847&location=9101696&matchtype=e&network=g&device=c&devicemodel=&creative=675877754346&keyword=jogos%20online&placement=&target=&gad_source=1&gclid=Cj0KCQjw0MexBhD3ARIsAEI3WHKv4SJAjrEyKFK_Mj9VnSEY4hgwzI8vlJr4kYLEJuPU3NReq7TM5X0aAuw2EALw_wcB";
		 Servicos.chamarLink(link);
		
	}
 
	@FXML
	public void onbttnAcerteAPalavraAction() {
		String nomeUser = lblUser.getText();
		String idQuiz = "ACPL1"; // INSERIR O CODIGO DO JOGO AQUI
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizView.fxml"));
		Parent parent = loader.load();	
		
		

		Scene scene = new Scene(parent);
		new QuizQuestaoBO().visualizarQuestaoTelaMenu(idQuiz, nomeUser, scene); //primeiro começa buscando na tabela quiz, depois vai para flash card e por ultimo acerte a palavra
		 
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		 
	}
	@FXML
	public void onBttnFlashCardAction() {
		String nomeUser = lblUser.getText();
		String idQuiz = "FLASH1"; // INSERIR O CODIGO DO JOGO AQUI
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizView.fxml"));
		Parent parent = loader.load();	
		
		

		Scene scene = new Scene(parent);
		new QuizQuestaoBO().visualizarQuestaoTelaMenu(idQuiz, nomeUser, scene); //primeiro começa buscando na tabela quiz, depois busca na tabela flash card e por ultimo acerte a palavra
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	      
	}
	@FXML
	public void onBttnSair() {

		bttnSair.setOnMouseClicked(event -> {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
			Parent parent;
			try {
				parent = loader.load();
				Scene scene = new Scene(parent);
				Main.getStage().setScene(scene);
			} catch (IOException e) {
			}

		});
	}

}