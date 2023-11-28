package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.vo.AgrupePalavrasVO;

public class AgrupePalavrasDAO {

	

	public String criarAgrupePalavras(AgrupePalavrasVO agrupePalavras) {
		 
		String sql1 = "SELECT id FROM agrupe_palavras WHERE id = (?);";
		String sql2 = "INSERT INTO agrupe_palavras (id, idAutor,nomeAgrupePalavras, horaCriacao) VALUES (?, ?, ?, ?);";


		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		boolean idJogoExiste = false;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, agrupePalavras.getId());
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				idJogoExiste = true;

			}

			if (!idJogoExiste) {
				pStatement2 = conn.prepareStatement(sql2);
				
				pStatement2.setString(1, agrupePalavras.getId());
				pStatement2.setInt(2, agrupePalavras.getIdAutor());
				pStatement2.setString(3, agrupePalavras.getNomeAgrupePalavra());
				pStatement2.setTimestamp(4, agrupePalavras.getHoraCriacao());
				boolean comandoSucedido = pStatement2.execute();
				if (!comandoSucedido) {
					// tela de adcionar questoes
					JOptionPane.showMessageDialog(null, "Jogo criado");
				} else
					JOptionPane.showMessageDialog(null, "Erro ao criar Jogo");
			} else
				JOptionPane.showMessageDialog(null, "ID de quiz ja existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agrupePalavras.getId();

	}

	public void alterarNomeAgrupePalavras(String novoNome, String id) {

		String sql1 = "UPDATE agrupe_palavras SET nomeAgrupePalavras = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, novoNome);
			pStatement1.setString(2, id);
			int i = pStatement1.executeUpdate();
			boolean condicao = i > 0;
			if (condicao) {
				JOptionPane.showMessageDialog(null, "Jogo alterado");

			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AgrupePalavrasVO consultarAgrupePalavras(String id) {
		String sql1 = "SELECT * FROM agrupe_palavras where id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		AgrupePalavrasVO agrupePalavras = new AgrupePalavrasVO();
		

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				agrupePalavras.setId(id);
				agrupePalavras.setNomeAgrupePalavra(rs.getString("nomeAgrupePalavras"));
				agrupePalavras.setIdAutor(rs.getInt("idAutor"));
				agrupePalavras.setHoraCriacao(rs.getTimestamp("horaCriacao"));

			} else
				JOptionPane.showMessageDialog(null, "Esse id de Jogo não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agrupePalavras ;

	}

	public void excluirAgrupePalavras(String id) {
		String sql1 = "DELETE FROM agrupe_palavras WHERE id = (?); ";
		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			int excluir = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE,
														// INSERT e retorna o numero de linhas afetadas

			if (excluir > 0) {
				JOptionPane.showMessageDialog(null, "Jogo Excluído");
			} else
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
