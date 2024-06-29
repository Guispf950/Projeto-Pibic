package model.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.dao.UsuarioDAO;
import model.dao.Conexao;
import model.dao.AdministradorDAO;
import model.vo.UsuarioVO;
import model.vo.AdministradorVO;
import view.TelaCadastro;
import view.TelaRestaurarSenha;

public class ProfessorBO {

	public void cadastrarProfessor(String nome, String sobrenome, String email, String user, String senha,
			String confirmaSenha, String teste_data, String nomeCachorro, String comidaFav, TelaCadastro tela) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNasc = null;
		if (!nome.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty() && !user.isEmpty() && !senha.isEmpty()
				&& !confirmaSenha.isEmpty() && !comidaFav.isEmpty() && !teste_data.isEmpty()) {
			if (senha.equals(confirmaSenha)) {
				if (teste_data.length() == 10 && teste_data.charAt(2) == '/' && teste_data.charAt(5) == '/') {
					try {
						dataNasc = sdf.parse(teste_data);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					AdministradorVO professor = new AdministradorVO(nome, sobrenome, email, user, senha, dataNasc, nomeCachorro,
							comidaFav);
					new AdministradorDAO().cadastrarProfessor(professor, tela);
				} else
					JOptionPane.showMessageDialog(null, "Digite a data no formato dd/MM/yyyy", "Erro no Cadastro",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Senha e Confirma senha diferentes !");
		} else
			JOptionPane.showMessageDialog(null, "Preencha todos os campos !");

	}

	
}