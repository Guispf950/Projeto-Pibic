package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;
import controller.FlashCardController;
import controller.FlashCardCriarController;
import controller.PontuacaoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.vo.FlashCardQuestaoVO;
import view.CriarFlashCar;
import view.FlashCardAluno;
import view.FlashCardCriar;
import view.TelaDeMenuAluno;
import view.TelaQuizAlterar;

public class FlashCardQuestaoDAO {

	public void adcionarQuestao(FlashCardQuestaoVO frase, FlashCardCriar tela) {
		String sql1 = "INSERT INTO flash_card_questao (idFlashCard, frase, condicao, explicacao) VALUES (?, ?, ?, ?);";

		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, frase.getIdFlashCard());
			pStatement1.setString(2, frase.getFrase());
			pStatement1.setInt(3, frase.getCondicao());
			pStatement1.setString(4, frase.getExplicacao());
			int linhasAfetadas = pStatement1.executeUpdate();
			if (linhasAfetadas != 0) {
				JOptionPane.showMessageDialog(null, "Pergunta Adcionada");
			 tela.getQuestaoProfInserirjEditorPane1().setText("");
			 tela.getBreveExplicacaojEditorPane2().setText("");
			 tela.getFalsojCheckBox1().setSelected(false);
			 tela.getVerdadeirojCheckBox2().setSelected(false);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<FlashCardQuestaoVO> consultarQuestoes(String id_flashCard) {
		String sql1 = "SELECT * FROM flash_card_questao WHERE idFlashCard = (?);";
		String sql2 = "SELECT * FROM flash_card WHERE id = (?) ;";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		List<FlashCardQuestaoVO> frases = new ArrayList<>();

		try {
			conn = new Conexao().getConnection();
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, id_flashCard);
			rs2 = pStatement2.executeQuery();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_flashCard);
			rs1 = pStatement1.executeQuery();

			if (rs2.next()) {
				while (rs1.next()) {
					int id = rs1.getInt("id");
					String fraseTexto = rs1.getString("frase");
					int condicao = rs1.getInt("condicao");
					String explicacao = rs1.getString("explicacao");
					FlashCardQuestaoVO frase = new FlashCardQuestaoVO(id, id_flashCard, fraseTexto, condicao,
							explicacao);
					frases.add(frase);

				}
			} else
				JOptionPane.showMessageDialog(null, "Digite um id válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return frases;

	}

	public FlashCardQuestaoVO consultarQuestaoId(int id) {

		String sql1 = "SELECT * FROM flash_card_questao WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		FlashCardQuestaoVO frase = new FlashCardQuestaoVO();
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				frase.setId(id);
				frase.setIdFlashCard(rs.getString("idFlashCard"));
				frase.setFrase(rs.getString("frase"));
				frase.setCondicao(rs.getInt("condicao"));
				frase.setExplicacao(rs.getString("explicacao"));

			} else
				JOptionPane.showMessageDialog(null, "Digite um ID de frase válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return frase;

	}

	public void alterarQuestao(FlashCardQuestaoVO frase) {

		String sql1 = "UPDATE frase SET frase = (?) , condicao = (?), explicacao = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, frase.getFrase());
			pStatement1.setInt(2, frase.getCondicao());
			pStatement1.setString(3, frase.getExplicacao());
			pStatement1.setInt(4, frase.getId());
			int i = pStatement1.executeUpdate();
			boolean condicao = i > 0;
			if (condicao) {
				JOptionPane.showMessageDialog(null, "Questão alterada");

			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirQuestao(int id, TelaQuizAlterar tela) { // nao tenho tela de alterar questoes do flash card, esse
																// parametro de tela sera substituido pela tela
		String sql1 = "DELETE FROM flash_card_questao WHERE id = (?); ";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			int linhasAfetadas = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE,
																// INSERT

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "Questão Excluída");
			} else
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void visualizarFlashCardBotaoContinuar(String idFlashCard, int numeroQuestao, int acertos) {
		String sql1 = "SELECT COUNT(*) as total FROM flash_card_questao WHERE idFlashCard = ?";
		String sql2 = "SELECT frase, condicao, explicacao FROM flash_card_questao WHERE idFlashCard = ? LIMIT 1 OFFSET ?; ";
		int totalQuestoes = 0;
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idFlashCard);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				totalQuestoes = rs1.getInt("total");
			}
			if (totalQuestoes == numeroQuestao + 1) {

			}
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, idFlashCard);
			pStatement2.setInt(2, numeroQuestao);

			rs2 = pStatement2.executeQuery();

