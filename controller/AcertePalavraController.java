package controller;

import java.sql.Timestamp;

import model.bo.AcertePalavraBO;
import model.dao.AcertePalavraDAO;
import model.vo.AcertePalavraVO;

public class AcertePalavraController {

	
	//AcertePalavraVO jogo = new AcertePalavraVO("33F43", 1, "TESTE JOGO", horaCriacao); //TROQUE O PARAMETRO POR UM ID QUE JA EXISTE NO BANCO DE DADOS
	//new AcertePalavraDAO().criarAcertePalavra(jogo);
	//new AcertePalavraDAO().alterarNomeAcertePalavra("TESTE JOGO ALTERAR NOME","33F43");
	//new AcertePalavraDAO().excluirAcertePalavra("33F43");
	public String criarAcertePalavra(String nomeProfessor, String nomeJogo) {
		String id_jogo = null;
		id_jogo = new AcertePalavraBO().criarAcertePalavra(nomeProfessor, nomeJogo);
		return id_jogo;
	}
	
	public void alterarNomeAcertePalavra(String novoNome, String idJogo) {
		new AcertePalavraBO().alterarNomeAcertePalavra(novoNome, idJogo);
	}
	
	public void excluirAcertePalavra(String idJogo) {
		new AcertePalavraBO().excluirAcertePalavra(idJogo);
	}
	

	
}
