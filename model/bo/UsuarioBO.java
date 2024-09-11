package model.bo;

import java.awt.Color;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	 
	public boolean cadastrarAluno(String nome, String senha, String confirmaSenha, String data_Nasc,
			String user, String dicaSenha, char sexo) {
		boolean condicao = false;
		Date dataNasc = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		 if (nome.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Nome'");
		        
		    } else if (sexo != 'M' && sexo !='F') {
		        JOptionPane.showMessageDialog(null, "Escolha seu sexo ");
		    }  else if (user.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Usuário'");
		    } else if (senha.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Senha'");
		    } else if (confirmaSenha.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Confirmação de Senha'");
		    } else if (dicaSenha.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Dica Senha'");
		    } else if (data_Nasc.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o campo 'Data de Nascimento'");
		    } else {
		        if (senha.equals(confirmaSenha)) {
		            if (data_Nasc.length() == 10 && data_Nasc.charAt(2) == '/' && data_Nasc.charAt(5) == '/') {
		                try {
		                    dataNasc = sdf.parse(data_Nasc);
		                } catch (ParseException e) {
		                    e.printStackTrace();
		                }
		               UsuarioVO usuarioVO = new UsuarioVO(nome, senha, dataNasc, user, dicaSenha, sexo);
		               condicao =  new UsuarioDAO().cadastrarAluno(usuarioVO);
		            } else {
		                JOptionPane.showMessageDialog(null, "Digite a data no formato dd/MM/yyyy", "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
		                return false;
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Senha e Confirma senha diferentes!");
		            return false;
		        } }
		return condicao;

		
		
		
		
		
		
		
		
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
		                AlunoVO alunoVO = new AlunoVO(nome, sobrenome, email, user, senha, dataNasc, nomeCachorro, comidaFav);
		                new AlunoDAO().cadastrarAluno(alunoVO);
		            } else {
		                JOptionPane.showMessageDialog(null, "Digite a data no formato dd/MM/yyyy", "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Senha e Confirma senha diferentes!");
		        }
		    } */
		
	}
	
	
	 

}