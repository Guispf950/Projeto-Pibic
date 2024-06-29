package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SplashScreenController  implements Initializable  {
	
	@FXML
	public StackPane stackPane;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private BorderPane boderpane;

	@FXML
	private ImageView logo;
	
	private static int cont = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			
			
			if(cont < 1) {
				// Agendando uma tarefa para ser executada após 5 segundos
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
	
					@Override
					public void run() {
	
						Platform.runLater(() -> {
	
	
							try {
								Parent login = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
								Scene scene = new Scene(login);
								Main.getStage().setScene(scene);
								Main.getStage().setMaximized(true);
								//Main.getStage().setFullScreen(true);
								Main.getStage().show();
								
	
							} catch (IOException e) {
								e.printStackTrace();
							}
	
						});
					}
				}, 3500); // 5000 milissegundos = 5 segundos
				cont++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