			if (rs2.next()) {
				String frase = rs2.getString("frase");
				String explicacao = rs2.getString("explicacao");
				int condicao = rs2.getInt("condicao");
				if (frase != null && explicacao != null) {

					

				} else {

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void visualizarQuestaoTelaMenu(String idFlashCard, String nomeAluno, Scene tela) {
		String sql1 = "SELECT * FROM flash_card_questao WHERE idFlashCard = ?; ";
		String sql2 = "SELECT nomeFlashCard FROM flash_card WHERE id = (?);";
		String sql3 = "SELECT COUNT(*) AS total FROM flash_card_questao WHERE idFlashCard = (?);";
		 
		 

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idFlashCard);
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, idFlashCard);
			pStatement3 = conn.prepareStatement(sql3);
			pStatement3.setString(1, idFlashCard);
			 
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				String frase = rs1.getString("frase");
				int condicao = rs1.getInt("condicao");
				String explicacao = rs1.getString("explicacao");
				rs2 = pStatement2.executeQuery();
				
				if (rs2.next()) {
					String nomeFlashCard = rs2.getString("nomeFlashCard");
					rs3 = pStatement3.executeQuery();

					if (rs3.next()) {
						int totalQuestoes = rs3.getInt("total");
						try {

							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FlashCardView.fxml"));
							Parent parent = loader.load();
							FlashCardController flashcard = loader.getController();

							flashcard.setIdJogo(idFlashCard);
							flashcard.setUserAluno(nomeAluno);

							
							flashcard.getLblNomeDoJogo().setText(nomeFlashCard);
							flashcard.getLblPergunta().setText(frase);
							flashcard.setCondicao(condicao);
							flashcard.setExplicacao(explicacao);
							flashcard.getLblCard().setText("Card 1/" + Integer.toString(totalQuestoes));

							flashcard.initialize(null, null);

							Scene scene = new Scene(parent);
							// Main.getStage().setMaximized(false);

							Main.getStage().setScene(scene);
							Main.getStage().setFullScreen(true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			} else {
				 
				new AcertePalavraQuestaoDAO().visualizarQuestaoTelaMenu(idFlashCard, nomeAluno, tela);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public void finalizarFlashCard(String idFlashCard, FlashCardAluno tela, int acertos, Timestamp tempoInicial) {
		TelaDeMenuAluno tela1 = new TelaDeMenuAluno();
		JOptionPane.showMessageDialog(null, "O jogo acabou, sua pontuação foi: " + acertos);
		String nomeAluno = tela.getLblAutor().getText();
		Date aux = new Date();
		Timestamp tempoFinal = new Timestamp(aux.getTime());
		new PontuacaoController().salvarPontuacao(idFlashCard, nomeAluno, acertos, tempoInicial, tempoFinal);
		tela1.getjLabel12().setText(nomeAluno);
		tela1.setVisible(true);
		tela.dispose();
	} */

	public boolean verificarResposta(int condicao, String idFlashCard, int acertos, int numeroQuestao) {

		String sql1 = "SELECT condicao, explicacao FROM flash_card_questao WHERE idFlashCard = ? LIMIT ? OFFSET ?";
		String explicacao = null;
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean acertou = false;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idFlashCard);
			pStatement1.setInt(2, 1);
			pStatement1.setInt(3, numeroQuestao - 1);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				int condicao1 = rs.getInt("condicao");

				explicacao = rs.getString("explicacao");
				 
				if (condicao1 == condicao) {
					acertou = true;

				}
			}
			if (acertou) {
				if (showConfirmationDialog("Você acertou!")) {
					
				}
				new FlashCardQuestaoDAO().visualizarFlashCardBotaoContinuar(idFlashCard, numeroQuestao, acertos);

			} else
				if (showConfirmationDialog("Você errou!")) {
					
				}
			// coloca a explicacao na tela

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertou;

	}
	
	private boolean showConfirmationDialog(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText(null);
		alert.setContentText(message);

		// Botões do diálogo
		ButtonType buttonTypeOK = new ButtonType("OK");

		alert.getButtonTypes().setAll(buttonTypeOK);
		
		alert.initOwner(Main.getStage());


		// Exibe o diálogo e aguarda a resposta do usuário
		return alert.showAndWait().orElse(ButtonType.CANCEL) == buttonTypeOK;
	}
	public String mudarParaExplicacao(String idFlashCard, int numeroQuestao) {
		String sql1 = "SELECT explicacao FROM flash_card_questao WHERE idFlashCard = ? LIMIT 1 OFFSET ?;";
		String explicacao = null;
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idFlashCard);
			pStatement1.setInt(2, numeroQuestao);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				explicacao = rs1.getString("explicacao");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return explicacao;
	}

	public String mudarParaPergunta(String idFlashCard, int numeroQuestao) {
		String sql1 = "SELECT frase FROM flash_card_questao WHERE idFlashCard = ? LIMIT 1 OFFSET ?;";
		String pergunta = null;
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idFlashCard);
			pStatement1.setInt(2, numeroQuestao);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				pergunta = rs1.getString("frase");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pergunta;
	}
}
