package model.bo;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import javafx.scene.Scene;
import model.dao.FlashCardQuestaoDAO;
import model.vo.FlashCardQuestaoVO;
 

public class FlashCardQuestaoBO {

	 

	public boolean verificarResposta(int condicao, String idFlashCard, int acertos, int numeroQuestao) {
		boolean acertou = false;
		if (condicao == 0 || condicao == 1) {
			acertou = new FlashCardQuestaoDAO().verificarResposta(condicao, idFlashCard, acertos, numeroQuestao);
		}
		return acertou;
	}

	 
}
