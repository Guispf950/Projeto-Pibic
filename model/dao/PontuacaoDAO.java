package model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.vo.PontuacaoVO;
import model.vo.QuizQuestaoVO;

public class PontuacaoDAO {

	public void salvarPontuacao(PontuacaoVO pontuacao) {

		String sql1 = "INSERT INTO pontuacao (id_jogo, idAluno, tempoInicial, tempoFinal, acertos, vezesJogadas) VALUES (?, ?, ?, ?, ?, ?); ";
		  
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
			pStatement1.setString(1, pontuacao.getid_jogo());
			pStatement1.setInt(2, pontuacao.getIdAluno());
			pStatement1.setTimestamp(3, pontuacao.getTempoInicial());
			pStatement1.setTimestamp(4, pontuacao.getTempoFinal());
			pStatement1.setInt(5, pontuacao.getAcertos());
			pStatement1.setInt(6, pontuacao.getVezesJogadas());
			boolean situacao = pStatement1.execute();

			if (!situacao) {
				JOptionPane.showMessageDialog(null, "Sua pontuacao foi salva");

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public int vezesJogadas(int idAluno, String id_jogo) {
		int vezesJogadas = 0;
		String sql1 = "SELECT COUNT(*) as total FROM pontuacao WHERE idAluno = (?) && id_quiz = (?) || id_flash_card = (?) || id_acerte_palavra = (?);";
		
		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, idAluno);
			pStatement1.setString(2, id_jogo);
			pStatement1.setString(3, id_jogo);
			pStatement1.setString(4, id_jogo);
			
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				vezesJogadas = rs1.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vezesJogadas;

	}

	public List<PontuacaoVO> consultarPontuacoesPorIdJogo(String id_jogo) {
		String sql1 = "SELECT * FROM pontuacao p1 WHERE p1.tempoFinal = (SELECT MAX(p2.tempoFinal) FROM pontuacao p2 WHERE p2.idAluno = p1.idAluno AND p2.id_jogo = (?));";
		String sql2 = "SELECT * FROM quiz WHERE id = (?) ;";
		
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		List<PontuacaoVO> pontuacoes = new ArrayList<>();

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
					int idAluno = rs1.getInt("idAluno");
					Timestamp tempoInicial = rs1.getTimestamp("tempoInicial");
					Timestamp tempoFinal = rs1.getTimestamp("tempoFinal");
					int acertos = rs1.getInt("acertos");
					int vezesJogadas = rs1.getInt("vezesJogadas");

					PontuacaoVO pontuacao = new PontuacaoVO(id, id_jogo, idAluno, acertos, tempoInicial, tempoFinal, vezesJogadas);
					pontuacoes.add(pontuacao);

				}
			} else
				JOptionPane.showMessageDialog(null, "Digite um id de quiz valido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pontuacoes;

	}

}
