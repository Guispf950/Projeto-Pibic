package model.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import controller.AcertePalavraController;
import model.dao.AcertePalavraQuestaoDAO;
import model.dao.UsuarioDAO;
import model.dao.PontuacaoDAO;
import model.dao.AdministradorDAO;
import model.dao.QuizDAO;
import model.dao.QuizQuestaoDAO;
import model.vo.AcertePalavraQuestaoVO;
import model.vo.QuizQuestaoVO;
import view.CriarAcerteAPalavra;
import view.TelaDeMenuAluno;
import view.TelaQuiz;
import view.TelaQuizAlterar;
import view.TelaQuizAluno;

public class AcertePalavraQuestaoBO {

	public void adcionarQuestao(String dica, String palavra, String id_jogo) {

		if (!dica.trim().isEmpty() && !palavra.trim().isEmpty()) {
			AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO(id_jogo, dica, palavra);
			new AcertePalavraQuestaoDAO().adcionarQuestao(questao);

		} else
			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro no cadastro de questoes",JOptionPane.ERROR_MESSAGE);

	}

	public void alterarQuestao(int id, String dica, String palavra, TelaQuizAlterar tela) {
		if (!dica.trim().isEmpty() && !palavra.trim().isEmpty()) {
			AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO(id, dica, palavra);
			new AcertePalavraQuestaoDAO().alterarQuestao(questao);
		} else
			JOptionPane.showMessageDialog(null, "Selecione a resposta dentre as alternativas");
	}

	public void excluirQuestao(int id, TelaQuizAlterar tela) {// TROCAR TELA QUIZ PELA TELA DE ADCIONAR QUESTAO NO
																// ACERTE PALAVRA

		new AcertePalavraQuestaoDAO().excluirQuestao(id, tela);
	}

	public boolean verificarResposta(String resposta, String id_jogo, int numeroQuestao) {
		boolean acertou = false;
		if (resposta != null) {
			new AcertePalavraQuestaoDAO().verificarResposta(resposta, id_jogo, numeroQuestao);
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
