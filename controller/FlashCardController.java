package controller;

import java.sql.Timestamp; 

import model.bo.FlashCardBO;
import model.bo.FlashCardQuestaoBO;
import view.FlashCardCriar;

public class FlashCardController {

	public void criarFlashCard(String nomeFlashCard, String autor) {
		new FlashCardBO().criarFlashCard(nomeFlashCard, autor);
	}
	
	public void adcionarQuestao(String idFlashCard, String frase, int condicao, String explicacao,FlashCardCriar tela) {
		new FlashCardQuestaoBO().adcionarQuestao(idFlashCard, frase, condicao, explicacao, tela);
	}
	
}
