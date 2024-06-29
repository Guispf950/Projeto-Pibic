package model.vo;

import java.sql.Timestamp;

public class FormacaoSilabVO {

	private int id;
	private int idAutor;
    private String nomeFormSil;
    private Timestamp horaCriacao;
    
    
    
    
    
	public FormacaoSilabVO() {}

	public FormacaoSilabVO(int idAutor, String nomeFormSil, Timestamp horaCriacao) {
		this.idAutor = idAutor;
		this.nomeFormSil = nomeFormSil;
		this.horaCriacao = horaCriacao;
	}

	
	public FormacaoSilabVO(int id, int idAutor, String nomeFormSil, Timestamp horaCriacao) {
		this.id = id;
		this.idAutor = idAutor;
		this.nomeFormSil = nomeFormSil;
		this.horaCriacao = horaCriacao;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeFormSil() {
		return nomeFormSil;
	}

	public void setNomeFormSil(String nomeFormSil) {
		this.nomeFormSil = nomeFormSil;
	}

	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
	
	
}
