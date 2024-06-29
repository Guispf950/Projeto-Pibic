package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.QuizQuestaoDAO;
import model.vo.QuizQuestaoVO;

public class QuizConsultarQuestoesController implements Initializable {

	private String userProf;
	private String idJogo;

	@FXML
	private TableView <QuizQuestaoVO> table ;
	@FXML
	private Label label;
	@FXML
	private Button bttnCriar1;
	@FXML
	private Button bttnConsultar1;
	@FXML
	private Button bttnAlterar1;
	@FXML
	private Button bttnExcluir1;
	@FXML
	private Button bttnVoltar1;

	@FXML
	private TableColumn <QuizQuestaoVO, String>  pergunta;
	@FXML
	private TableColumn <QuizQuestaoVO, String>  alternativaA;
	@FXML
	private TableColumn <QuizQuestaoVO, String>  alternativaB;
	@FXML
	private TableColumn <QuizQuestaoVO, String>  alternativaC;
	@FXML
	private TableColumn <QuizQuestaoVO, String>  alternativaD;

	ObservableList<QuizQuestaoVO> perguntas = FXCollections.observableArrayList();

	private boolean initialized = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (initialized) {
			perguntas.clear();
			List<QuizQuestaoVO> questoes = new QuizQuestaoDAO().consultarQuestoes(idJogo);
			
			pergunta.setCellValueFactory( new PropertyValueFactory<>("pergunta"));
			alternativaA.setCellValueFactory( new PropertyValueFactory<>("alternativaA"));
			alternativaB.setCellValueFactory( new PropertyValueFactory<>("alternativaB"));
			alternativaC.setCellValueFactory( new PropertyValueFactory<>("alternativaC"));
			alternativaD.setCellValueFactory( new PropertyValueFactory<>("alternativaD"));
			for(int i = 0 ; i < questoes.size(); i++) {
				perguntas.add(questoes.get(0));
			}
			
			
			table.setItems(perguntas);

		} else {
			initialized = true;
		}
	}
	
    

	public void configurarDados(String idJogo, String userProf) {
		this.idJogo = idJogo;
		this.userProf = userProf;
	}

	public String getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(String idJogo) {
		this.idJogo = idJogo;
	}

	public TableView<QuizQuestaoVO> getTable() {
		return table;
	}

	public void setTable(TableView<QuizQuestaoVO> table) {
		this.table = table;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getUserProf() {
		return userProf;
	}

	public void setUserProf(String userProf) {
		this.userProf = userProf;

	}

	public Label getTxtTituloQuiz() {
		// TODO Auto-generated method stub
		return label;
	}

	@FXML
	public void onBttnCriar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizCriarView.fxml"));
			Parent parent = loader.load();
			QuizCriarController quizCriarController = loader.getController();
			quizCriarController.setUserProf(userProf);

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
		}
	}
	
	@FXML
	public void onBttnVoltar() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuProfessorView.fxml"));
		Parent parent;
		try {
			parent = loader.load();
			MenuUsuarioController tela1 = loader.getController();
			tela1.getLblUser().setText(userProf);
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	public void onBttnConsultar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizConsultarView.fxml"));
			Parent parent = loader.load();
			QuizConsultarController quizConsultarController = loader.getController();
			
			quizConsultarController.setUserProf(userProf); // Configurar userProf antes de chamar initialize
			quizConsultarController.initialize(null, null);
			 
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onBttnAlterar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizAlterarView.fxml"));
			Parent parent = loader.load();
			QuizAlterarController quizConsultarController = loader.getController();
			
			quizConsultarController.setUserProf(userProf); // Configurar userProf antes de chamar initialize
			quizConsultarController.initialize(null, null);
			 
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

	@FXML
	public void onBttnExcluir() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizExcluir.fxml"));
			Parent parent = loader.load();
			QuizExcluirController quizExcluirController = loader.getController();
			
			quizExcluirController.setUserProf(userProf);
			quizExcluirController.initialize(null, null);
			 
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}


	private void mudarCena(String absoluteView) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteView));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Main.setMainScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
