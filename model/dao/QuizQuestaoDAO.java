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
import controller.FlashCardQuestaoController;
import controller.PontuacaoController;
import controller.QuizViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.bo.FlashCardQuestaoBO;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;
import view.TelaAcerteAPalavraAluno;
import view.TelaDeMenuAluno;
import view.TelaQuizAlterar;
import view.TelaQuizAluno;

public class QuizQuestaoDAO {

	public void adcionarQuestao(QuizQuestaoVO questao, int numeroQuestao) {

		String sql1 = "SELECT id FROM quiz_questao WHERE id_quiz = ? LIMIT 1 OFFSET ?";
		String sql2 = "INSERT INTO quiz_questao (id_quiz, pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta) VALUES (?, ?, ?, ?, ?, ?, ?);";
		String sql3 = "UPDATE quiz_questao SET pergunta = (?), alternativaA = (?), alternativaB = (?), alternativaC = (?) , alternativaD = (?), resposta = (?) WHERE id = (?)";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;

		ResultSet rs1 = null;
		numeroQuestao--; // necessario subtrair um, pois no banco vai ser pulado essa quantidade de
							// linhas

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, questao.getId_quiz());
			pStatement1.setInt(2, numeroQuestao);

			rs1 = pStatement1.executeQuery();

			if (rs1.next()) {
				int idQuestao = rs1.getInt("id");
				pStatement3 = conn.prepareStatement(sql3);
				pStatement3.setString(1, questao.getPergunta());
				pStatement3.setString(2, questao.getAlternativaA());
				pStatement3.setString(3, questao.getAlternativaB());
				pStatement3.setString(4, questao.getAlternativaC());
				pStatement3.setString(5, questao.getAlternativaD());
				pStatement3.setString(6, questao.getResposta());
				pStatement3.setInt(7, idQuestao);
				int condicao = pStatement3.executeUpdate();

				if (condicao > 0) {
					JOptionPane.showMessageDialog(null, "Questão alterada");

				}
			} else {
				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1, questao.getId_quiz());
				pStatement2.setString(2, questao.getPergunta());
				pStatement2.setString(3, questao.getAlternativaA());
				pStatement2.setString(4, questao.getAlternativaB());
				pStatement2.setString(5, questao.getAlternativaC());
				pStatement2.setString(6, questao.getAlternativaD());
				pStatement2.setString(7, questao.getResposta());
				int condicao = pStatement2.executeUpdate();
				if (condicao > 0) {
					JOptionPane.showMessageDialog(null, "Nova questao adcionada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

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

	public void alterarQuestoes(QuizQuestaoVO questao) {

		String sql1 = "UPDATE quiz_questao SET pergunta = (?) , alternativaA = (?), alternativaB = (?), alternativaC = (?) , alternativaD = (?), resposta = (?)"
				+ "WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, questao.getPergunta());
			pStatement1.setString(2, questao.getAlternativaA());
			pStatement1.setString(3, questao.getAlternativaB());
			pStatement1.setString(4, questao.getAlternativaC());
			pStatement1.setString(5, questao.getAlternativaD());
			pStatement1.setString(6, questao.getResposta());
			pStatement1.setInt(7, questao.getId());
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

	public void excluirQuestao(int id, TelaQuizAlterar tela) {
		String sql1 = "DELETE FROM quiz_questao WHERE id = (?); ";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			int excluir = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE, INSERT
														// e retorna o numero de linhas afetadas

			if (excluir > 0) {
				tela.getTxtPergunta().setText("");
				tela.getTxtAlternativaA().setText("");
				tela.getTxtAlternativaB().setText("");
				tela.getTxtAlternativaC().setText("");
				tela.getTxtAlternativaD().setText("");

				if (tela.getAlternativa1jCheckBox().isSelected()) {
					tela.getAlternativa2jCheckBox2().setSelected(false);
					tela.getAlternativa3jCheckBox3().setSelected(false);
					tela.getjCheckBox4().setSelected(false);
				}
				if (tela.getAlternativa2jCheckBox2().isSelected()) {
					tela.getAlternativa1jCheckBox().setSelected(false);
					tela.getAlternativa3jCheckBox3().setSelected(false);
					tela.getjCheckBox4().setSelected(false);
				}
				if (tela.getAlternativa3jCheckBox3().isSelected()) {
					tela.getAlternativa1jCheckBox().setSelected(false);
					tela.getAlternativa2jCheckBox2().setSelected(false);
					tela.getjCheckBox4().setSelected(false);
				}
				if (tela.getjCheckBox4().isSelected()) {
					tela.getAlternativa1jCheckBox().setSelected(false);
					tela.getAlternativa2jCheckBox2().setSelected(false);
					tela.getAlternativa3jCheckBox3().setSelected(false);
				}
				JOptionPane.showMessageDialog(null, "Questão Excluída");
			} else
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void visualizarQuestaoBotaoContinuar(String id_quiz, int numeroQuestao, TelaQuizAluno tela, int acertos,
			Timestamp tempoInicial) {
		String sql1 = "SELECT COUNT(*) as total FROM quiz_questao WHERE id_quiz = ?";
		String sql2 = "SELECT pergunta, alternativaA, alternativaB, alternativaC, alternativaD FROM quiz_questao WHERE id_quiz = ? LIMIT 1 OFFSET ?; ";
		int totalQuestoes = 0;
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_quiz);

			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				totalQuestoes = rs1.getInt("total");
			}
			if (totalQuestoes == numeroQuestao + 1) {
				tela.getContinuarjButton1().setText("Finalizar");
			}
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, id_quiz);
			pStatement2.setInt(2, numeroQuestao);

			rs2 = pStatement2.executeQuery();

			if (rs2.next()) {
				String pergunta = rs2.getString("pergunta");
				String alternativaA = rs2.getString("alternativaA");
				String alternativaB = rs2.getString("alternativaB");
				String alternativaC = rs2.getString("alternativaC");
				String alternativaD = rs2.getString("alternativaD");
				if (pergunta != null && alternativaA != null && alternativaB != null && alternativaC != null
						&& alternativaD != null) {

					tela.getQuestaoAlunojLabel3().setText(pergunta); // altera o texto das questões
					tela.getAlternativa1jCheckBox1().setText(alternativaA);
					tela.getAlternativa2jCheckBox2().setText(alternativaB);
					tela.getAlternativa3jCheckBox3().setText(alternativaC);
					tela.getAlternativa4jCheckBox4().setText(alternativaD);
					tela.getAlternativa1jCheckBox1().setSelected(false); // desmarcar opções das alternativas
					tela.getAlternativa2jCheckBox2().setSelected(false);
					tela.getAlternativa3jCheckBox3().setSelected(false);
					tela.getAlternativa4jCheckBox4().setSelected(false);

				} else {

				}
			} else {
				TelaDeMenuAluno tela1 = new TelaDeMenuAluno();
			 
				JOptionPane.showMessageDialog(null, "O quiz acabou, sua pontuação foi: " + acertos);
				String nomeAluno = tela.getNomeDoAlunojLabel7().getText().substring(12);
				Date aux = new Date();
				Timestamp tempoFinal = new Timestamp(aux.getTime());
				new PontuacaoController().salvarPontuacao(id_quiz, nomeAluno, acertos, tempoInicial, tempoFinal);
				tela1.getjLabel12().setText(nomeAluno);
				tela1.setVisible(true);
				tela.dispose();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void visualizarProximaQuestao(int id, TelaQuizAlterar tela) {
		int idProximaPergunta = id + 1;
		String sql1 = "SELECT pergunta, alternativaA, alternativaB, alternativaC, alternativaD FROM quiz_questao WHERE id = (?); ";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, idProximaPergunta);
			 

			rs = pStatement1.executeQuery();

			if (rs.next()) {
				String pergunta = rs.getString("pergunta");
				String alternativaA = rs.getString("alternativaA");
				String alternativaB = rs.getString("alternativaB");
				String alternativaC = rs.getString("alternativaC");
				String alternativaD = rs.getString("alternativaD");
				tela.getTxtPergunta().setText(pergunta);
				tela.getTxtAlternativaA().setText(alternativaA);
				tela.getTxtAlternativaB().setText(alternativaB);
				tela.getTxtAlternativaC().setText(alternativaC);
				tela.getTxtAlternativaD().setText(alternativaD);
				tela.getLblNumeroQuestao().setText(String.valueOf(idProximaPergunta));
			} else
				JOptionPane.showMessageDialog(null, "Não há mais questões");

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void visualizarQuestaoTelaMenu(String id_quiz, String nomeAluno, TelaDeMenuAluno tela1) {
		String sql1 = "SELECT pergunta, alternativaA, alternativaB, alternativaC, alternativaD FROM quiz_questao WHERE id_quiz = ?; ";
		String sql2 = "SELECT nomeQuiz FROM quiz WHERE id = (?);";
		String sql3 = "SELECT COUNT(*) AS total FROM quiz_questao WHERE id_quiz = (?);";
		Date aux = new Date();
		Timestamp tempoInicial = new Timestamp(aux.getTime());

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
					String tempoQuiz = rs2.getString("tempoQuiz");
					rs3 = pStatement3.executeQuery();

					if (rs3.next()) {
						int totalQuestoes = rs3.getInt("total");
						TelaQuizAluno tela = new TelaQuizAluno(tempoInicial);
						tela.getNomeDoAlunojLabel7().setText("Nome Aluno: " + nomeAluno);
						tela.getTotaldeQuestoesjLabel9().setText("Total de questoes: " + String.valueOf(totalQuestoes));
						tela.getNomeDoQuizjLabel6().setText(id_quiz);
						tela.getTotalDeTempojLabel8().setText("Tempo do Quiz: " + tempoQuiz);
						tela.getQuestaoAlunojLabel3().setText(pergunta);
						tela.getAlternativa1jCheckBox1().setText(alternativaA);
						tela.getAlternativa2jCheckBox2().setText(alternativaB);
						tela.getAlternativa3jCheckBox3().setText(alternativaC);
						tela.getAlternativa4jCheckBox4().setText(alternativaD);
						tela.setVisible(true);
						tela1.dispose();

					}
				}

			} else {

				 new TelaAcerteAPalavraAluno(tempoInicial, id_quiz);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
