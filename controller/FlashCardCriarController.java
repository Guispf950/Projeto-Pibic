package controller;

import java.io.IOException; 
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.bo.FlashCardQuestaoBO;
import model.bo.QuizBO;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;

public class FlashCardCriarController implements Initializable {

	@FXML
	private VBox vbox;
	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private AnchorPane anchorPane2;
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
	private TextField txtTituloQuiz;
	@FXML
	private Label lblQuestao;
	@FXML
	private TextArea txtPergunta;
	@FXML
	private TextArea txtExplicacao; 
	@FXML
	private RadioButton rbVerdadeiro;
	@FXML
	private RadioButton rbFalso;
	 
	@FXML
	private ImageView bttnVoltarQuestao;
	@FXML
	private ImageView bttnFinalizarFlashCard;

	@FXML
	private ImageView bttnAdicionarQuestao;
	private boolean initialized = false;
	public int numeroQuestaoAtual = 1;
	private String userProf;
	int i = 0;
	 
		String id_jogo = null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblQuestao.setText("1");
		System.out.println(lblQuestao);
		 
		if (initialized) {
 
			bttnAdicionarQuestao.setOnMouseClicked(event -> {
				if (i == 0) {
					String nomeQuiz = txtTituloQuiz.getText();
					id_jogo = new QuizBO().criarQuiz(nomeQuiz, userProf);
					this.getTxtTituloQuiz().setEditable(false);
				}
				String questao = txtPergunta.getText();
				String explicacao = txtExplicacao.getText();
				 
				FlashCardCriarController tela = this;
				int condicao = 0;
				if(rbVerdadeiro.isSelected()) {
					condicao = 1;
				}
				new FlashCardQuestaoBO().adcionarQuestao(id_jogo, questao, condicao, explicacao, tela);
				 

			});
			bttnFinalizarFlashCard.setOnMouseClicked(event -> {

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
			});

		} else {
			initialized = true;
		}

	}

	@FXML
	public void onBttnCriar() {
		// ja esta nessa tela
	}

	@FXML
	public void onBttnConsultar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashCardConsultar.fxml"));
			Parent parent = loader.load();
			FlashCardConsultarController flashCardConsultarController = loader.getController();

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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashCardAlterar.fxml"));
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashCardExcluir.fxml"));
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

	 

	@FXML
	public void onBttnAdcionarQuestao() {
		if (i == 0) {
			String nomeQuiz = txtTituloQuiz.getText();
			id_jogo = new QuizBO().criarQuiz(nomeQuiz, userProf);
		}

		String questao = txtPergunta.getText();
		 
		FlashCardCriarController tela = this;
		new QuizQuestaoBO().adcionarQuestao(questao, alternativa1, alternativa2, alternativa3, alternativa4, resposta,
				tela, id_jogo, Integer.valueOf(lblQuestao.getText()));
		int ultimaQuestao = new QuizQuestaoBO().totalQuestaoQuiz(id_jogo) + 1;

		this.lblQuestao.setText(String.valueOf(ultimaQuestao));

		i++;

	}

	@FXML
	public void onbttnFinalizarQuiz() {
		onBttnAdcionarQuestao();
		JOptionPane.showMessageDialog(null, "O codigo do jogo é : " + id_jogo);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizCriarView.fxml"));
			Parent parent = loader.load();
			QuizCriarController quizCriarController = loader.getController();
			quizCriarController.setUserProf(this.userProf);

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
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
			MenuUsuarioController tela1 = loader.getController();
			tela1.getLblUser().setText(userProf);
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	public void onbttnVoltarQuestao() {

		if (Integer.valueOf(this.lblQuestao.getText()) == 1) {
			JOptionPane.showMessageDialog(null, "Não há questão anterior");
		} else {
			int numeroQuestao = Integer.valueOf(this.lblQuestao.getText()) - 1;
			this.lblQuestao.setText(String.valueOf(numeroQuestao));
			QuizQuestaoVO questao = new QuizQuestaoBO().visualizarQuestaoAnterior(id_jogo, numeroQuestao);
			this.txtPergunta.setText(questao.getPergunta());
			 

			if (questao.getResposta().equals(questao.getAlternativaA())) {
				this.rbAlternativaA.setSelected(true);
			}
			if (questao.getResposta().equals(questao.getAlternativaB())) {
				this.rbAlternativaB.setSelected(true);
			}
			if (questao.getResposta().equals(questao.getAlternativaC())) {
				this.rbAlternativaC.setSelected(true);
			}
			if (questao.getResposta().equals(questao.getAlternativaD())) {
				this.rbAlternativaD.setSelected(true);
			}

		}
	}

	private void mudarCena(String absoluteView) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteView));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Main.setMainScene(scene);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUserProf() {
		return userProf;
	}

	public void setUserProf(String userProf) {
		this.userProf = userProf;
	}

	public TextField getTxtTiuloQuiz() {
		return txtTituloQuiz;
	}

	public void setTxtTiuloQuiz(TextField txtTiuloQuiz) {
		this.txtTituloQuiz = txtTiuloQuiz;
	}

	public TextArea getTxtPergunta() {
		return txtPergunta;
	}

	public void setTxtPergunta(TextArea txtPergunta) {
		this.txtPergunta = txtPergunta;
	}

	 

	public TextField getTxtTituloQuiz() {
		return txtTituloQuiz;
	}

	public void setTxtTituloQuiz(TextField txtTituloQuiz) {
		this.txtTituloQuiz = txtTituloQuiz;
	}

	public TextArea getTxtExplicacao() {
		return txtExplicacao;
	}

	public void setTxtExplicacao(TextArea txtExplicacao) {
		this.txtExplicacao = txtExplicacao;
	}

	public RadioButton getRbVerdadeiro() {
		return rbVerdadeiro;
	}

	public void setRbVerdadeiro(RadioButton rbVerdadeiro) {
		this.rbVerdadeiro = rbVerdadeiro;
	}

	public RadioButton getRbFalso() {
		return rbFalso;
	}

	public void setRbFalso(RadioButton rbFalso) {
		this.rbFalso = rbFalso;
	}

	public ImageView getBttnAdicionarQuestao() {
		return bttnAdicionarQuestao;
	}

	public void setBttnAdicionarQuestao(ImageView bttnAdicionarQuestao) {
		this.bttnAdicionarQuestao = bttnAdicionarQuestao;
	}

	public String getId_jogo() {
		return id_jogo;
	}

	public void setId_jogo(String id_jogo) {
		this.id_jogo = id_jogo;
	}

	public Label getLblQuestao() {
		return lblQuestao;
	}

	public void setLblQuestao(Label lblQuestao) {
		this.lblQuestao = lblQuestao;
	}

}
