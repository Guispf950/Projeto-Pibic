package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import javafx.event.EventHandler;
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
import model.bo.QuizBO;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;
import servicos.Servicos;

public class QuizAlterarQuestao implements Initializable {

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
	private TextField txtAlternativaA;
	@FXML
	private TextField txtAlternativaB;
	@FXML
	private TextField txtAlternativaC;
	@FXML
	private TextField txtAlternativaD;
	@FXML
	private RadioButton rbAlternativaA;
	@FXML
	private RadioButton rbAlternativaB;
	@FXML
	private RadioButton rbAlternativaC;
	@FXML
	private RadioButton rbAlternativaD;
	@FXML
	private ImageView bttnVoltarQuestao;

	@FXML
	private ImageView bttnAdicionarQuestao;

	@FXML
	private ImageView bttnFinalizarQuiz;

	public int numeroQuestaoAtual = 1;
	private ToggleGroup grupoAlternativas;

	private String userProf;

	private boolean initialized = false;
	private String id_jogo = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblQuestao.setText("1");
		System.out.println(lblQuestao);
		if (initialized) {
			

			grupoAlternativas = new ToggleGroup();
			rbAlternativaA.setToggleGroup(grupoAlternativas);
			rbAlternativaB.setToggleGroup(grupoAlternativas);
			rbAlternativaC.setToggleGroup(grupoAlternativas);
			rbAlternativaD.setToggleGroup(grupoAlternativas);

			bttnVoltarQuestao.setOnMouseClicked(event -> {
				System.out.println("teste");

				if (Integer.valueOf(this.lblQuestao.getText()) == 1) {
					JOptionPane.showMessageDialog(null, "Não há questão anterior");
				} else {
					int numeroQuestao = Integer.valueOf(this.lblQuestao.getText()) - 1;
					this.lblQuestao.setText(String.valueOf(numeroQuestao));
					QuizQuestaoVO questao = new QuizQuestaoBO().visualizarQuestaoAnterior(id_jogo, numeroQuestao);
					this.txtPergunta.setText(questao.getPergunta());
					this.txtAlternativaA.setText(questao.getAlternativaA());
					this.txtAlternativaB.setText(questao.getAlternativaB());
					this.txtAlternativaC.setText(questao.getAlternativaC());
					this.txtAlternativaD.setText(questao.getAlternativaD());

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
			});

			bttnAdicionarQuestao.setOnMouseClicked(event -> {
				String questao = txtPergunta.getText();
				String alternativa1 = txtAlternativaA.getText();
				String alternativa2 = txtAlternativaB.getText();
				String alternativa3 = txtAlternativaC.getText();
				String alternativa4 = txtAlternativaD.getText();
				String resposta = null;
				if (rbAlternativaA.isSelected()) {
					resposta = alternativa1;
				}
				if (rbAlternativaB.isSelected()) {
					resposta = alternativa2;
				}
				if (rbAlternativaC.isSelected()) {
					resposta = alternativa3;
				}
				if (rbAlternativaD.isSelected()) {
					resposta = alternativa1;
				}
				QuizAlterarQuestao tela = this;
				System.out.println("ID DO QUIZ" + id_jogo);
				new QuizQuestaoBO().proximaQuestao(questao, alternativa1, alternativa2, alternativa3, alternativa4,
						resposta, tela, id_jogo, Integer.valueOf(lblQuestao.getText()));

			});
			bttnFinalizarQuiz.setOnMouseClicked(event -> {

				Servicos.chamarTela("/view/MenuAdministradorView.fxml", userProf, MenuUsuarioController.class);
			});

		} else {
			initialized = true;
		}

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

	int i = 0;

	@FXML
	public void onBttnAdcionarQuestao() {
		System.out.println("teste");
		if (i == 0) {
			String nomeQuiz = txtTituloQuiz.getText();
			id_jogo = new QuizBO().criarQuiz(nomeQuiz, userProf);
		}

		String questao = txtPergunta.getText();
		String alternativa1 = txtAlternativaA.getText();
		String alternativa2 = txtAlternativaB.getText();
		String alternativa3 = txtAlternativaC.getText();
		String alternativa4 = txtAlternativaD.getText();
		String resposta = null;
		if (rbAlternativaA.isSelected()) {
			resposta = alternativa1;
		}
		if (rbAlternativaB.isSelected()) {
			resposta = alternativa2;
		}
		if (rbAlternativaC.isSelected()) {
			resposta = alternativa3;
		}
		if (rbAlternativaD.isSelected()) {
			resposta = alternativa1;
		}
		QuizAlterarQuestao tela = this;
		new QuizQuestaoBO().proximaQuestao(questao, alternativa1, alternativa2, alternativa3, alternativa4, resposta,
				tela, id_jogo, Integer.valueOf(lblQuestao.getText()));
		int ultimaQuestao = new QuizQuestaoBO().totalQuestaoQuiz(id_jogo) + 1;

		this.lblQuestao.setText(String.valueOf(ultimaQuestao));

		i++;

	}

	@FXML
	public void onBttnVoltar() {
		Servicos.chamarTela("/view/MenuAdministradorView.fxml", userProf, MenuUsuarioController.class);

	}

	@FXML
	public void onbttnVoltarQuestao() {
		System.out.println("teste");

		if (Integer.valueOf(this.lblQuestao.getText()) == 1) {
			JOptionPane.showMessageDialog(null, "Não há questão anterior");
		} else {
			int numeroQuestao = Integer.valueOf(this.lblQuestao.getText()) - 1;
			this.lblQuestao.setText(String.valueOf(numeroQuestao));
			QuizQuestaoVO questao = new QuizQuestaoBO().visualizarQuestaoAnterior(id_jogo, numeroQuestao);
			this.txtPergunta.setText(questao.getPergunta());
			this.txtAlternativaA.setText(questao.getAlternativaA());
			this.txtAlternativaB.setText(questao.getAlternativaB());
			this.txtAlternativaC.setText(questao.getAlternativaC());
			this.txtAlternativaD.setText(questao.getAlternativaD());

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

	public TextField getTxtAlternativaA() {
		return txtAlternativaA;
	}

	public void setTxtAlternativaA(TextField txtAlternativaA) {
		this.txtAlternativaA = txtAlternativaA;
	}

	public TextField getTxtAlternativaB() {
		return txtAlternativaB;
	}

	public void setTxtAlternativaB(TextField txtAlternativaB) {
		this.txtAlternativaB = txtAlternativaB;
	}

	public TextField getTxtAlternativaC() {
		return txtAlternativaC;
	}

	public void setTxtAlternativaC(TextField txtAlternativaC) {
		this.txtAlternativaC = txtAlternativaC;
	}

	public TextField getTxtAlternativaD() {
		return txtAlternativaD;
	}

	public void setTxtAlternativaD(TextField txtAlternativaD) {
		this.txtAlternativaD = txtAlternativaD;
	}

	public RadioButton getRbAlternativaA() {
		return rbAlternativaA;
	}

	public void setRbAlternativaA(RadioButton rbAlternativaA) {
		this.rbAlternativaA = rbAlternativaA;
	}

	public RadioButton getRbAlternativaB() {
		return rbAlternativaB;
	}

	public void setRbAlternativaB(RadioButton rbAlternativaB) {
		this.rbAlternativaB = rbAlternativaB;
	}

	public RadioButton getRbAlternativaC() {
		return rbAlternativaC;
	}

	public void setRbAlternativaC(RadioButton rbAlternativaC) {
		this.rbAlternativaC = rbAlternativaC;
	}

	public RadioButton getRbAlternativaD() {
		return rbAlternativaD;
	}

	public void setRbAlternativaD(RadioButton rbAlternativaD) {
		this.rbAlternativaD = rbAlternativaD;
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