package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.vo.FormacaoSilabVO;
import model.vo.FormacaoSilabicaQuestaoVO;

public class FormacaoSilabicaQuestaoDAO {

	public void adcionarPalavra(FormacaoSilabicaQuestaoVO palavra) {
		String sql1 = "INSERT INTO formacao_silabica_questao (idFormacaoSilabica, palavra) VALUES (?, ?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;

		Connection conn = null;
		try {

			conn = new Conexao().getConnection();

			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, palavra.getIdFormacaoSilabica());
			pStatement1.setString(2, palavra.getPalavra());

			int LinhasAfetadas = pStatement1.executeUpdate();
			if (LinhasAfetadas != 0) {
				JOptionPane.showMessageDialog(null, "Palavra foi adcionada ao jogo");
			} else
				JOptionPane.showMessageDialog(null, "Erro ao adcionar palavra");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
