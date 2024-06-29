package model.dao;

import java.security.Timestamp; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.vo.QuizQuestaoVO;
import model.vo.QuizVO;
 

public class QuizDAO {

	public String criarQuiz(QuizVO quiz) {

		String sql1 = "SELECT id FROM quiz where id = (?);";
		String sql2 = "INSERT INTO quiz (id, nomeQuiz, idAutor, horaCriacao) VALUES (?,?,?,?);";
		String sql3 = "SELECT id FROM professor WHERE usuario = (?);";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		String id_jogo = null;

		ResultSet rs = null;
		ResultSet rs2 = null;
		Connection conn = null;
		boolean idQuizExiste = false;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, quiz.getId());
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				idQuizExiste = true;

			}

			if (!idQuizExiste) {
				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1, quiz.getId());
				pStatement2.setString(2, quiz.getNomeQuiz());
				pStatement2.setInt(3, quiz.getidAutor());
				pStatement2.setTimestamp(4, quiz.getHoraCriacao());
				boolean comandoSucedido = pStatement2.execute();
				if (!comandoSucedido) {

					id_jogo = quiz.getId();
				} else
					JOptionPane.showMessageDialog(null, "Erro ao criar Quiz");
			} else
				JOptionPane.showMessageDialog(null, "ID de quiz ja existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_jogo;

	}

	public QuizVO consultarQuiz(String id) {
		String sql1 = "SELECT * FROM quiz where id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		QuizVO quiz = new QuizVO();

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				quiz.setId(id);
				quiz.setNomeQuiz(rs.getString("nomeQuiz"));
				quiz.setTempoQuiz(rs.getString("tempoQuiz"));

			} else
				JOptionPane.showMessageDialog(null, "Esse id de Quiz não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quiz;

	}

	public void AlterarQuiz(QuizVO quiz) {
		String sql1 = "UPDATE quiz SET nomeQuiz = (?) , tempoQuiz = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, quiz.getNomeQuiz());
			pStatement1.setString(2, quiz.getTempoQuiz());
			pStatement1.setString(3, quiz.getId());
			int atualizar = pStatement1.executeUpdate();
			if (atualizar > 0) {
				JOptionPane.showMessageDialog(null, "Quiz alterado");

			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirQuiz(String id) {
		String sql1 = "DELETE FROM quiz WHERE id = (?); ";
		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			int excluir = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE, INSERT
														// e retorna o numero de linhas afetadas

			if (excluir > 0) {
				JOptionPane.showMessageDialog(null, "Quiz Excluído");
			} else
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String pegarUltimoIdQuiz() {
		String ultimoId = null;
		String sql1 = "SELECT id FROM quiz ORDER BY horaCriacao DESC LIMIT 1";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getString("id");
			 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ultimoId;

	}

	public String pegarUltimoLoginNomeProfessor(String user) {
		String ultimoId = null;
		String sql1 = "SELECT nome FROM quiz ORDER BY id DESC LIMIT 1";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getString("id");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ultimoId;

	}

	public int totalDeQuestoesQuiz(String id) {
		int totalQuestoes = 0;
		String sql1 = "SELECT COUNT(*) AS total FROM questao WHERE id_quiz = (?);";
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

	public List<QuizVO> consultarQuizAdm(int idAutor) {
		String sql1 = "SELECT * FROM quiz where idAutor = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		List<QuizVO> listaQuiz = new ArrayList<>();
		 

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, idAutor);
			rs = pStatement1.executeQuery();

			 
				while (rs.next()) {
			 
					String id = rs.getString("id");
					String nomeQuiz = rs.getString("nomeQuiz");
					java.sql.Timestamp horaCriacao = rs.getTimestamp("horaCriacao");
					QuizVO quiz = new QuizVO(id, nomeQuiz, idAutor, horaCriacao);
				 
					listaQuiz.add(quiz);
					

				}
		 
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return listaQuiz;

	}

}