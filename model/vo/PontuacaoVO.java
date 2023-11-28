package model.vo;

import java.sql.Timestamp;

public class PontuacaoVO {
	
	private int id;
	private String id_jogo;
	private int idAluno;
	private int acertos;
	private Timestamp tempoInicial;
	private Timestamp tempoFinal;
	private int vezesJogadas;
	
	
	public PontuacaoVO() {}
	
	public PontuacaoVO(String id_jogo, int idAluno, int acertos, Timestamp tempoInicial, Timestamp tempoFinal, int vezesJogadas) {
		this.id_jogo = id_jogo;
		this.idAluno = idAluno;
		this.acertos = acertos;
		this.tempoInicial = tempoInicial;
		this.tempoFinal = tempoFinal;
		this.vezesJogadas = vezesJogadas;
	} //CONSTRUTOR SEM ID, POIS NO BD O ID É AUTO_INCREMENT

	
	public PontuacaoVO(int id, String id_jogo, int idAluno, int acertos, Timestamp tempoInicial, Timestamp tempoFinal,
			int vezesJogadas) {
		super();
		this.id = id;
		this.id_jogo = id_jogo;
		this.idAluno = idAluno;
		this.acertos = acertos;
		this.tempoInicial = tempoInicial;
		this.tempoFinal = tempoFinal;
		this.vezesJogadas = vezesJogadas;
	}// CONSTRUTOR COM ID, PARA FAZER CONSULTAS NO BD

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getid_jogo() {
		return id_jogo;
	}

	public void setid_jogo(String id_jogo) {
		this.id_jogo = id_jogo;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public Timestamp getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Timestamp tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public Timestamp getTempoFinal() {
		return tempoFinal;
	}

	public void setTempoFinal(Timestamp tempoFinal) {
		this.tempoFinal = tempoFinal;
	}

	public int getVezesJogadas() {
		return vezesJogadas;
	}

	public void setVezesJogadas(int vezesJogadas) {
		this.vezesJogadas = vezesJogadas;
	}
	
	
	
	
}
