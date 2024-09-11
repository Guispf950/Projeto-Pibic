package servicos;

import java.awt.Desktop; 
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.Main;
import controller.MenuUsuarioController;
import controller.MenuUsuarioController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Servicos {
	
	public static void chamarTela(String endereco) {
		try {
			FXMLLoader loader = new FXMLLoader(Servicos.class.getResource(endereco));
			Parent parent = loader.load();			
			parent = loader.load();
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void chamarTela(String endereco, String user, Class<?> controllerClass) {
	    FXMLLoader loader = new FXMLLoader(Servicos.class.getResource(endereco));
	    Parent parent;
	    try {
	        parent = loader.load();
	        Object controller = loader.getController();

	       if (controllerClass.equals(MenuUsuarioController.class)) {
	            ((MenuUsuarioController) controller).getLblUser().setText(user);
	        }

	        Scene scene = new Scene(parent);
	        Main.getStage().setScene(scene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void chamarLink(String link) {
		try {
		       Desktop.getDesktop().browse(new URI(link));
		    }
		    catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
	}
}
/*	@FXML
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

 
}
@FXML
public void onBttnFlashCardAction() {
  
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

} */