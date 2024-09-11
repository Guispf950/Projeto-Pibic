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
import controller.PontuacaoController;
import controller.QuizViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.bo.FlashCardQuestaoBO;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;
 
public class QuizQuestaoDAO {

	 
	public List<QuizQuestaoVO> consultarQuestoes(String id_quiz) {
		String sql1 = "SELECT * FROM quiz_questao WHERE id_quiz = (?);";
		String sql2 = "SELECT * FROM quiz WHERE id = (?) ;";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		List<QuizQuestaoVO> questoes = new ArrayList<>();

		try {
			conn = new Conexao().getConnection();
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, id_quiz);
			rs2 = pStatement2.executeQuery();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_quiz);
			rs1 = pStatement1.executeQuery();

			if (rs2.next()) {
				while (rs1.next()) {
					int id = rs1.getInt("id");
					String pergunta = rs1.getString("pergunta");
					String alternativaA = rs1.getString("alternativaA");
					String alternativaB = rs1.getString("alternativaB");
					String alternativaC = rs1.getString("alternativaC");
					String alternativaD = rs1.getString("alternativaD");
					String resposta = rs1.getString("resposta");
					QuizQuestaoVO questao = new QuizQuestaoVO(id, id_quiz, pergunta, alternativaA, alternativaB,
							alternativaC, alternativaD, resposta);
					questoes.add(questao);

					 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questoes;

	}

	public QuizQuestaoVO consultarQuestaoId(int id) {

		String sql1 = "SELECT * FROM quiz_questao WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		QuizQuestaoVO questao = new QuizQuestaoVO();
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				questao.setId_quiz(rs.getString("id_quiz"));
				questao.setPergunta(rs.getString("pergunta"));
				questao.setAlternativaA(rs.getString("alternativaA"));
				questao.setAlternativaB(rs.getString("alternativaB"));
				questao.setAlternativaC(rs.getString("alternativaC"));
				questao.setAlternativaD(rs.getString("alternativaD"));
				questao.setResposta(rs.getString("resposta"));

			} else
				JOptionPane.showMessageDialog(null, "Digite um ID de pergunta válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao;

	}

	 

	 
	public int totalDeQuestoesQuiz(String id) {
		int totalQuestoes = 0;
		String sql1 = "SELECT COUNT(*) AS total FROM quiz_questao WHERE idJogo = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();

			if (rs.next()) {

				totalQuestoes = rs.getInt("total");

			} else
				JOptionPane.showMessageDialog(null, "Digite um ID de quiz válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalQuestoes;

	}
	public int pegarUltimoIdQuestao() {
		int ultimoId = 0;
		String sql1 = "SELECT id FROM quiz_questao ORDER BY id DESC LIMIT 1";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getInt("id");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ultimoId;

	}

	 

	public void visualizarQuestaoTelaMenu(String id_quiz, String nomeAluno, Scene tela) {
		String sql1 = "SELECT pergunta, alternativaA, alternativaB, alternativaC, alternativaD FROM quiz_questao WHERE id_quiz = (?); ";
		String sql2 = "SELECT nomeQuiz FROM quiz WHERE id = (?);";
		String sql3 = "SELECT COUNT(*) AS total FROM quiz_questao WHERE id_quiz = (?);";
		 
		 
		 
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
			pStatement1.setString(1, id_quiz);
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, id_quiz);
			pStatement3 = conn.prepareStatement(sql3);
			pStatement3.setString(1, id_quiz);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {

				String pergunta = rs1.getString("pergunta");
				String alternativaA = rs1.getString("alternativaA");
				String alternativaB = rs1.getString("alternativaB");
				String alternativaC = rs1.getString("alternativaC");
				String alternativaD = rs1.getString("alternativaD");
				rs2 = pStatement2.executeQuery();
				 
				
				if (rs2.next()) {
					String nomeQuiz = rs2.getString("nomeQuiz");
					rs3 = pStatement3.executeQuery();
					 

					if (rs3.next()) {
						 
						try {
							 
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizView.fxml"));
							Parent parent = loader.load();
							QuizViewController quizAlterarQuestao = loader.getController();
							
							quizAlterarQuestao.setIdJogo(id_quiz);
							quizAlterarQuestao.setUserAluno(nomeAluno);
	
							QuizQuestaoVO questao1 = new QuizQuestaoVO();
							questao1 = new QuizQuestaoBO().primeiraQuestaoQuiz(id_quiz);
							quizAlterarQuestao.getLblTituloQuiz().setText(nomeQuiz);
							quizAlterarQuestao.getLblPergunta().setText(pergunta);
							quizAlterarQuestao.getLblAlternativaA().setText(questao1.getAlternativaA());
							quizAlterarQuestao.getLblAlternativaB().setText(questao1.getAlternativaB());
							quizAlterarQuestao.getLblAlternativaC().setText(questao1.getAlternativaC());
							quizAlterarQuestao.getLblAlternativaD().setText(questao1.getAlternativaD());
							List<QuizQuestaoVO> questoes = new QuizQuestaoDAO().consultarQuestoes(id_quiz);
							quizAlterarQuestao.getLblQuestao().setText("1/" + Integer.toString(questoes.size()));
							
							quizAlterarQuestao.initialize(null, null);

							Scene scene = new Scene(parent);
							//Main.getStage().setMaximized(false);
							
							Main.getStage().setScene(scene);
							Main.getStage().setFullScreen(true);
						} catch (IOException e) {
							e.printStackTrace();
						}

						

					}
				} 

			} else {
				 
		 
				new FlashCardQuestaoDAO().visualizarQuestaoTelaMenu(id_quiz, nomeAluno, tela);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verificarResposta(String resposta, String id_quiz, int acertos, int numeroQuestao) {
		String sql1 = "SELECT resposta FROM quiz_questao WHERE id_quiz = ? LIMIT ? OFFSET ?";
		
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean acertou = false;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_quiz);
			pStatement1.setInt(2, 1);
			pStatement1.setInt(3, numeroQuestao - 1);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				String respostaVerdadeira = rs.getString("resposta");
				 
				if (resposta.equals(respostaVerdadeira)) {
					acertou = true;

				}
			}
			if (acertou) {
				JOptionPane.showMessageDialog(null, "Você acertou ");
			} else
				JOptionPane.showMessageDialog(null, "Você errou !");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertou;

	}

	 

	public int totalQuestaoQuiz(String id_quiz) {
		String sql1 = "SELECT COUNT(*) as total FROM quiz_questao WHERE id_quiz = ?";
		int totalQuestoes = 0;
		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_quiz);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				totalQuestoes = rs1.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalQuestoes;
	}

