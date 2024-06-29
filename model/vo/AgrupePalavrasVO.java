package model.vo;

import java.sql.Timestamp;

public class AgrupePalavrasVO {

	private String id;
	private int idAutor;
	private String nomeAgrupePalavra;
	private Timestamp horaCriacao;

	public AgrupePalavrasVO(String id, int idAutor, String nomeAgrupePalavra, Timestamp horaCriacao) {
		this.id = id;
		this.idAutor = idAutor;
		this.nomeAgrupePalavra = nomeAgrupePalavra;
		this.horaCriacao = horaCriacao;
	}

	public AgrupePalavrasVO(int idAutor, String nomeAgrupePalavra, Timestamp horaCriacao) {
		this.idAutor = idAutor;
		this.nomeAgrupePalavra = nomeAgrupePalavra;
		this.horaCriacao = horaCriacao;
	}

	public AgrupePalavrasVO() {
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

	public String getNomeAgrupePalavra() {
		return nomeAgrupePalavra;
	}

	public void setNomeAgrupePalavra(String nomeAgrupePalavra) {
		this.nomeAgrupePalavra = nomeAgrupePalavra;
	}

	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}
}
