package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bo.QuizQuestaoBO;
import model.dao.QuizQuestaoDAO;
import model.vo.QuizQuestaoVO;
import servicos.Servicos;

public class QuizViewController implements Initializable {

	private String userAluno;
	private String idJogo;
	private int pulos1 = 3;
	@FXML
	private ImageView finalizarJogo;
	@FXML
	private Label pulos;
	@FXML
	private ImageView pular;
	@FXML
	private ImageView musicaJogo;
	@FXML
	private ImageView sair;
	@FXML
	private Label lblTituloQuiz;
	@FXML
	private Label lblPergunta;
	@FXML
	private Label lblAlternativaA;
	@FXML
	private Label lblAlternativaB;
	@FXML
	private Label lblAlternativaC;
	@FXML
	private Label lblAlternativaD;
	@FXML
	private Label lblQuestao;
	@FXML
	private Label lblNumero;

	private static int questao = 1;
	QuizQuestaoVO questao1;
	private boolean init = false;

	int acertos = 0;
	Timestamp tempoInicial;
	int numeroQuestao = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (init) {
			List<QuizQuestaoVO> questoes = new QuizQuestaoDAO().consultarQuestoes(idJogo);
			int questoesTotais = questoes.size();
			finalizarJogo.setOnMouseClicked(event -> {
				if (questoesTotais <= numeroQuestao) {
					JOptionPane.showMessageDialog(null, "O quiz ainda não acabou");
				} else {
					JOptionPane.showMessageDialog(null, "O quiz acabou, sua pontuação foi: " + acertos);
					Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);
					Object[] options = { "Sim", "Não" };
					int opcao = JOptionPane.showOptionDialog(null, "Quer ser direcionado ao Formulario?",
							"Google Forms", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if (opcao == 0) {
						String link = "https://forms.gle/QpZABsBGxx4nrHWu7";
						Servicos.chamarLink(link);
					}
				}
			});

			musicaJogo.setOnMouseClicked(event -> {
				Image image = new Image(getClass().getResourceAsStream("volume-x.png"));
				Image image1 = new Image(getClass().getResourceAsStream("volume-2.png"));

				if (musicaJogo.getImage() != image1) {
					musicaJogo.setImage(image);
				} else if (musicaJogo.getImage() == image) {
					musicaJogo.setImage(image1);
				}

			});

			sair.setOnMouseClicked(event -> {
				Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);

			});

			pular.setOnMouseClicked(event -> {

				if (pulos1 > 0) {
					if (showConfirmationDialog("Você tem certeza que deseja pular essa questao?")) {
						numeroQuestao++;
						proximaQuestao();
						pulos1--;
						pulos.setText("Pulos: " + Integer.toString(pulos1));

					} else {

					}
				}

			});

			lblAlternativaA.setOnMouseClicked(event -> {

				String resposta = null;
				resposta = lblAlternativaA.getText();

				String id_quiz = idJogo;
				boolean acertou = new QuizQuestaoBO().verificarResposta(resposta, id_quiz, acertos, numeroQuestao);

				if (acertou) {
					acertos++;

				}

				numeroQuestao++;
				proximaQuestao();

			});

			lblAlternativaB.setOnMouseClicked(event -> {

				// Alerts

				// Salvar respostas
				String resposta = null;
				resposta = lblAlternativaB.getText();

				String id_quiz = idJogo;

				boolean acertou = new QuizQuestaoBO().verificarResposta(resposta, id_quiz, acertos, numeroQuestao);
				if (acertou) {
					acertos++;
				}

				numeroQuestao++;
				proximaQuestao();

			});

			lblAlternativaC.setOnMouseClicked(event -> {

				// Salvar resposta
				String resposta = null;
				resposta = lblAlternativaC.getText();

				String id_quiz = idJogo;
				boolean acertou = new QuizQuestaoBO().verificarResposta(resposta, id_quiz, acertos, numeroQuestao);
				if (acertou) {
					acertos++;
				}
				numeroQuestao++;
				proximaQuestao();

			});

