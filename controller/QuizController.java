package controller;

import model.bo.QuizBO;

public class QuizController {

	
	
	public void criarQuiz( String nomeQuiz, String autor) {
		new QuizBO().criarQuiz(nomeQuiz, autor);
		
	}
	
	public void consultarQuiz(String id) {
		
		
	}
	
	public void alterarQuiz(String id) {
		
	}
	
	public void deletarQuiz(String id) {
		
	}
	


	
// NÃO É MAIS USADO
/*	public void adcionarQuestao(String pergunta, String alternativaA, String alternativaB, String alternativaC, 
			String alternativaD, String resposta, TelaQuiz tela, String id_jogo) {
		
		new QuizQuestaoBO().adcionarQuestao(pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta, id_jogo);
		
	} */
	

	
	
	
}