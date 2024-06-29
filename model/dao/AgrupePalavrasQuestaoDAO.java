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

import javafx.scene.Scene;
import model.vo.AgrupePalavrasQuestaoVO;
import model.vo.AdministradorVO;
 
import view.TelaQuizAlterar;

public class AgrupePalavrasQuestaoDAO {
	
	public void adcionarQuestao(AgrupePalavrasQuestaoVO questao, Scene tela) { // trocar por tela de AgrupePalavras

		String sql1 = "INSERT INTO agrupe_palavras_questao (idAgrupePalavras, grupo, palavra) VALUES (?, ?, ?);";

		PreparedStatement pStatement1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, questao.getIdAgrupePalavras()); 
			pStatement1.setInt(2, questao.getGrupo());
			pStatement1.setString(3, questao.getPalavra());
			boolean teste = pStatement1.execute();
			if (!teste) {
				JOptionPane.showMessageDialog(null, "Questão adcionada");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<AgrupePalavrasQuestaoVO> consultarQuestoes(String id_jogo) {
		String sql1 = "SELECT * FROM agrupe_palavras_questao WHERE idAgrupePalavras = (?);";
		String sql2 = "SELECT * FROM agrupe_palavras WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		List<AgrupePalavrasQuestaoVO> questoes = new ArrayList<>();
		 
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
					int grupo = rs1.getInt("grupo");
					String palavra = rs1.getString("palavra");
					
					AgrupePalavrasQuestaoVO questao = new AgrupePalavrasQuestaoVO(id, id_jogo, palavra, grupo);				
					questoes.add(questao);

				}
			} else
				JOptionPane.showMessageDialog(null, "Digite um id de jogo valido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questoes;

	}

	public AgrupePalavrasQuestaoVO consultarQuestaoId(int id) {

		String sql1 = "SELECT * FROM agrupe_palavras_questao WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		AgrupePalavrasQuestaoVO questao = new AgrupePalavrasQuestaoVO();
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs = pStatement1.executeQuery();

			if (rs.next()) {
				questao.setIdAgrupePalavras(rs.getString("idAgrupePalavras"));
				questao.setGrupo(rs.getInt("grupo"));
				questao.setPalavra(rs.getString("palavra"));
				
			} else
				JOptionPane.showMessageDialog(null, "Digite um ID de pergunta válido");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questao;

	}

	public void alterarQuestao(AgrupePalavrasQuestaoVO questao) {

		String sql1 = "UPDATE agrupe_palavras_questao SET grupo = (?) , palavra = (?) WHERE id = (?);";
		PreparedStatement pStatement1 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, questao.getGrupo());
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

	public void excluirQuestao(int id, TelaQuizAlterar tela) { //trocar TelaQuizAlterar pela tela de CRUD da questoes de AgrupePalavras
		String sql1 = "DELETE FROM agrupe_palavras_questao WHERE id = (?); ";
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

	public boolean visualizarQuestaoTelaMenu(String idJogo, String nomeAluno, Scene tela2) {
		 
		String sql1 = "SELECT grupo, palavra FROM agrupe_palavras_questao WHERE idAgrupePalavras = (?); ";
		String sql2 = "SELECT idAutor, nomeAcertePalavra FROM agrupe_palavras WHERE id = (?);";
		String sql3 = "SELECT COUNT(*) AS total FROM agrupe_palavras_questao WHERE idAgrupePalavras = (?);";
		Date aux = new Date();
		Timestamp tempoInicial = new Timestamp(aux.getTime());
		boolean condicao = false;
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
			pStatement1.setString(1, idJogo);
			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, idJogo);
			pStatement3 = conn.prepareStatement(sql3);
			pStatement3.setString(1, idJogo);

			rs1 = pStatement1.executeQuery();
			 
			if (rs1.next()) {
				
				int grupo = rs1.getInt("grupo");
				String palavra = rs1.getString("palavra");
				rs2 = pStatement2.executeQuery();
				if (rs2.next()) {
					condicao = true;
					String nomeJogo = rs2.getString("nomeAgrupePalavras");
					int idAutor = rs2.getInt("idAutor");
					AdministradorVO professor = new AdministradorDAO().pegarDadosAdministradorComId(idAutor);
					rs3 = pStatement3.executeQuery();

					if (rs3.next()) {
						int totalQuestoes = rs3.getInt("total");
						//TELA DE JOGO AgrupePalavras
						/*TelaAcerteAPalavraAluno tela = new TelaAcerteAPalavraAluno(tempoInicial, idJogo);
						tela.getLblAluno().setText(nomeAluno);
						tela.getLblPontuacao1().setText(String.valueOf(totalQuestoes));
						tela.getLblTema().setText(nomeJogo);
						tela.getLblAutor().setText(professor.getNome());
						tela.getLblCodigo().setText(idJogo);
						tela.setVisible(true);
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
						tela.setVisible(true); */
						//tela2.dispose();

					}
				}

			} else {
				 
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return condicao;
	}
	public int pegarUltimoIdQuestao() {
		int ultimoId = 0;
		String sql1 = "SELECT id FROM agrupe_palavras_questao ORDER BY id DESC LIMIT 1";
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

	

	//IMPLEMENTAR O METODO DE VERIFICAR RESPOSTA DO JOGO AGRUPE PALAVRAS

	/*public boolean verificarResposta(String resposta, String idAgrupePalavras ,int numeroQuestao) {
		String sql1 = "SELECT palavra FROM agrupe_palavras_questao WHERE idAgrupePalavras = ? LIMIT ? OFFSET ?";

		PreparedStatement pStatement1 = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean acertou = false;
		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, idAgrupePalavras);
			pStatement1.setInt(2, 1);
			pStatement1.setInt(3, numeroQuestao - 1);
			rs = pStatement1.executeQuery();
			if (rs.next()) {
				String respostaVerdadeira = rs.getString("palavra");
				System.out.println(respostaVerdadeira);
				if (resposta.equals(respostaVerdadeira)) {
					acertou = true;

				}
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acertou;

	} */
	

}