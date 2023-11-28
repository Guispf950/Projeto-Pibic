package model.bo;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.dao.AlunoDAO;
import model.vo.AlunoVO;
import view.TelaCadastro;
import view.TelaRestaurarSenha;

public class AlunoBO {
	boolean condicao = false;
	public void cadastrarAluno(String nome, String sobrenome, String email, String user, String senha,
			String confirmaSenha, String teste_data, String nomeCachorro, String comidaFav, TelaCadastro tela) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNasc = null;
		 if (nome.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Nome'");
		        
		    } else if (sobrenome.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Sobrenome'");
		    } else if (email.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Email'");
		    } else if (user.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Usuário'");
		    } else if (senha.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Senha'");
		    } else if (confirmaSenha.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Confirmação de Senha'");
		    } else if (comidaFav.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Comida Favorita'");
		    } else if (teste_data.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Data de Nascimento'");
		    } else {
		        if (senha.equals(confirmaSenha)) {
		            if (teste_data.length() == 10 && teste_data.charAt(2) == '/' && teste_data.charAt(5) == '/') {
		                try {
		                    dataNasc = sdf.parse(teste_data);
		                } catch (ParseException e) {
		                    e.printStackTrace();
		                }
		                AlunoVO aluno = new AlunoVO(nome, sobrenome, email, user, senha, dataNasc, nomeCachorro, comidaFav);
		                new AlunoDAO().cadastrarAluno(aluno, tela);
		            } else {
		                JOptionPane.showMessageDialog(null, "Digite a data no formato dd/MM/yyyy", "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Senha e Confirma senha diferentes!");
		        }
		    }
		
	}
	
	
	public void restaurarSenha(String nome, String sobrenome, String email,  String senha, String confirmarSenha,
			 String teste_data, String nomeCachorro, String comidaFav, TelaRestaurarSenha tela) {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNasc = null;
		if (!nome.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty()  && !senha.isEmpty()&&
				!confirmarSenha.isEmpty() && !comidaFav.isEmpty() && !teste_data.isEmpty()) {
			if (senha.equals(confirmarSenha)) {
				if (teste_data.length() == 10 && teste_data.charAt(2) == '/' && teste_data.charAt(5) == '/') {
					try {
						dataNasc = sdf.parse(teste_data);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					new AlunoDAO().restaurarSenhaAluno(nome, sobrenome, email, senha, dataNasc, nomeCachorro, comidaFav, tela);
				
					
				} else
					JOptionPane.showMessageDialog(null, "Digite a data no formato dd/MM/yyyy","Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Senha e Confirma senha diferentes !");
		} else
			JOptionPane.showMessageDialog(null, "Preencha todos os campos !");
		
	
		
		
		
		
		
	}

}