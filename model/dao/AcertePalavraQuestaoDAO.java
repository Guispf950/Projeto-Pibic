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
import controller.AcerteAPalavraController;
import controller.FlashCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.vo.AcertePalavraQuestaoVO;
import model.vo.AdministradorVO;
 
public class AcertePalavraQuestaoDAO {

	/*public void adcionarQuestao(AcertePalavraQuestaoVO questao, CriarAcerteAPalavra tela) {

		String sql1 = "INSERT INTO acerte_palavra_questoes (id_AcertePalavra, dica, palavra) VALUES (?, ?, ?);";

		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, questao.getIdAcertePalavra()); 
			pStatement1.setString(2, questao.getDica());
			pStatement1.setString(3, questao.getPalavra());
			boolean teste = pStatement1.execute();
			if (!teste) {
				JOptionPane.showMessageDialog(null, "Questão adcionada");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} */

	public List<AcertePalavraQuestaoVO> consultarQuestoes(String id_jogo) {
		String sql1 = "SELECT * FROM acerte_palavra_questao WHERE id_AcertePalavra = (?);";
		String sql2 = "SELECT * FROM acerte_palavra WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		List<AcertePalavraQuestaoVO> questoes = new ArrayList<>();

		try {
			conn = new Conexao().getConnection();
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, id_jogo);
			rs2 = pStatement2.executeQuery();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_jogo);
			rs1 = pStatement1.executeQuery();

			if (rs2.next()) {
				while (rs1.next()) {
					int id = rs1.getInt("id");
					String dica = rs1.getString("dica");
					String palavra = rs1.getString("palavra");
					
					AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO(id, id_jogo, dica, palavra);				
					questoes.add(questao);

				}
			} else
				JOptionPane.showMessageDialog(null, "Digite um id de jogo valido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questoes;

	}

	public AcertePalavraQuestaoVO consultarQuestaoId(int id) {

		String sql1 = "SELECT * FROM acerte_palavra_questoes WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO ();
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				questao.setIdAcertePalavra(rs.getString("id_AcertePalavra"));
				questao.setDica(rs.getString("dica"));
				questao.setPalavra(rs.getString("palavra"));
				
			} else
				JOptionPane.showMessageDialog(null, "Digite um ID de pergunta válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao;

	}

	public void alterarQuestao(AcertePalavraQuestaoVO questao) {

		String sql1 = "UPDATE acerte_palavra_questoes SET dica = (?) , palavra = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, questao.getDica());
			pStatement1.setString(2, questao.getPalavra());
			pStatement1.setInt(3, questao.getId());
			
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

	 

	public void visualizarQuestaoTelaMenu(String id_quiz, String nomeAluno, Scene tela2) {
		 
		String sql1 = "SELECT dica, palavra FROM acerte_palavra_questao WHERE id_AcertePalavra = ?; ";
		String sql2 = "SELECT idAutor, nomeAcertePalavra FROM acerte_palavra WHERE id = (?);";
		String sql3 = "SELECT COUNT(*) AS total FROM  acerte_palavra_questao WHERE id_AcertePalavra = (?);";
		 
		 
		 
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
				String dica = rs1.getString("dica");
				String palavra = rs1.getString("palavra");
				rs2 = pStatement2.executeQuery();
				
				if (rs2.next()) {
					String nomeQuiz = rs2.getString("nomeAcertePalavra");
					int idAutor = rs2.getInt("idAutor");
					rs3 = pStatement3.executeQuery();

					if (rs3.next()) {
						int totalQuestoes = rs3.getInt("total");
						
						try {

							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AcerteAPalavraView.fxml"));
							Parent parent = loader.load();
							AcerteAPalavraController quizAlterarQuestao = loader.getController();

							quizAlterarQuestao.setId_jogo(id_quiz);
							quizAlterarQuestao.setUserAluno(nomeAluno);
							

							quizAlterarQuestao.initialize(null, null);

							Scene scene = new Scene(parent);
							// Main.getStage().setMaximized(false);

							Main.getStage().setScene(scene);
							Main.getStage().setFullScreen(true);
						} catch (IOException e) {
							e.printStackTrace();
						}
						/*
						System.out.println(idJogo);
						TelaAcerteAPalavraAluno tela = new TelaAcerteAPalavraAluno(tempoInicial, idJogo);
						tela.getLblAluno().setText(nomeAluno);
						tela.getLblPontuacao1().setText(String.valueOf(totalQuestoes));
						tela.getLblTema().setText(nomeQuiz);
						tela.getLblAutor().setText(professor.getNome());
						tela.getLblCodigo().setText(id_quiz);
						tela.setVisible(true);
						//tela2.dispose();*/

					}
				}

			} else {

				JOptionPane.showMessageDialog(null, "Digite um ID de jogo válido");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int pegarUltimoIdQuestao() {
		int ultimoId = 0;
		String sql1 = "SELECT id FROM acerte_palavra_questoes ORDER BY id DESC LIMIT 1";
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

	

	

	public boolean verificarResposta(String resposta, String id_AcertePalavra ,int numeroQuestao) {
		String sql1 = "SELECT palavra FROM acerte_palavra_questao WHERE id_AcertePalavra = ? LIMIT ? OFFSET ?";
		 
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean acertou = false;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id_AcertePalavra);
			pStatement1.setInt(2, 1);
			pStatement1.setInt(3, numeroQuestao);
			rs = pStatement1.  executeQuery();
			if (rs.next()) {
				String respostaVerdadeira = rs.getString("palavra");				
				if (resposta.equals(respostaVerdadeira.toUpperCase())) {
					acertou = true;

				}
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertou;

	}

	

}
