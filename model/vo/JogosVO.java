package model.vo;

import java.sql.Timestamp;

public class JogosVO {

	private String id;
	private String nomeJogo;
	private int idAutor;
	private Timestamp horaCriacao;

	public JogosVO() {
	}

	public JogosVO(String id, String nomeJogo, int idAutor, Timestamp horaCriacao) {
		this.id = id;
		this.nomeJogo = nomeJogo;
		this.idAutor = idAutor;
		this.horaCriacao = horaCriacao;
	}

	public JogosVO(String nomeJogo, int idAutor, Timestamp horaCriacao) {
		this.nomeJogo = nomeJogo;
		this.idAutor = idAutor;
		this.horaCriacao = horaCriacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeQuiz) {
		this.nomeJogo = nomeQuiz;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

}
