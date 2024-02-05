package model.bo;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import controller.FlashCardCriarController;
import javafx.scene.Scene;
import model.dao.FlashCardQuestaoDAO;
import model.vo.FlashCardQuestaoVO;
import view.CriarFlashCar;
import view.FlashCardAluno;
import view.FlashCardCriar;
 

public class FlashCardQuestaoBO {

	public void adcionarQuestao(String idFlashCard, String frase, int condicao, String explicacao, FlashCardCriar tela) {
		if(!idFlashCard.trim().isEmpty()&& !frase.trim().isEmpty()&& !explicacao.trim().isEmpty()) {
		FlashCardQuestaoVO FlashCardQuestaoVO = new FlashCardQuestaoVO(idFlashCard, frase, condicao, explicacao);
		new FlashCardQuestaoDAO().adcionarQuestao(FlashCardQuestaoVO, tela);
		} else JOptionPane.showMessageDialog(null,"Preencha todos os campos");
	} 
 
	public boolean verificarResposta(int condicao, String idFlashCard, int acertos, int numeroQuestao) {
		boolean acertou = false;
		if(condicao == 0 || condicao == 1) {
			acertou = new FlashCardQuestaoDAO().verificarResposta(condicao, idFlashCard, acertos, numeroQuestao);
		}
		return  acertou;
	}
	
	public void visualizarQuestaoTelaMenu(String idFlashCard, String nomeAluno, Scene tela, Timestamp tempoInicial) {
		new FlashCardQuestaoDAO().visualizarQuestaoTelaMenu(idFlashCard, nomeAluno, tela, tempoInicial);
	
	}
}
