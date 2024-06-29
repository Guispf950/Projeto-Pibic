package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.bo.PontuacaoBO;
import model.vo.PontuacaoVO;

public class PontuacaoController {

	public void salvarPontuacao(String id_jogo, String nomeAluno, int acertos, Timestamp tempoInicial, Timestamp tempoFinal) {
		
		new PontuacaoBO().salvarPontuacao(id_jogo, nomeAluno, acertos, tempoInicial, tempoFinal);
	}
	
	public 	List<PontuacaoVO> consultarPontuacoesPorIdQuiz(String id_jogo) {
		List<PontuacaoVO> pontuacao = new ArrayList<>();
		pontuacao = new PontuacaoBO().consultarPontuacoesPorIdQuiz(id_jogo);
		return pontuacao;
	}

}
