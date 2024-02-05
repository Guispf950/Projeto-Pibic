package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.bo.AgrupePalavrasBO;
import model.bo.AgrupePalavrasQuestaoBO;
import model.dao.AgrupePalavrasQuestaoDAO;
import model.vo.AgrupePalavrasQuestaoVO;

public class AgrupeAsPalavrasCriarController implements Initializable {

	@FXML
	private TextField txtPalavra1;
	@FXML
	private TextField txtPalavra2;
	@FXML
	private TextField txtPalavra3;
	@FXML
	private TextField txtPalavra4;
	@FXML
	private TextField txtPalavra5;
	@FXML
	private TextField txtPalavra6;
	@FXML
	private TextField txtPalavra7;
	@FXML
	private TextField txtPalavra8;
	@FXML
	private TextField txtPalavra9;
	@FXML
	private TextField txtPalavra10;
	@FXML
	private TextField txtPalavra11;
	@FXML
	private TextField txtPalavra12;
	@FXML
	private TextField txtPalavra13;
	@FXML
	private TextField txtPalavra14;
	@FXML
	private TextField txtPalavra15;
	@FXML
	private TextField txtPalavra16;
	@FXML
	private TextField txtPalavra17;
	@FXML
	private TextField txtPalavra18;
	@FXML
	private TextField txtPalavra19;
	@FXML
	private TextField txtPalavra20;
	@FXML
	private VBox vbox;
	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private AnchorPane anchorPane2;
	@FXML
	private Button bttnFinalizar;
	@FXML
	private Button bttnConsultar;
	@FXML
	private Button bttnAlterar;
	@FXML
	private Button bttnExcluir;
	@FXML
	private Button bttnVoltar;
	@FXML
	private Label lblCriar;
	@FXML
	private Label lblConsultar;
	@FXML
	private Label lblAlterar;
	@FXML
	private Label lblExcluir;
	@FXML
	private Label lblVoltar;
	@FXML
	private TextField txtTiuloGrupo1;
	@FXML
	private TextField txtTiuloGrupo2;
	@FXML
	private Button bttnFinalizarAgrupeAsPalavras;
	@FXML
	private ImageView finalizar;
	private String userProf;
	static private ArrayList<String> grupo1 = new ArrayList<>();
	static private ArrayList<String> grupo2 = new ArrayList<>();

	@FXML
	public void bttnFinalizar() {
		String id_jogo = criarAgrupePalavras("Professor", "Agrupe as Palavras");

		grupo1.add(txtPalavra1.getText());
		grupo1.add(txtPalavra2.getText());
		grupo1.add(txtPalavra3.getText());
		grupo1.add(txtPalavra4.getText());
		grupo1.add(txtPalavra5.getText());
		grupo1.add(txtPalavra6.getText());
		grupo1.add(txtPalavra7.getText());
		grupo1.add(txtPalavra8.getText());
		grupo1.add(txtPalavra9.getText());
		grupo1.add(txtPalavra10.getText());
		grupo2.add(txtPalavra11.getText());
		grupo2.add(txtPalavra12.getText());
		grupo2.add(txtPalavra13.getText());
		grupo2.add(txtPalavra14.getText());
		grupo2.add(txtPalavra15.getText());
		grupo2.add(txtPalavra16.getText());
		grupo2.add(txtPalavra17.getText());
		grupo2.add(txtPalavra18.getText());
		grupo2.add(txtPalavra19.getText());
		grupo2.add(txtPalavra20.getText());

		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/AgrupeAsPalavrasCriar.fxml"));
		Parent parent;
		try {
			parent = loader1.load();
			Scene scene = new Scene(parent);
			for (String palavra : grupo1) {
				adcionarQuestao(palavra, 1, id_jogo, scene);
			}
			for (String palavra : grupo1) {
				adcionarQuestao(palavra, 2, id_jogo, scene);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String criarAgrupePalavras(String nomeProfessor, String nomeJogo) {
		String id_jogo = null;
		id_jogo = new AgrupePalavrasBO().criarAgrupePalavras(nomeProfessor, nomeJogo);
		return id_jogo;
	}

	public void alterarNomeAcertePalavra(String novoNome, String idJogo) {
		new AgrupePalavrasBO().alterarNomeAgrupePalavras(novoNome, idJogo);
	}

	public void excluirAcertePalavra(String idJogo) {
		new AgrupePalavrasBO().excluirAgrupePalavras(idJogo);
	}

	public void adcionarQuestao(String palavra, int grupo, String id_jogo, Scene tela) { // SUBSTITUIR

		new AgrupePalavrasQuestaoBO().adcionarQuestao(palavra, grupo, id_jogo, tela); // SUBSTITUIR ESSA TELA

	}

	public List<AgrupePalavrasQuestaoVO> consultarQuestoesId(String id_jogo) {

		List<AgrupePalavrasQuestaoVO> questoes = new ArrayList<>();
		questoes = new AgrupePalavrasQuestaoDAO().consultarQuestoes(id_jogo);
		return questoes;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void onBttnCriar() {
		mudarCena("/view/QuizCriarView.fxml");
	}

	@FXML
	public void onBttnConsultar() {
		mudarCena("/view/QuizConsultarView.fxml");
	}

	@FXML
	public void onBttnAlterar() {
		mudarCena("/view/QuizAlterarView.fxml");
	}

	@FXML
	public void onBttnExcluir() {
		mudarCena("/view/QuizExcluirView.fxml");
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

	@FXML
	private void onBttnFinalizarAction() {
		 ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Informações");
	        alert.setHeaderText(null);
	        alert.setContentText("Código: 2T5HG");

			alert.getButtonTypes().setAll(okButton, ButtonType.CANCEL);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent() && result.get() == okButton) {
	            // Lógica a ser executada quando o botão OK é pressionado
	            System.out.println("Botão OK foi pressionado!");
	        } else {
	            // Lógica a ser executada quando o botão Cancelar é pressionado ou o Alert é fechado
	            System.out.println("Botão Cancelar ou Alert fechado.");
	        }
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuProfessorView.fxml"));
			Parent parent;
			try {
			    parent = loader.load();
			    Scene scene = new Scene(parent);
			    Main.setMainScene(scene);
			    Main.getStage().setScene(Main.getMainScene());
			} catch (IOException e) {
			    
			    e.printStackTrace();
			}
	        
	      
	}
	
	@FXML
		public void onBttnVoltar() {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuProfessorView.fxml"));
			Parent parent;
			try {
				parent = loader.load();
				MenuProfessorController tela1 = loader.getController();
				tela1.getLblUser().setText(userProf);
				Scene scene = new Scene(parent);
				Main.getStage().setScene(scene);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		public String getUserProf() {
			return userProf;
		}

		public void setUserProf(String userProf) {
			this.userProf = userProf;
		}

}
