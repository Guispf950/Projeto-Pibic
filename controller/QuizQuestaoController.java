package controller;

import java.sql.Timestamp;

import javafx.scene.Scene;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;
import view.TelaDeMenuAluno;
import view.TelaQuiz;
import view.TelaQuizAlterar;
import view.TelaQuizAluno;

public class QuizQuestaoController {
	

	
	public void visualizarQuestoesBotaoContinuar(String id_quiz, int numeroQuestao, TelaQuizAluno tela, int acertos, Timestamp tempoInicial) {
		new QuizQuestaoBO().visualizarQuestaoBotaoContinuar(id_quiz, numeroQuestao, tela, acertos, tempoInicial);
	}
  
	public void visualizarQuestaoTelaMenu(String id_quiz, String nomeAluno, Scene tela) {
		new QuizQuestaoBO().visualizarQuestaoTelaMenu(id_quiz, nomeAluno, tela);
	
	}

	public boolean verificarResposta(String resposta, String id_Quiz, int acertos, int numeroQuestao) {
		boolean acertou = new QuizQuestaoBO().verificarResposta(resposta, id_Quiz, acertos, numeroQuestao);
		return acertou;
		
	}
	
	public void consultarQuestaoId(int id, String nomeProf) {
		QuizQuestaoVO questao = new QuizQuestaoVO();
		questao = new QuizQuestaoBO().consultarQuestaoId(id);
		TelaQuizAlterar tela = new TelaQuizAlterar(nomeProf);
		tela.getTxtAlternativaA().setText(questao.getAlternativaA());
		tela.getTxtAlternativaB().setText(questao.getAlternativaB());
		tela.getTxtAlternativaC().setText(questao.getAlternativaC());
		tela.getTxtAlternativaD().setText(questao.getAlternativaD());
		tela.getTxtPergunta().setText(questao.getPergunta());
		tela.getLblNumeroQuestao().setText(String.valueOf(id));
		
		if(questao.getResposta().equals(questao.getAlternativaA())) {
			tela.getAlternativa1jCheckBox().setSelected(true);	
		} else if (questao.getResposta().equals(questao.getAlternativaB())) {
			tela.getAlternativa2jCheckBox2().setSelected(true);
		} else if (questao.getResposta().equals(questao.getAlternativaC())) {
			tela.getAlternativa3jCheckBox3().setSelected(true);
		}else if (questao.getResposta().equals(questao.getAlternativaD())) {
			tela.getjCheckBox4().setSelected(true);
		}
		tela.setVisible(true);
	}
	
	
	public void alterarQuestao(int id , String pergunta, String alternativaA, String alternativaB, String alternativaC, 
			String alternativaD, String resposta, TelaQuizAlterar tela) {
		
		new QuizQuestaoBO().alterarQuestao(id , pergunta, alternativaA, alternativaB, alternativaC, alternativaD, resposta, tela);
		
	} 
	
	public void visualizarProximaQuestao(int id, TelaQuizAlterar tela) {
		new QuizQuestaoBO().visualizarProximaQuestao(id, tela);
		 
	}
	
	public void excluirQuestao(int id, TelaQuizAlterar tela) {
		new QuizQuestaoBO().excluirQuestao(id, tela);
	}
	}