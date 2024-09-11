package model.bo;

import java.sql.Timestamp;  

import javax.swing.JOptionPane;

 
import javafx.scene.Scene;
import model.dao.UsuarioDAO;
import model.dao.PontuacaoDAO;
import model.dao.QuizQuestaoDAO;
import model.vo.QuizQuestaoVO;
 
 

public class QuizQuestaoBO {

	 
	 
	 

	public void visualizarQuestaoTelaMenu(String id_jogo, String nomeAluno, Scene tela) {
		int idAluno = new UsuarioDAO().pegarIdComNomeUser(nomeAluno);
		int vezesJogadas = new PontuacaoDAO().vezesJogadas(idAluno, id_jogo);
		
		if (vezesJogadas < 3) {
			 
			new QuizQuestaoDAO().visualizarQuestaoTelaMenu(id_jogo, nomeAluno, tela);
		} else
			JOptionPane.showMessageDialog(null, "Voce atingiu o limite de tentativas");

	}

	public boolean verificarResposta(String resposta, String id_jogo, int acertos, int numeroQuestao) {
		boolean acertou = false;
		acertou = new QuizQuestaoDAO().verificarResposta(resposta, id_jogo, acertos, numeroQuestao);
		return acertou;

	}

	public QuizQuestaoVO consultarQuestaoId(int id) {
		QuizQuestaoVO questao = new QuizQuestaoVO();
		questao = new QuizQuestaoDAO().consultarQuestaoId(id);

		return questao;

	}

	 

 

	public int totalQuestaoQuiz(String id_quiz) {

		int totalQuestoes = new QuizQuestaoDAO().totalQuestaoQuiz(id_quiz);
		return totalQuestoes;
	}

	public QuizQuestaoVO primeiraQuestaoQuiz(String id_jogo) {

		QuizQuestaoVO questao1 = new QuizQuestaoDAO().primeiraQuestaoQuiz(id_jogo);
		return questao1;
	}

	public QuizQuestaoVO visualizarQuestaoAnterior(String id_quiz, int numeroQuestao) {
		QuizQuestaoVO questao = new QuizQuestaoDAO().visualizarQuestaoAnterior(id_quiz, numeroQuestao);
		System.out.println("pergunta: " + numeroQuestao);
		return questao;
	}
}

