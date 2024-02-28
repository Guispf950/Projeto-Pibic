

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MenuUsuarioController implements Initializable {
	
	@FXML Button seta;
	@FXML TextField txtCodigoJogo;
	@FXML Label sair;
	
	@FXML public Label lblUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		seta.setOnMouseClicked(event -> {
			String idJogo = txtCodigoJogo.getText();
			String userAluno = lblUser.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizView.fxml"));
			Parent parent;
			try {
				 
				 parent = loader.load();
				 Scene scene = new Scene(parent);
				 new QuizQuestaoController().visualizarQuestaoTelaMenu(idJogo, userAluno, scene);
			    
			} catch (IOException e) {
			    
			    e.printStackTrace();
			}
			
		    
		});
		

		sair.setOnMouseClicked(event -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
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
	  	
	
	public Button getSeta() {
		return seta;
	}

	public void setSeta(Button seta) {
		this.seta = seta;
	}

	public TextField getTxtCodigoJogo() {
		return txtCodigoJogo;
	}

	public void setTxtCodigoJogo(TextField txtCodigoJogo) {
		this.txtCodigoJogo = txtCodigoJogo;
	}

	public Label getSair() {
		return sair;
	}

	public void setSair(Label sair) {
		this.sair = sair;
	}

	public Label getLblUser() {
		return lblUser;
	}

	public void setLblUser(Label lblUser) {
		this.lblUser = lblUser;
	}

	public Label getLblSair() {
		return lblSair;
	}

	public void setLblSair(Label lblSair) {
		this.lblSair = lblSair;
	}

	@FXML
	public void onBttnSeta() {
		
	}
	
	 @FXML
	    private Label lblSair;

	   

}
