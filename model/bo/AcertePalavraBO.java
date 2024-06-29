package model.bo;

import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JOptionPane;

import model.dao.AcertePalavraDAO;
import model.dao.AdministradorDAO;
import model.dao.QuizDAO;
import model.vo.AcertePalavraVO;
import model.vo.QuizVO;

public class AcertePalavraBO {

	public String criarAcertePalavra(String nomeProfessor, String nomeJogo) {
		String id_jogo = null;
		Random r = new Random();
		int i = r.nextInt(0xFFFFF); // de 0 até o maior numero de 5 digitos em hexadecimal
		String idJogo = String.format("%05X", i); // "%05X" serve para formatar o numero em 5 digitos
		Timestamp horaCriacao = new Timestamp(System.currentTimeMillis());
		int idProfessor = new AdministradorDAO().pegarIdComNomeUser(nomeProfessor);

		AcertePalavraVO acertePalavra = new AcertePalavraVO(idJogo, idProfessor, nomeJogo, horaCriacao);
		id_jogo = new AcertePalavraDAO().criarAcertePalavra(acertePalavra);
		return id_jogo;
	}

	public void alterarNomeAcertePalavra(String novoNome, String idJogo) {
		if(!novoNome.trim().isEmpty() && !idJogo.trim().isEmpty()) {
			new AcertePalavraDAO().alterarNomeAcertePalavra(novoNome, idJogo);
		}
	}
	public void excluirAcertePalavra(String idJogo) {
		if(!idJogo.trim().isEmpty()) {
			new AcertePalavraDAO().excluirAcertePalavra(idJogo);
		} else JOptionPane.showMessageDialog(null, "Digite um id válido");
	}

}
