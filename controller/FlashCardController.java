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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bo.FlashCardQuestaoBO;
import model.dao.FlashCardQuestaoDAO;
import model.vo.FlashCardQuestaoVO;
import model.vo.QuizQuestaoVO;
import servicos.Servicos;

public class FlashCardController implements Initializable {

	@FXML
	Button bttnVerdadeiro;
	@FXML
	Button bttnFalso;
	@FXML
	ImageView proximaQuestao;
	@FXML
	Label lblCard;
	@FXML
	Label lblNomeDoJogo;
	@FXML
	Label lblPergunta;
	@FXML
	private ImageView finalizarJogo;
	@FXML
	private ImageView sair;
	@FXML
	private ImageView musicaJogo;

	private String explicacao;
	private int condicao;
	private String idJogo;
	private String userAluno;
	private boolean init = false;
	private static int questao = 1;
	QuizQuestaoVO questao1;
	Timestamp tempoInicial;
	int acertos = 0;
	int numeroQuestao = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (init) {
			List<FlashCardQuestaoVO> questoes = new FlashCardQuestaoDAO().consultarQuestoes(idJogo);

			bttnVerdadeiro.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1) {
					if (numeroQuestao < questoes.size()) {
						bttnFalso.setDisable(true);
						bttnVerdadeiro.setDisable(true);
						numeroQuestao++;

						int condicao = 1;
						String idFlashCard = this.idJogo;
						boolean acertou = new FlashCardQuestaoBO().verificarResposta(condicao, idFlashCard, acertos, numeroQuestao);
						if (acertou) {
							acertos++;
							lblPergunta.setStyle("-fx-background-color: green;");
							lblPergunta.setText(questoes.get(numeroQuestao - 1).getExplicacao());
						} else {
						lblPergunta.setStyle("-fx-background-color: #ff6961;");
						lblPergunta.setText(questoes.get(numeroQuestao - 1).getExplicacao());
						
						}

					} else
						showConfirmationDialog("Você esta na ultima questao");

				}
			}

			);

			bttnFalso.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1) {
					if (numeroQuestao < questoes.size()) {
						bttnFalso.setDisable(true);
						bttnVerdadeiro.setDisable(true);
						numeroQuestao++;
						int condicao = 0;
						String idFlashCard = this.idJogo;
						boolean acertou = new FlashCardQuestaoBO().verificarResposta(condicao, idFlashCard, acertos, numeroQuestao);
						if (acertou) {
							acertos++;
							lblPergunta.setStyle("-fx-background-color: green;");
							lblPergunta.setText(questoes.get(numeroQuestao - 1).getExplicacao());
						} else {
							lblPergunta.setStyle("-fx-background-color: red;");
							lblPergunta.setText(questoes.get(numeroQuestao - 1).getExplicacao());
						}

					} else
						showConfirmationDialog("Você esta na ultima questao");

				}
			}

			);

			proximaQuestao.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1) {
					bttnFalso.setDisable(false);
					bttnVerdadeiro.setDisable(false);
					if (numeroQuestao < questoes.size()) {
						if (questoes.get(numeroQuestao - 1).getExplicacao() == lblPergunta.getText()) {
							lblPergunta.setStyle("-fx-background-color: white;");
							lblPergunta.setText(questoes.get(numeroQuestao).getFrase());
							lblCard.setText("Card " +  numeroQuestao + "/"
									+ Integer.toString(questoes.size()));
						} else
							showConfirmationDialog("Responda se é verdadeiro ou falso");
						{

						}

					} else {
						showConfirmationDialog("Você Finalizou o Jogou, sua pontuação foi : " + acertos);
						Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);
					}

				}
			});

			finalizarJogo.setOnMouseClicked(event -> {
				if (questao + 1 < numeroQuestao) {
					JOptionPane.showMessageDialog(null, "O quiz ainda não acabou");
				} else {
					JOptionPane.showMessageDialog(null, "O quiz acabou, sua pontuação foi: " + acertos);

					try {

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuAlunoView.fxml"));
						Parent parent = loader.load();
						MenuUsuarioController quizAlterarQuestao = loader.getController();

						quizAlterarQuestao.getLblUser().setText(userAluno);
						quizAlterarQuestao.initialize(null, null);

						Scene scene = new Scene(parent);
						Main.getStage().setScene(scene);
					} catch (IOException e) {
						e.printStackTrace();
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

				// NAO CONSEGUI
			});

			sair.setOnMouseClicked(event -> {
				Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);

			});

		} else {
			init = true;
		}

	}

	private void explicação() {

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

	int totalQuestoes;

	public int getTotalQuestoes() {
		return totalQuestoes;
	}

	public void setTotalQuestoes(int totalQuestoes) {
		this.totalQuestoes = totalQuestoes;
	}

	public String getExplicacao() {
		return explicacao;
	}

	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}

	public int getCondicao() {
		return condicao;
	}

	public void setCondicao(int condicao) {
		this.condicao = condicao;
	}

	public Label getLblPergunta() {
		return lblPergunta;
	}

	public void setLblPergunta(Label lblPergunta) {
		this.lblPergunta = lblPergunta;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public static int getQuestao() {
		return questao;
	}

	public static void setQuestao(int questao) {
		FlashCardController.questao = questao;
	}

	public QuizQuestaoVO getQuestao1() {
		return questao1;
	}

	public void setQuestao1(QuizQuestaoVO questao1) {
		this.questao1 = questao1;
	}

	public Timestamp getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Timestamp tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public Button getBttnVerdadeiro() {
		return bttnVerdadeiro;
	}

	public void setBttnVerdadeiro(Button bttnVerdadeiro) {
		this.bttnVerdadeiro = bttnVerdadeiro;
	}

	public Button getBttnFalso() {
		return bttnFalso;
	}

	public void setBttnFalso(Button bttnFalso) {
		this.bttnFalso = bttnFalso;
	}

	public ImageView getProximaQuestao() {
		return proximaQuestao;
	}

	public void setProximaQuestao(ImageView proximaQuestao) {
		this.proximaQuestao = proximaQuestao;
	}

	public Label getLblCard() {
		return lblCard;
	}

	public void setLblCard(Label lblCard) {
		this.lblCard = lblCard;
	}

	public Label getLblNomeDoJogo() {
		return lblNomeDoJogo;
	}

	public void setLblNomeDoJogo(Label lblNomeDoJogo) {
		this.lblNomeDoJogo = lblNomeDoJogo;
	}

	public ImageView getFinalizarJogo() {
		return finalizarJogo;
	}

	public void setFinalizarJogo(ImageView finalizarJogo) {
		this.finalizarJogo = finalizarJogo;
	}

	public ImageView getSair() {
		return sair;
	}

	public void setSair(ImageView sair) {
		this.sair = sair;
	}

	public ImageView getMusicaJogo() {
		return musicaJogo;
	}

	public void setMusicaJogo(ImageView musicaJogo) {
		this.musicaJogo = musicaJogo;
	}

	public String getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(String idJogo) {
		this.idJogo = idJogo;
	}

	public String getUserAluno() {
		return userAluno;
	}

	public void setUserAluno(String userAluno) {
		this.userAluno = userAluno;
	}

}
