package model.dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.vo.FormacaoSilabVO;

public class FormacaoSilabDAO {

	public void criarFormacaoSilab(FormacaoSilabVO formSil) {
		String sql1 = "INSERT INTO formacao_silabica (idAutor, nomeFormSil , horaCriacao) VALUES (?, ? , ?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;

		Connection conn = null;
		try {

			conn = new Conexao().getConnection();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, formSil.getIdAutor());
			pStatement1.setString(2, formSil.getNomeFormSil());
			pStatement1.setTimestamp(3, formSil.getHoraCriacao());
			int LinhasAfetadas = pStatement1.executeUpdate();
			if (LinhasAfetadas != 0) {
				JOptionPane.showMessageDialog(null, "Jogo foi criado");
			} else
				JOptionPane.showMessageDialog(null, "Erro na criacao");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
