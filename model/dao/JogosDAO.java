package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.vo.JogosVO;
import model.vo.QuizVO;

public class JogosDAO {

	public String criarJogo(JogosVO jogo) {

		String sql1 = "SELECT id FROM jogo where id = (?);";
		String sql2 = "INSERT INTO jogo (id, nomeJogo, idAutor, horaCriacao) VALUES (?,?,?,?);";
		String sql3 = "SELECT id FROM administrador WHERE usuario = (?);";
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
			pStatement1.setString(1, jogo.getId());
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				idQuizExiste = true;

			}

			if (!idQuizExiste) {
				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1, jogo.getId());
				pStatement2.setString(2, jogo.getNomeJogo());
				pStatement2.setInt(3, jogo.getIdAutor());
				pStatement2.setTimestamp(4, jogo.getHoraCriacao());
				boolean comandoSucedido = pStatement2.execute();
				if (!comandoSucedido) {

					id_jogo = jogo.getId();
				} else
					JOptionPane.showMessageDialog(null, "Erro ao criar Jogo");
			} else
				JOptionPane.showMessageDialog(null, "ID de jogo já existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_jogo;

	}

	public JogosVO consultarJogo(String id) {
		String sql1 = "SELECT * FROM jogo where id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		JogosVO jogo = new JogosVO();

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				jogo.setId(id);
				jogo.setNomeJogo(rs.getString("nomeJogo"));
				 
			} else
				JOptionPane.showMessageDialog(null, "Esse id de jogo não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogo;

	}

	public void AlterarJogo(JogosVO jogo) {
		String sql1 = "UPDATE jogo SET nomeJogo = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, jogo.getNomeJogo());
			pStatement1.setString(2, jogo.getId());
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

	public void excluirJogo(String id) {
		String sql1 = "DELETE FROM jogo WHERE id = (?); ";
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

	public String pegarUltimoIdJogo() {
		String ultimoId = null;
		String sql1 = "SELECT id FROM jogo ORDER BY horaCriacao DESC LIMIT 1";
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

	 

	

	public List<JogosVO> consultarJogosDeAdm(int idAutor) {
		String sql1 = "SELECT * FROM jogo where idAutor = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		List<JogosVO> listaJogos = new ArrayList<>();

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, idAutor);
			rs = pStatement1.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				String nomeQuiz = rs.getString("nomeQuiz");
				java.sql.Timestamp horaCriacao = rs.getTimestamp("horaCriacao");
				JogosVO quiz = new JogosVO(id, nomeQuiz, idAutor, horaCriacao);

				listaJogos.add(quiz);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaJogos;

	}
}
