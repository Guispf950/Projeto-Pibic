package model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.vo.AcertePalavraVO;
import model.vo.FlashCardVO;
import model.vo.QuizVO;
 
public class AcertePalavraDAO {

	
	public String criarAcertePalavra(AcertePalavraVO acertePalavra) {
		String id_jogo = null;
		String sql1 = "SELECT id FROM acerte_palavra WHERE id = (?);";
		String sql2 = "INSERT INTO acerte_palavra (id, nomeAcertePalavra, idAutor, horaCriacao) VALUES (?,?,?,?);";
		 
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
			pStatement1.setString(1, acertePalavra.getId());
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				idJogoExiste = true;

			}

			if (!idJogoExiste) {
				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1,acertePalavra.getId());
				pStatement2.setString(2,acertePalavra.getNomeAcertePalavra());
				pStatement2.setInt(3,acertePalavra.getIdAutor());
				pStatement2.setTimestamp(4,acertePalavra.getHoraCriacao());
				boolean comandoSucedido = pStatement2.execute();
				if (!comandoSucedido) {
					//telaa
					
				} else
					JOptionPane.showMessageDialog(null, "Erro ao criar Jogo");
			} else
				JOptionPane.showMessageDialog(null, "ID de quiz ja existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertePalavra.getId();

	}
	
	public void alterarNomeAcertePalavra(String novoNome, String id) {

		String sql1 = "UPDATE acerte_palavra SET nomeAcertePalavra = (?) WHERE id = (?);";
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

	public AcertePalavraVO consultarAcertePalavra(String id) {
		String sql1 = "SELECT * FROM acerte_palavra where id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		AcertePalavraVO acertePalavra= new AcertePalavraVO();

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				acertePalavra.setId(id);
				acertePalavra.setNomeAcertePalavra(rs.getString("nomeAcertePalavra"));
				acertePalavra.setIdAutor(rs.getInt("idAutor"));
				acertePalavra.setHoraCriacao(rs.getTimestamp("horaCriacao"));

			} else
				JOptionPane.showMessageDialog(null, "Esse id de Quiz não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertePalavra;

	}
 	
	 
	
	

	public void excluirAcertePalavra(String id) {
		String sql1 = "DELETE FROM acerte_palavra WHERE id = (?); ";
		PreparedStatement pStatement1 = null;
		
		Connection conn = null;
		

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			int excluir = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE, INSERT e retorna o numero de linhas afetadas
			
			if(excluir>0) {
				JOptionPane.showMessageDialog(null, "Jogo Excluído");
			} else 
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		 

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	 
}
