package model.bo;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model.dao.AdministradorDAO;
import model.dao.QuizDAO;
import model.vo.QuizVO;

public class QuizBO {

	public String criarQuiz(String nomeQuiz, String autor) {

		Random r = new Random();
		int i = r.nextInt(0xFFFFF); // de 0 até o maior numero de 5 digitos em hexadecimal
		String idQuiz = String.format("%05X", i); // "%05X" serve para formatar o numero em 5 digitos
		Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		int idAutor = new AdministradorDAO().pegarIdComNomeUser(autor);
		System.out.println(idAutor);
 
		QuizVO quiz = new QuizVO(idQuiz, nomeQuiz , idAutor, horaCriacao);
 
		String id_jogo = new QuizDAO().criarQuiz(quiz);
		 
		if (idAutor == 0) {
			JOptionPane.showMessageDialog(null, "Autor nao existe  ");
		}
		return id_jogo;
	}
	
	public List<QuizVO> consultarQuizAdm(String nomeAutor) {
		int idAutor = new AdministradorDAO().pegarIdComNomeUser(nomeAutor);
		return new QuizDAO().consultarQuizAdm(idAutor);
		 

	}
	
 
	 
}