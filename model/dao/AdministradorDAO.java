package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.UsuarioVO;
import model.vo.AdministradorVO;
import view.TelaCadastro;
import view.TelaRestaurarSenha;

public class AdministradorDAO {

	public void cadastrarProfessor(AdministradorVO professor, TelaCadastro tela) {

		String sql1 = "INSERT INTO  professor (nome, sobrenome, email, usuario, senha, dataNasc, nomeCachorro, comidaFav) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT id FROM  professor WHERE usuario = (?);";
		String sql3 = "SELECT id FROM professor WHERE email = (?);";
		String sql4 = "SELECT id FROM aluno WHERE usuario = (?);";
		String sql5 = "SELECT id FROM aluno WHERE email = (?);";

		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		PreparedStatement pStatement3 = null;
		PreparedStatement pStatement4 = null;
		PreparedStatement pStatement5 = null;
		Connection conn = null;
		try {
			conn = new Conexao().getConnection();

			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, professor.getUser());
			boolean nome_user_professor = pStatement2.executeQuery().next();// verifica se nome de usuario na tabela
																			// professor já está cadastrado
			 

			pStatement3 = conn.prepareStatement(sql3);
			pStatement3.setString(1, professor.getEmail());
			boolean email_user_professor = pStatement3.executeQuery().next();// verifica se o email na tabela professor
																				// já está cadastrado

			pStatement4 = conn.prepareStatement(sql4);
			pStatement4.setString(1, professor.getUser());
			boolean nome_user_aluno = pStatement4.executeQuery().next();// verifica se nome de usuario na tabela aluno
																		// já está cadastrado

			pStatement5 = conn.prepareStatement(sql5);
			pStatement5.setString(1, professor.getEmail());
			boolean email_user_aluno = pStatement5.executeQuery().next();// verifica se o email na tabela aluno já está
																			// cadastrado

			if (!nome_user_professor && !nome_user_aluno) {
				if (!email_user_professor && !email_user_aluno) {
					pStatement1 = conn.prepareStatement(sql1);
					pStatement1.setString(1, professor.getNome());
					pStatement1.setString(2, professor.getSobrenome());
					pStatement1.setString(3, professor.getEmail());
					pStatement1.setString(4, professor.getUser());
					pStatement1.setString(5, professor.getSenha());
					java.util.Date utilDate = professor.getDatanasc();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					pStatement1.setDate(6, sqlDate);
					pStatement1.setString(7, professor.getNomeCachorro());
					pStatement1.setString(8, professor.getComidaFav());
					pStatement1.execute();
					JOptionPane.showMessageDialog(null, "Usuario Cadastrado !");
					tela.dispose();
					
				} else
					JOptionPane.showMessageDialog(null, "Email já esta cadastrado ", "Erro no Cadastro",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Esse nome de usuario já está cadastrado ", "Erro no Cadastro",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pStatement1 != null) {
					pStatement1.close();
				}
			} catch (SQLException e) {

				System.out.println("Erro ao fechar Statement");
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("Erro ao fechar conexao");
			}
		}

	}

	public void restaurarSenhaAdministrador(String nome, String sobrenome, String email, String senha, Date dataNasc,
			String nomeCachorro, String comidaFav, TelaRestaurarSenha tela) {
		String sql1 = "SELECT email FROM professor WHERE nome = ? AND sobrenome = ? AND dataNasc = ? AND comidaFav = ? AND email = ? ;";
		String sql2 = "UPDATE professor SET senha = (?) WHERE email = (?);";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, nome);
			pStatement1.setString(2, sobrenome);
			java.util.Date utilDate = dataNasc;
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pStatement1.setDate(3, sqlDate);
			pStatement1.setString(4, comidaFav);
			pStatement1.setString(5, email);
			ResultSet result = pStatement1.executeQuery();
			if (result.next()) {
				boolean condicao = false;
				// String email_teste= result.getString("email");

				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1, senha);
				pStatement2.setString(2, email);
				int i = pStatement2.executeUpdate();
				condicao = i > 0;
				if (condicao) {
					JOptionPane.showMessageDialog(null, "Senha Restaurada !");
					tela.dispose();
				} else
					JOptionPane.showConfirmDialog(null, "Dados incorretos", "Erro ao restaurar senha",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
						JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pStatement1 != null) {
					pStatement1.close();
 
				}
			} catch (SQLException e) {

				System.out.println("Erro ao fechar Statement");
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("Erro ao fechar conexao");
			}

		}

	}

	public int pegarIdComNomeUser(String user) {
		int idAutor = 0;
		 
		String sql1 = "SELECT id FROM administrador WHERE usuario = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1,user);
			rs1 = pStatement1.executeQuery();
			if(rs1.next()) {
				idAutor = rs1.getInt("id");
				
			}
				return idAutor;
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}
	
	public AdministradorVO pegarDadosAdministradorComId(int id) {
		
		 
		String sql1 = "SELECT * FROM administrador WHERE id = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				
				
				String nomeUser = rs1.getString("usuario");
				String senha = rs1.getString("senha");
				String dicaSenha = rs1.getString("dicaSenha");
				AdministradorVO professor = new AdministradorVO(nomeUser, senha, dicaSenha);
				return professor;

			}
			 
		} catch (SQLException e) {
			e.printStackTrace();

		 
		}
		return null;
		
	}
}