/*
 * package model.bo;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.sql.Timestamp;
 * 
 * import javax.swing.JOptionPane;
 * 
 * import controller.QuizCriarController; import javafx.scene.Scene; import
 * model.dao.AlunoDAO; import model.dao.Conexao; import model.dao.PontuacaoDAO;
 * import model.dao.QuizQuestaoDAO; import model.vo.QuizQuestaoVO; import
 * view.TelaDeMenuAluno; import view.TelaQuizAlterar; import view.TelaQuizAluno;
 * 
 * public class QuizQuestaoBO {
 * 
 * public void adcionarQuestao(String pergunta, String alternativaA, String
 * alternativaB, String alternativaC, String alternativaD, String resposta,
 * QuizCriarController tela, String id_jogo, int numeroQuestao) {
 * 
 * 
 * 
 * if (!pergunta.trim().isEmpty() && !alternativaA.trim().isEmpty() &&
 * !alternativaB.trim().isEmpty()
 * 
 * && !alternativaC.trim().isEmpty() && !alternativaD.trim().isEmpty() &&
 * !id_jogo.trim().isEmpty()) {
 * 
 * if (resposta != null) {
 * 
 * tela.getTxtAlternativaA().setText(""); tela.getTxtAlternativaB().setText("");
 * tela.getTxtAlternativaC().setText(""); tela.getTxtAlternativaD().setText("");
 * 
 * 
 * tela.getRbAlternativaA().setSelected(false);
 * tela.getRbAlternativaB().setSelected(false);
 * tela.getRbAlternativaC().setSelected(false);
 * tela.getRbAlternativaD().setSelected(false);
 * tela.getTxtPergunta().setText(""); tela.getTxtTiuloQuiz().setEditable(false);
 * 
 * 
 * 
 * QuizQuestaoVO questao = new QuizQuestaoVO(id_jogo, pergunta, alternativaA,
 * alternativaB, alternativaC, alternativaD, resposta); new
 * QuizQuestaoDAO().adcionarQuestao(questao, numeroQuestao);
 * tela.numeroQuestaoAtual++;
 * tela.getLblQuestao().setText(String.valueOf(tela.numeroQuestaoAtual)); } else
 * 
 * JOptionPane.showMessageDialog(null,
 * "Selecione a resposta dentre as alternativas",
 * 
 * "Erro no cadastro de questoes", JOptionPane.ERROR_MESSAGE);
 * 
 * } else
 * 
 * JOptionPane.showMessageDialog(null, "Preencha todos os campos",
 * "Erro no cadastro de questoes",
 * 
 * JOptionPane.ERROR_MESSAGE);
 * 
 * }
 * 
 * public void visualizarQuestaoBotaoContinuar(String id_jogo, int
 * numeroQuestao, TelaQuizAluno tela, int acertos, Timestamp tempoInicial) { if
 * (!id_jogo.trim().isEmpty()) { new
 * QuizQuestaoDAO().visualizarQuestaoBotaoContinuar(id_jogo, numeroQuestao,
 * tela, acertos, tempoInicial); } }
 * 
 * public void visualizarQuestaoTelaMenu(String id_jogo, String nomeAluno,
 * TelaDeMenuAluno tela) { int idAluno = new
 * AlunoDAO().pegarIdComNomeUser(nomeAluno); int vezesJogadas = new
 * PontuacaoDAO().vezesJogadas(idAluno, id_jogo); if(vezesJogadas<3) { new
 * QuizQuestaoDAO().visualizarQuestaoTelaMenu(id_jogo, nomeAluno, tela); } else
 * JOptionPane.showMessageDialog(null, "Voce atingiu o limite de tentativas");
 * 
 * 
 * }
 * 
 * public boolean verificarResposta(String resposta, String id_jogo, int
 * acertos, int numeroQuestao) { boolean acertou = false; if (resposta != null)
 * { acertou = new QuizQuestaoDAO().verificarResposta(resposta, id_jogo,
 * acertos, numeroQuestao); } else JOptionPane.showMessageDialog(null,
 * "Marque uma das alternativas"); return acertou;
 * 
 * }
 * 
 * public QuizQuestaoVO consultarQuestaoId(int id) { QuizQuestaoVO questao = new
 * QuizQuestaoVO(); questao = new QuizQuestaoDAO().consultarQuestaoId(id);
 * 
 * return questao;
 * 
 * }
 * 
 * public QuizQuestaoVO visualizarQuestaoAnterior(String id_quiz, int
 * numeroQuestao) { QuizQuestaoVO questao = new
 * QuizQuestaoDAO().visualizarQuestaoAnterior(id_quiz, numeroQuestao);
 * System.out.println("pergunta: " + numeroQuestao); return questao; }
 * 
 * public void alterarQuestao(int id, String pergunta, String alternativaA,
 * String alternativaB, String alternativaC, String alternativaD, String
 * resposta, TelaQuizAlterar tela) { if (!pergunta.trim().isEmpty() &&
 * !alternativaA.trim().isEmpty() && !alternativaB.trim().isEmpty()
 * 
 * && !alternativaC.trim().isEmpty() && !alternativaD.trim().isEmpty()) {
 * 
 * if (resposta != null) { tela.getTxtPergunta().setText("");
 * tela.getTxtAlternativaA().setText(""); tela.getTxtAlternativaB().setText("");
 * tela.getTxtAlternativaC().setText(""); tela.getTxtAlternativaD().setText("");
 * 
 * if (tela.getAlternativa1jCheckBox().isSelected()) {
 * tela.getAlternativa2jCheckBox2().setSelected(false);
 * tela.getAlternativa3jCheckBox3().setSelected(false);
 * tela.getjCheckBox4().setSelected(false); } if
 * (tela.getAlternativa2jCheckBox2().isSelected()) {
 * tela.getAlternativa1jCheckBox().setSelected(false);
 * tela.getAlternativa3jCheckBox3().setSelected(false);
 * tela.getjCheckBox4().setSelected(false); } if
 * (tela.getAlternativa3jCheckBox3().isSelected()) {
 * tela.getAlternativa1jCheckBox().setSelected(false);
 * tela.getAlternativa2jCheckBox2().setSelected(false);
 * tela.getjCheckBox4().setSelected(false); } if
 * (tela.getjCheckBox4().isSelected()) {
 * tela.getAlternativa1jCheckBox().setSelected(false);
 * tela.getAlternativa2jCheckBox2().setSelected(false);
 * tela.getAlternativa3jCheckBox3().setSelected(false); }
 * 
 * QuizQuestaoVO questao = new QuizQuestaoVO(id, resposta, pergunta,
 * alternativaA, alternativaB, alternativaC, alternativaD, resposta);
 * System.out.println(questao.getId()); new
 * QuizQuestaoDAO().alterarQuestoes(questao); } else
 * JOptionPane.showMessageDialog(null,
 * "Selecione a resposta dentre as alternativas"); } }
 * 
 * public void visualizarProximaQuestao(int id, TelaQuizAlterar tela) { new
 * QuizQuestaoDAO().visualizarProximaQuestao(id, tela); }
 * 
 * public void excluirQuestao(int id, TelaQuizAlterar tela) { new
 * QuizQuestaoDAO().excluirQuestao(id, tela); } public int
 * totalQuestaoQuiz(String id_quiz) {
 * 
 * 
 * int totalQuestoes = new QuizQuestaoDAO().totalQuestaoQuiz(id_quiz); return
 * totalQuestoes; }
 * 
 * public QuizQuestaoVO primeiraQuestaoQuiz(String id_jogo) {
 * 
 * 
 * QuizQuestaoVO questao1 = new QuizQuestaoDAO().primeiraQuestaoQuiz(id_jogo);
 * return questao1; } }
 */