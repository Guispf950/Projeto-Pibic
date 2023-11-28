package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import view.util.Alerts;

public class AgrupeAsPalavrasController implements Initializable{
	
	@FXML private Label palavra1;
	@FXML private Label palavra2;
	@FXML private Label palavra3;
	@FXML private Label palavra4;
	@FXML private Label palavra5;
	@FXML private Label palavra6;
	@FXML private Label palavra7;
	@FXML private Label palavra8;
	@FXML private Label palavra9;
	@FXML private Label palavra10;
	@FXML private Label palavra11;
	@FXML private Label palavra12;
	@FXML private Label palavra13;
	@FXML private Label palavra14;
	@FXML private Label palavra15;
	@FXML private Label palavra16;
	@FXML private Label palavra17;
	@FXML private Label palavra18;
	@FXML private Label palavra19;
	@FXML private Label palavra20;
	 double originalX, originalY;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		palavra1.setOnMousePressed(event -> {
            originalX = palavra1.getTranslateX() - event.getSceneX();
            originalY = palavra1.getTranslateY() - event.getSceneY();
        });

		palavra1.setOnMouseDragged(event -> {
			palavra1.setTranslateX(event.getSceneX() + originalX);
			palavra1.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra2.setOnMousePressed(event -> {
            originalX = palavra2.getTranslateX() - event.getSceneX();
            originalY = palavra2.getTranslateY() - event.getSceneY();
        });

		palavra2.setOnMouseDragged(event -> {
			palavra2.setTranslateX(event.getSceneX() + originalX);
			palavra2.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra3.setOnMousePressed(event -> {
            originalX = palavra3.getTranslateX() - event.getSceneX();
            originalY = palavra3.getTranslateY() - event.getSceneY();
        });

		palavra3.setOnMouseDragged(event -> {
			palavra3.setTranslateX(event.getSceneX() + originalX);
			palavra3.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra4.setOnMousePressed(event -> {
            originalX = palavra4.getTranslateX() - event.getSceneX();
            originalY = palavra4.getTranslateY() - event.getSceneY();
        });

		palavra4.setOnMouseDragged(event -> {
			palavra4.setTranslateX(event.getSceneX() + originalX);
			palavra4.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra5.setOnMousePressed(event -> {
            originalX = palavra5.getTranslateX() - event.getSceneX();
            originalY = palavra5.getTranslateY() - event.getSceneY();
        });

		palavra5.setOnMouseDragged(event -> {
			palavra5.setTranslateX(event.getSceneX() + originalX);
			palavra5.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra6.setOnMousePressed(event -> {
            originalX = palavra6.getTranslateX() - event.getSceneX();
            originalY = palavra6.getTranslateY() - event.getSceneY();
        });

		palavra6.setOnMouseDragged(event -> {
			palavra6.setTranslateX(event.getSceneX() + originalX);
			palavra6.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra7.setOnMousePressed(event -> {
            originalX = palavra7.getTranslateX() - event.getSceneX();
            originalY = palavra7.getTranslateY() - event.getSceneY();
        });

		palavra7.setOnMouseDragged(event -> {
			palavra7.setTranslateX(event.getSceneX() + originalX);
			palavra7.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra8.setOnMousePressed(event -> {
            originalX = palavra8.getTranslateX() - event.getSceneX();
            originalY = palavra8.getTranslateY() - event.getSceneY();
        });

		palavra8.setOnMouseDragged(event -> {
			palavra8.setTranslateX(event.getSceneX() + originalX);
			palavra8.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra9.setOnMousePressed(event -> {
            originalX = palavra9.getTranslateX() - event.getSceneX();
            originalY = palavra9.getTranslateY() - event.getSceneY();
        });

		palavra9.setOnMouseDragged(event -> {
			palavra9.setTranslateX(event.getSceneX() + originalX);
			palavra9.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra10.setOnMousePressed(event -> {
            originalX = palavra10.getTranslateX() - event.getSceneX();
            originalY = palavra10.getTranslateY() - event.getSceneY();
        });

		palavra10.setOnMouseDragged(event -> {
			palavra10.setTranslateX(event.getSceneX() + originalX);
			palavra10.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra11.setOnMousePressed(event -> {
            originalX = palavra11.getTranslateX() - event.getSceneX();
            originalY = palavra11.getTranslateY() - event.getSceneY();
        });

		palavra11.setOnMouseDragged(event -> {
			palavra11.setTranslateX(event.getSceneX() + originalX);
			palavra11.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra12.setOnMousePressed(event -> {
            originalX = palavra12.getTranslateX() - event.getSceneX();
            originalY = palavra12.getTranslateY() - event.getSceneY();
        });

		palavra12.setOnMouseDragged(event -> {
			palavra12.setTranslateX(event.getSceneX() + originalX);
			palavra12.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra13.setOnMousePressed(event -> {
            originalX = palavra13.getTranslateX() - event.getSceneX();
            originalY = palavra13.getTranslateY() - event.getSceneY();
        });

		palavra13.setOnMouseDragged(event -> {
			palavra13.setTranslateX(event.getSceneX() + originalX);
			palavra13.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra14.setOnMousePressed(event -> {
            originalX = palavra14.getTranslateX() - event.getSceneX();
            originalY = palavra14.getTranslateY() - event.getSceneY();
        });

		palavra14.setOnMouseDragged(event -> {
			palavra14.setTranslateX(event.getSceneX() + originalX);
			palavra14.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra15.setOnMousePressed(event -> {
            originalX = palavra15.getTranslateX() - event.getSceneX();
            originalY = palavra15.getTranslateY() - event.getSceneY();
        });

		palavra15.setOnMouseDragged(event -> {
			palavra15.setTranslateX(event.getSceneX() + originalX);
			palavra15.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra16.setOnMousePressed(event -> {
            originalX = palavra16.getTranslateX() - event.getSceneX();
            originalY = palavra16.getTranslateY() - event.getSceneY();
        });

		palavra16.setOnMouseDragged(event -> {
			palavra16.setTranslateX(event.getSceneX() + originalX);
			palavra16.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra17.setOnMousePressed(event -> {
            originalX = palavra17.getTranslateX() - event.getSceneX();
            originalY = palavra17.getTranslateY() - event.getSceneY();
        });

		palavra17.setOnMouseDragged(event -> {
			palavra17.setTranslateX(event.getSceneX() + originalX);
			palavra17.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra18.setOnMousePressed(event -> {
            originalX = palavra18.getTranslateX() - event.getSceneX();
            originalY = palavra18.getTranslateY() - event.getSceneY();
        });

		palavra18.setOnMouseDragged(event -> {
			palavra18.setTranslateX(event.getSceneX() + originalX);
			palavra18.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra19.setOnMousePressed(event -> {
            originalX = palavra19.getTranslateX() - event.getSceneX();
            originalY = palavra19.getTranslateY() - event.getSceneY();
        });

		palavra19.setOnMouseDragged(event -> {
			palavra19.setTranslateX(event.getSceneX() + originalX);
			palavra19.setTranslateY(event.getSceneY() + originalY);
        });
		
		palavra20.setOnMousePressed(event -> {
            originalX = palavra20.getTranslateX() - event.getSceneX();
            originalY = palavra20.getTranslateY() - event.getSceneY();
        });

		palavra20.setOnMouseDragged(event -> {
			palavra20.setTranslateX(event.getSceneX() + originalX);
			palavra20.setTranslateY(event.getSceneY() + originalY);
        });

		
		
	}
	
	@FXML Button bbtnFinalizar;
	
	@FXML
	public void onBttnFinalizar() {
		
		 palavra1.setStyle("-fx-background-color: lightgreen");
		 palavra2.setStyle("-fx-background-color: lightgreen");
		 palavra3.setStyle("-fx-background-color: lightgreen");
		 palavra4.setStyle("-fx-background-color: lightgreen");
		 palavra5.setStyle("-fx-background-color: lightgreen");
		 palavra6.setStyle("-fx-background-color: lightgreen");
		 palavra7.setStyle("-fx-background-color: lightgreen");
		 palavra8.setStyle("-fx-background-color: lightgreen");
		 palavra9.setStyle("-fx-background-color: lightgreen");
		 palavra10.setStyle("-fx-background-color: lightgreen");
		 palavra11.setStyle("-fx-background-color: lightgreen");
		 palavra12.setStyle("-fx-background-color: lightgreen");
		 palavra13.setStyle("-fx-background-color: lightgreen");
		 palavra14.setStyle("-fx-background-color: lightgreen");
		 palavra15.setStyle("-fx-background-color: lightgreen");
		 palavra16.setStyle("-fx-background-color: lightgreen");
		 palavra17.setStyle("-fx-background-color: lightgreen");
		 palavra18.setStyle("-fx-background-color: lightgreen");
		 palavra19.setStyle("-fx-background-color: lightgreen");
		 palavra20.setStyle("-fx-background-color: lightgreen");
		
		
		 ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Pontuação");
	        alert.setHeaderText(null);
	        alert.setContentText("Pontuação: 100%");

			alert.getButtonTypes().setAll(okButton, ButtonType.CANCEL);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent() && result.get() == okButton) {
	            // Lógica a ser executada quando o botão OK é pressionado
	            System.out.println("Botão OK foi pressionado!");
	        } else {
	            // Lógica a ser executada quando o botão Cancelar é pressionado ou o Alert é fechado
	            System.out.println("Botão Cancelar ou Alert fechado.");
	        }
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuAlunoView.fxml"));
		Parent parent;
		try {
		    parent = loader.load();
		    Scene scene = new Scene(parent);
		    Main.getStage().setScene(scene);
		} catch (IOException e) {
		    
		    e.printStackTrace();
		}
	}

}
