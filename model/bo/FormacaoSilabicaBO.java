package model.bo;

import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JOptionPane;

import model.dao.FormacaoSilabDAO;
import model.dao.AdministradorDAO;
import model.dao.QuizDAO;
import model.vo.FormacaoSilabVO;
import model.vo.QuizVO;

public class FormacaoSilabicaBO {

	public void criarQuiz(String nomeFormSil, String autor) {

		
		Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		int idAutor = new AdministradorDAO().pegarIdComNomeUser(autor);
		FormacaoSilabVO formSil = new FormacaoSilabVO(idAutor, nomeFormSil, horaCriacao);
		new FormacaoSilabDAO().criarFormacaoSilab(formSil);
		
		
	}
}
