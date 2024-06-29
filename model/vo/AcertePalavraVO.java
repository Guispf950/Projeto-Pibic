package model.vo;

import java.sql.Timestamp;

public class AcertePalavraVO {

	private String id;
	private int idAutor;
	private String nomeAcertePalavra;
	private Timestamp horaCriacao;

	public AcertePalavraVO() {}

	public AcertePalavraVO(int idAutor, String nomeAcertePalavra, Timestamp horaCriacao) {
		this.idAutor = idAutor;
		this.nomeAcertePalavra = nomeAcertePalavra;
		this.horaCriacao = horaCriacao;
	}

	public AcertePalavraVO(String id, int idAutor, String nomeAcertePalavra, Timestamp horaCriacao) {
		this.id = id;
		this.idAutor = idAutor;
		this.nomeAcertePalavra = nomeAcertePalavra;
		this.horaCriacao = horaCriacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeAcertePalavra() {
		return nomeAcertePalavra;
	}

	public void setNomeAcertePalavra(String nomeAcertePalavra) {
		this.nomeAcertePalavra = nomeAcertePalavra;
	}

	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

}
