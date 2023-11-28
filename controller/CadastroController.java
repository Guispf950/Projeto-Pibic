package controller;

import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import model.bo.AlunoBO;
import model.bo.ProfessorBO;
import view.TelaCadastro;

public class CadastroController {

	public void cadastrar(String nome, String sobrenome, String email, String user, String senha, String confirmarSenha,
			 String teste_data, String nomeCachorro, boolean cargo_aluno, boolean cargo_prof,
			String comidaFav, TelaCadastro tela) {
		
		
		if (cargo_aluno == true) {
				new AlunoBO().cadastrarAluno(nome, sobrenome, email, user, senha, confirmarSenha, teste_data, nomeCachorro, comidaFav, tela);
			} else if (cargo_prof == true) {
				new ProfessorBO().cadastrarProfessor(nome, sobrenome, email, user, senha, confirmarSenha, teste_data, nomeCachorro, comidaFav, tela);
			} else if (cargo_aluno == false && cargo_prof == false) {
				JOptionPane.showMessageDialog(null, "Selecione entre Aluno e Professor", "Erro no cadastro",
						JOptionPane.ERROR_MESSAGE);}

		}
	}
 