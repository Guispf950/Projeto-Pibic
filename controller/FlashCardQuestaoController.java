package controller;

import java.sql.Timestamp;

import javafx.scene.Scene;
import model.bo.FlashCardQuestaoBO;
import view.FlashCardAluno;

public class FlashCardQuestaoController {

	
	public boolean verificarResposta(int condicao, String idFlashCard, int acertos, int numeroQuestao) {
		boolean acertou = new FlashCardQuestaoBO().verificarResposta(condicao, idFlashCard, acertos, numeroQuestao);
		
		return acertou;
		
	}
	
	public void visualizarQuestaoTelaMenu(String idFlashCard, String nomeAluno, Scene tela, Timestamp tempoInicial) {
		new FlashCardQuestaoBO().visualizarQuestaoTelaMenu(idFlashCard, nomeAluno, tela, tempoInicial);
	
	}
}
