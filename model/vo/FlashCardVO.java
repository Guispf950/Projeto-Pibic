package model.vo;

import java.sql.Timestamp;

public class FlashCardVO {

	private String id;
	private int idAutor;
	private String nomeFlashCard;
	private Timestamp horaCriacao;
	

	public FlashCardVO() {}
	
	public FlashCardVO(int idAutor, String nomeFlashCard, Timestamp horaCriacao) {
		this.idAutor = idAutor;
		this.nomeFlashCard = nomeFlashCard; 
		this.horaCriacao = horaCriacao;
	}
	public FlashCardVO(String id, int idAutor, String nomeFlashCard, Timestamp horaCriacao) {
		this.id = id;
		this.idAutor = idAutor;
		this.nomeFlashCard = nomeFlashCard;
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
	public String getnomeFlashCard() {
		return nomeFlashCard;
	}
	public void setnomeFlashCard(String nomeFlashCard) {
		this.nomeFlashCard = nomeFlashCard;
	}
	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}
	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}
	
	
	
}
