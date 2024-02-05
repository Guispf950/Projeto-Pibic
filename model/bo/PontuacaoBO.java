package model.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.PontuacaoController;
import model.dao.UsuarioDAO;
import model.dao.PontuacaoDAO;
import model.vo.PontuacaoVO;

public class PontuacaoBO {

	public void salvarPontuacao(String id_jogo, String nomeAluno, int acertos, Timestamp tempoInicial,
			Timestamp tempoFinal) {
		
		if (!id_jogo.trim().isEmpty() && !nomeAluno.trim().isEmpty() && tempoInicial != null && tempoFinal != null) {
			int idAluno = new UsuarioDAO().pegarIdComNomeUser(nomeAluno);
			int vezesJogadas = new PontuacaoDAO().vezesJogadas(idAluno, id_jogo) + 1;
			PontuacaoVO pontuacao = new PontuacaoVO(id_jogo, idAluno, acertos, tempoInicial, tempoFinal, vezesJogadas);
			new PontuacaoDAO().salvarPontuacao(pontuacao);
		} else
			JOptionPane.showMessageDialog(null, "Erro ao salvar sua pontuacao");
		System.out.println(id_jogo);
		System.out.println("ALUNO : "+nomeAluno);
		System.out.println(acertos);
		System.out.println("tempo inicial: "+tempoInicial);
		System.out.println(tempoFinal);
	}

	public List<PontuacaoVO> consultarPontuacoesPorIdQuiz(String id_jogo) {
		List<PontuacaoVO> pontuacao = new ArrayList<>();
		if (!id_jogo.trim().isEmpty()) {
			new PontuacaoDAO().consultarPontuacoesPorIdJogo(id_jogo);
			pontuacao = new PontuacaoDAO().consultarPontuacoesPorIdJogo(id_jogo);
		} else
			JOptionPane.showMessageDialog(null, "Digite um Id de Quiz");
		return pontuacao;

	}
}