	public QuizQuestaoVO visualizarQuestaoAnterior(String id_quiz, int numeroQuestao) {
		String sql1 = "SELECT id, pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta FROM quiz_questao WHERE id_quiz = ? LIMIT 1 OFFSET ?; ";
		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		QuizQuestaoVO questao = null;
		try {
			numeroQuestao--; // necessario subtrair um
			 
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_quiz);
			pStatement1.setInt(2, numeroQuestao);
			rs1 = pStatement1.executeQuery();

			if (rs1.next()) {
			 
				int idQuestao = rs1.getInt("id");
				String pergunta = rs1.getString("pergunta");
				String alternativaA = rs1.getString("alternativaA");
				String alternativaB = rs1.getString("alternativaB");
				String alternativaC = rs1.getString("alternativaC");
				String alternativaD = rs1.getString("alternativaD");
				String resposta = rs1.getString("resposta");
				questao = new QuizQuestaoVO(idQuestao, id_quiz, pergunta, alternativaA, alternativaB, alternativaC,
						alternativaD, resposta);
				return questao;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao;
	}
	public QuizQuestaoVO visualizarProximaQuestao(String id_quiz, int numeroQuestao) {
		String sql1 = "SELECT id, pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta FROM quiz_questao WHERE id_quiz = ? LIMIT 1 OFFSET ?; ";
		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		QuizQuestaoVO questao = null;
		try {
		
		 
		 
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			 
			pStatement1.setString(1, id_quiz);
			pStatement1.setInt(2, numeroQuestao);
			rs1 = pStatement1.executeQuery();

			if (rs1.next()) {
			 
				int idQuestao = rs1.getInt("id");
				String pergunta = rs1.getString("pergunta");
				String alternativaA = rs1.getString("alternativaA");
				String alternativaB = rs1.getString("alternativaB");
				String alternativaC = rs1.getString("alternativaC");
				String alternativaD = rs1.getString("alternativaD");
				String resposta = rs1.getString("resposta");
				 
				questao = new QuizQuestaoVO(idQuestao, id_quiz, pergunta, alternativaA, alternativaB, alternativaC,
						alternativaD, resposta);
				return questao;
			}  else JOptionPane.showMessageDialog(null, "Você já esta na ultima questao");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao;
	}
	 

	public QuizQuestaoVO primeiraQuestaoQuiz(String id_jogo) {
		String sql1 = "SELECT pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta FROM quiz_questao WHERE id_quiz = ?; ";

		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		QuizQuestaoVO questao1 = null;

		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_jogo);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {

				String pergunta = rs1.getString("pergunta");
				String alternativaA = rs1.getString("alternativaA");
				String alternativaB = rs1.getString("alternativaB");
				String alternativaC = rs1.getString("alternativaC");
				String alternativaD = rs1.getString("alternativaD");
				String resposta = rs1.getString("resposta");

				questao1 = new QuizQuestaoVO(id_jogo, pergunta, alternativaA, alternativaB, alternativaC, alternativaD,
						resposta);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao1;
	}

}
