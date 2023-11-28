package model.dao;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.List;

import controller.AcertePalavraController;
import controller.AcertePalavraQuestaoController;
import controller.FlashCardController;
import controller.PontuacaoController;
import controller.QuizQuestaoController;
import controller.QuizController;
import model.vo.AcertePalavraVO;
import model.vo.FlashCardVO;
import model.vo.FormacaoSilabVO;
import model.vo.FlashCardQuestaoVO;
import model.vo.FormacaoSilabicaQuestaoVO;
import model.vo.PontuacaoVO;
import model.vo.QuizVO;
import view.CriarAcerteAPalavra;

public class teste {

	public static void main(String[] args) {
		 
		/*Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		FlashCardQuestaoVO frase = new FlashCardQuestaoDAO().consultarQuestaoId(1);
		System.out.println(frase.getFrase());
		List<FlashCardQuestaoVO> frases = new ArrayList<>();
		frases = new FlashCardQuestaoDAO().consultarQuestoes("33F42");//TROQUE O PARAMETRO POR UM ID QUE JA EXISTE NO BANCO DE DADOS
		System.out.println(frases.size());
		AcertePalavraVO jogo = new AcertePalavraVO("33F43", 1, "TESTE JOGO", horaCriacao); 
		new AcertePalavraDAO().criarAcertePalavra(jogo);
		new AcertePalavraDAO().alterarNomeAcertePalavra("TESTE JOGO ALTERAR NOME","33F43");
		new AcertePalavraDAO().excluirAcertePalavra("33F43");
		new AcertePalavraController().criarAcertePalavra("33F44", "Professor1", "TESTE CRIACAO DE JOGO");
		new AcertePalavraController().alterarNomeAcertePalavra("teste alterando nome","D3A79");//TROQUE O PARAMETRO POR UM ID QUE JA EXISTE NO BANCO DE DADOS
		new AcertePalavraController().excluirAcertePalavra("012CD"); */
		
		Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		//AcertePalavraVO jogo = new AcertePalavraVO("33F43", 1, "TESTE JOGO", horaCriacao); 
		//new AcertePalavraDAO().criarAcertePalavra(jogo);
		//new AcertePalavraQuestaoController().adcionarQuestao("oi", "TESTANDO", null, "33F43");
		//new AcertePalavraQuestaoController().adcionarQuestao("oi", "TESTE", null, "33F43");
		//new AcertePalavraQuestaoController().adcionarQuestao("oi", "BIOLOGIA", null, "33F43");
		//new AcertePalavraQuestaoController().adcionarQuestao("oi", "NAVE", null, "33F43");
		new AcertePalavraController().criarAcertePalavra("Professor1","jogo teste");
		CriarAcerteAPalavra tela = new CriarAcerteAPalavra("Professor1");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA1", "QUESTAO1", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA2", "QUESTAO2", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA3", "QUESTAO3", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA4", "QUESTAO4", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA5", "QUESTAO5", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA6", "QUESTAO6", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA7", "QUESTAO7", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA8", "QUESTAO8", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA9", "QUESTAO9", tela, "C040A");
	   new AcertePalavraQuestaoController().adcionarQuestao("DICA10", "QUESTAO10", tela, "C040A");
		String palavra = "" ;
		
		String [ ] letras = new String [2];
		letras[0] = "o";
		letras[1] = "i";
		//letras[2] = "A";
		palavra += letras[0];
		palavra += letras[1];
		//palavra += letras[2];
		System.out.println(palavra);
		boolean condicao;
		//condicao = new AcertePalavraQuestaoController().verificarResposta(palavra, "33F43", 0, 3);
		//if(condicao) {
			System.out.println("correto");
		//} else System.out.println("errado");
	}
}