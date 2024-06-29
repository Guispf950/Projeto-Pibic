package model.bo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.scene.Scene;
import model.dao.AgrupePalavrasQuestaoDAO;
import model.vo.AgrupePalavrasQuestaoVO;

public class AgrupePalavrasQuestaoBO {

	public void adcionarQuestao(String palavra, int grupo, String id_jogo, Scene tela) { //SUBSTITUIR ESSA TELA PELA TELA DE ADCIONAR QUESTAO DO AGRUPE PALAVRAS

		if (!palavra.trim().isEmpty() && grupo < 5) {
			AgrupePalavrasQuestaoVO questao = new AgrupePalavrasQuestaoVO(id_jogo, palavra, grupo);
			new AgrupePalavrasQuestaoDAO().adcionarQuestao(questao, tela);

		} else
			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro no cadastro de questoes",JOptionPane.ERROR_MESSAGE);

	}
	public List<AgrupePalavrasQuestaoVO> consultarQuestoesId(String id_jogo) {

		List<AgrupePalavrasQuestaoVO> questoes = new ArrayList<>();
		questoes = new AgrupePalavrasQuestaoDAO().consultarQuestoes(id_jogo);
		return questoes;

	}
	
}
