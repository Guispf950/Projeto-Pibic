package model.bo;

import java.sql.Timestamp;
import java.util.Random;

import model.dao.FlashCardDAO;
import model.dao.ProfessorDAO;
import model.vo.FlashCardVO;
 

public class FlashCardBO {

	public void criarFlashCard(String nomeFlashCard, String autor) {
		Random r = new Random();
		int i = r.nextInt(0xFFFFF); // de 0 até o maior numero de 5 digitos em hexadecimal
		String idFlashCard = String.format("%05X", i); // "%05X" serve para formatar o numero em 5 digitos
		Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		int idAutor = new ProfessorDAO().pegarIdComNomeUser(autor);
		FlashCardVO flash = new FlashCardVO(idFlashCard, idAutor, nomeFlashCard, horaCriacao);
		new FlashCardDAO().criarFlashCard(flash);

	}

}
