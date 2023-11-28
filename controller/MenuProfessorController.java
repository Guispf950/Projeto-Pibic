package controller;

import java.io.IOException;
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
import view.FlashCardCriarController;

public class MenuProfessorController implements Initializable {

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
	private Button bttnAgrupePalavras;
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

		ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(200), bttnAgrupePalavras);
		scaleTransition3.setToX(1.1); // Fator de escala horizontal
		scaleTransition3.setToY(1.1); // Fator de escala vertical

		// Configure os eventos de mouse para iniciar e interromper a transição
		bttnAgrupePalavras.setOnMouseEntered(event -> {
			scaleTransition3.playFromStart();
		});

		bttnAgrupePalavras.setOnMouseExited(event -> {
			scaleTransition3.stop();
			bttnAgrupePalavras.setScaleX(1); // Restaurar a escala horizontal original
			bttnAgrupePalavras.setScaleY(1); // Restaurar a escala vertical original
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

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizCriarView.fxml"));
			Parent parent = loader.load();			
			QuizCriarController quizCriarController = loader.getController();
			quizCriarController.setUserProf(this.lblUser.getText());
			

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void onBttnAgrupePalavrasAction() {

		bttnAgrupePalavras.setOnMouseClicked(event -> {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AgrupeAsPalavrasCriar.fxml"));
			Parent parent;
			try {
				parent = loader.load();
				Scene scene = new Scene(parent);
				Main.getStage().setScene(scene);
			} catch (IOException e) {
			}

		});
	}
 
	@FXML
	public void onbttnAcerteAPalavraAction() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AcerteAPalavraCriar.fxml"));
	        Parent parent = loader.load();
	        Scene scene = new Scene(parent);
	        Main.getStage().setScene(scene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	@FXML
	public void onBttnFlashCardAction() {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashcardCriar.fxml"));
	    Parent parent;
	    try {
	        parent = loader.load();
	        Scene scene = new Scene(parent);
	        Main.getStage().setScene(scene);

	         
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