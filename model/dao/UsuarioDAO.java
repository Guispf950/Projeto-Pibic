package model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.UsuarioVO;
import view.TelaCadastro;
import view.TelaRestaurarSenha;

public class UsuarioDAO {
	public boolean cadastrarAluno(UsuarioVO usuario) {
		
		String sql1 = "INSERT INTO usuario (nome, senha, dataNasc, usuario, dicaSenha, sexo) VALUES (?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT id FROM usuario WHERE usuario = (?);";
		PreparedStatement pStatement1 = null;
		PreparedStatement pStatement2 = null;
		 
		Connection conn = null;
		try {

			conn = new Conexao().getConnection();

			pStatement2 = conn.prepareStatement(sql2);
			pStatement2.setString(1, usuario.getUser());
			boolean nome_user_aluno = pStatement2.executeQuery().next(); // verifica se nome de usuario na tabela aluno
																			// já existe

			if (!nome_user_aluno) {

				pStatement1 = conn.prepareStatement(sql1);
				pStatement1.setString(1, usuario.getNome());
				pStatement1.setString(2, usuario.getSenha());
				java.util.Date utilDate = usuario.getDatanasc();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				pStatement1.setDate(3, sqlDate);
				pStatement1.setString(4, usuario.getUser());
				pStatement1.setString(5, usuario.getDicaSenha());
				pStatement1.setString(6,String.valueOf(usuario.getSexo()));
				pStatement1.execute();
				JOptionPane.showMessageDialog(null, "Usuario Cadastrado !");
				return true;

			} else
				JOptionPane.showMessageDialog(null, "Esse nome de usuario já está cadastrado ", "Erro no Cadastro",
						JOptionPane.ERROR_MESSAGE);
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pStatement1 != null) {
					pStatement1.close();
					pStatement2.close();
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
		return false;
 

	}

	public void restaurarSenhaAluno(String nome, String sobrenome, String email, String senha, Date dataNasc,
			String nomeCachorro, String comidaFav, TelaRestaurarSenha tela) {
		String sql1 = "SELECT email FROM aluno WHERE nome = ? AND sobrenome = ? AND dataNasc = ? AND comidaFav = ? AND email = ? ;";
		String sql2 = "UPDATE aluno SET senha = (?) WHERE email = (?);";
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
				// String email_coluna = result.getString("email");

				pStatement2 = conn.prepareStatement(sql2);
				pStatement2.setString(1, senha);
				pStatement2.setString(2, email);
				int i = pStatement2.executeUpdate();
				condicao = i > 0;
				System.out.println(condicao);
				if (condicao) {
					JOptionPane.showMessageDialog(null, "Senha Restaurada !");
					tela.dispose();

				} else
					JOptionPane.showMessageDialog(null, "Dados Incorretos", "Erro na Restauração",
							JOptionPane.ERROR_MESSAGE);
			} else
				new AdministradorDAO().restaurarSenhaAluno(nome, sobrenome, email, senha, dataNasc, nomeCachorro, comidaFav,
						tela);

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
		int idAluno = 0;
		String sql1 = "SELECT id FROM usuario WHERE usuario = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setString(1, user);
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				idAluno = rs1.getInt("id");

			}
			return idAluno;
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	/*public UsuarioVO pegarDadosAlunoComId(int id) {

		String sql1 = "SELECT * FROM aluno WHERE id = (?);";

		PreparedStatement pStatement1 = null;
		ResultSet rs1 = null;

		Connection conn = null;

		try {
			conn = new Conexao().getConnection();
			pStatement1 = conn.prepareStatement(sql1);
			pStatement1.setInt(1, id);
			rs1 = pStatement1.executeQuery();
			if (rs1.next()) {
				String nome = rs1.getString("nome");
				String sobrenome = rs1.getString("sobrenome");
				String email = rs1.getString("email");
				String nomeUser = rs1.getString("usuario");
				String senha = rs1.getString("senha");
				Date dataNasc = rs1.getDate("dataNasc");
				String nomeCachorro = rs1.getString("nomeCachorro");
				String comidaFav = rs1.getString("comidaFav");
				UsuarioVO aluno = new UsuarioVO(nome, sobrenome, email, nomeUser, senha, dataNasc, nomeCachorro,
						comidaFav);
				return aluno;

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;

	} */
}
