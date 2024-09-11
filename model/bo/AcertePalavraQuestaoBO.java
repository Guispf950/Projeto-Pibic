package model.bo;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;


import model.dao.AcertePalavraQuestaoDAO;
import model.dao.UsuarioDAO;
import model.dao.PontuacaoDAO;
 
import model.dao.QuizQuestaoDAO;
import model.vo.AcertePalavraQuestaoVO;
import model.vo.QuizQuestaoVO;

public class AcertePalavraQuestaoBO {

	
	public boolean verificarResposta(String resposta, String id_jogo, int numeroQuestao) {
		boolean acertou = false;
		if (resposta != null) {
		  acertou = new AcertePalavraQuestaoDAO().verificarResposta(resposta, id_jogo, numeroQuestao);	 
		} else
			JOptionPane.showMessageDialog(null, "Marque uma das alternativas");
		return acertou;

	}

	public AcertePalavraQuestaoVO consultarQuestaoId(int id) {

		AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO();
		questao = new AcertePalavraQuestaoDAO().consultarQuestaoId(id);

		return questao;

	}

	public List<AcertePalavraQuestaoVO> consultarQuestoesId(String id_jogo) {

		List<AcertePalavraQuestaoVO> questoes = new ArrayList<>();
		questoes = new AcertePalavraQuestaoDAO().consultarQuestoes(id_jogo);
		return questoes;

	}

}
