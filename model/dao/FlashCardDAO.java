package model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.FlashCardVO;
import model.vo.QuizQuestaoVO;
import model.vo.QuizVO;
import view.FlashCardCriar;
 

public class FlashCardDAO {

	public void criarFlashCard(FlashCardVO flashCard) {
		String sql1 = "INSERT INTO flash_card (id, idAutor, nomeFlashCard, horaCriacao) VALUES (?,?,?,?);";
		PreparedStatement pStatement1 = null; 
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, flashCard.getId());
			pStatement1.setInt(2, flashCard.getIdAutor());
			pStatement1.setString(3, flashCard.getnomeFlashCard());
			pStatement1.setTimestamp(4, flashCard.getHoraCriacao());

			int linhasAfetadas = pStatement1.executeUpdate();
			if (linhasAfetadas != 0) {
				JOptionPane.showMessageDialog(null, "Jogo criado, codigo é : " + flashCard.getId());
				new FlashCardCriar(flashCard.getId()).setVisible(true);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	public void alterarNomeFlashCard(String novoNome, String id) {

		String sql1 = "UPDATE flash_card SET nomeFlashCard = (?) WHERE id = (?);";
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
				JOptionPane.showMessageDialog(null, "Flash Card alterado");

			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void excluirFlashCard(String id) {
		String sql1 = "DELETE FROM flash_card WHERE id = (?); ";
		PreparedStatement pStatement1 = null;
		
		Connection conn = null;
		

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			int excluir = pStatement1.executeUpdate(); // executeUpdate() usado para operações de UPDATE, DELETE, INSERT e retorna o numero de linhas afetadas
			
			if(excluir>0) {
				JOptionPane.showMessageDialog(null, "Flash Excluído");
			} else 
				JOptionPane.showMessageDialog(null, "Esse id não existe");

		 

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public FlashCardVO consultarFlashCard(String id) {
		String sql1 = "SELECT * FROM flash_card WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		FlashCardVO flashCard = new FlashCardVO();

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, id);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				 flashCard.setId(id);
				 flashCard.setIdAutor(rs.getInt("idAutor"));
				 flashCard.setnomeFlashCard(rs.getString("nomeFlashCard"));
				 flashCard.setHoraCriacao(rs.getTimestamp("horaCriacao"));
				 
				 

			} else
				JOptionPane.showMessageDialog(null, "Esse id de Quiz não existe");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flashCard;

	}
	
	
		 
}