			lblAlternativaD.setOnMouseClicked(event -> {
				String resposta = null;
				resposta = lblAlternativaD.getText();

				String id_quiz = idJogo;
				boolean acertou = new QuizQuestaoBO().verificarResposta(resposta, id_quiz, acertos, numeroQuestao);
				if (acertou) {
					acertos++;
				}

				numeroQuestao++;
				proximaQuestao();

			});

		} else {
			init = true;
		}

	}

	private boolean showConfirmationDialog(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText(null);
		alert.setContentText(message);

		// Botões do diálogo
		ButtonType buttonTypeOK = new ButtonType("Sim");
		ButtonType buttonTypeCancel = new ButtonType("Não");

		alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

		alert.initOwner(Main.getStage());

		// Exibe o diálogo e aguarda a resposta do usuário
		return alert.showAndWait().orElse(ButtonType.CANCEL) == buttonTypeOK;
	}

	public void proximaQuestao() {
		List<QuizQuestaoVO> questoes = new QuizQuestaoDAO().consultarQuestoes(idJogo);
		if (numeroQuestao < questoes.size() + 1) {

			lblNumero.setText(Integer.toString(numeroQuestao));
			lblPergunta.setText(questoes.get(numeroQuestao - 1).getPergunta());
			lblAlternativaA.setText(questoes.get(numeroQuestao - 1).getAlternativaA());
			lblAlternativaB.setText(questoes.get(numeroQuestao - 1).getAlternativaB());
			lblAlternativaC.setText(questoes.get(numeroQuestao - 1).getAlternativaC());
			lblAlternativaD.setText(questoes.get(numeroQuestao - 1).getAlternativaD());
			lblQuestao.setText(Integer.toString(numeroQuestao) + "/" + Integer.toString(questoes.size()));
		} else {
			JOptionPane.showMessageDialog(null, "O quiz acabou, sua pontuação foi: " + acertos);
			Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);

			Object[] options = { "Sim", "Não" };
			int opcao = JOptionPane.showOptionDialog(null, "Quer ser direcionado ao Formulario?", "Google Forms",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			// se opcao for igual a 0 o usuario clicou no botão sim; se for igual a 1 clicou
			// em nao; se somente fechou o aviso é -1
			if (opcao == 0) {
				String link = "https://forms.gle/QpZABsBGxx4nrHWu7"; // adcionar o link do formulario
				Servicos.chamarLink(link);
			}
		}
	}

	public String getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(String idJogo) {
		this.idJogo = idJogo;
	}

	public Label getLblQuestao() {
		return lblQuestao;
	}

	public void setLblQuestao(Label lblQuestao) {
		this.lblQuestao = lblQuestao;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public String getUserAluno() {
		return userAluno;
	}

	public void setUserAluno(String userAluno) {
		this.userAluno = userAluno;
	}

	public Label getLblTituloQuiz() {
		return lblTituloQuiz;
	}

	public void setLblTituloQuiz(Label lblTituloQuiz) {
		this.lblTituloQuiz = lblTituloQuiz;
	}

	public Label getLblPergunta() {
		return lblPergunta;
	}

	public void setLblPergunta(Label lblPergunta) {
		this.lblPergunta = lblPergunta;
	}

	public Label getLblAlternativaA() {
		return lblAlternativaA;
	}

	public void setLblAlternativaA(Label lblAlternativaA) {
		this.lblAlternativaA = lblAlternativaA;
	}

	public Label getLblAlternativaB() {
		return lblAlternativaB;
	}

	public void setLblAlternativaB(Label lblAlternativaB) {
		this.lblAlternativaB = lblAlternativaB;
	}

	public Label getLblAlternativaC() {
		return lblAlternativaC;
	}

	public void setLblAlternativaC(Label lblAlternativaC) {
		this.lblAlternativaC = lblAlternativaC;
	}

	public Label getLblAlternativaD() {
		return lblAlternativaD;
	}

	public void setLblAlternativaD(Label lblAlternativaD) {
		this.lblAlternativaD = lblAlternativaD;
	}

}
