package controller;

import model.bo.AlunoBO;
import view.TelaRestaurarSenha;

public class RestaurarSenhaController {

	public void restaurarSenha(String nome, String sobrenome, String email, String senha,
			String confirmarSenha, String teste_data, String nomeCachorro, String comidaFav, TelaRestaurarSenha tela) {
		
		new AlunoBO().restaurarSenha(nome, sobrenome, email, senha, confirmarSenha, teste_data,
				nomeCachorro, comidaFav, tela);
		
		

	}
} 
