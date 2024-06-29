package model.vo;

public class FlashCardQuestaoVO {

	private int id;
	private String idFlashCard;
	private String frase;
	private int condicao;
	private String explicacao;

	public FlashCardQuestaoVO() {

	}

	public FlashCardQuestaoVO(String idFlashCard, String frase, int condicao, String explicacao) {
		super();
		this.idFlashCard = idFlashCard;
		this.frase = frase;
		this.condicao = condicao;
		this.explicacao = explicacao; 
	}

	public FlashCardQuestaoVO(int id, String idFlashCard, String frase, int condicao, String explicacao) {
		this.id = id;
		this.idFlashCard = idFlashCard;
		this.frase = frase;
		this.condicao = condicao;
		this.explicacao = explicacao;
	}

	public int getId() {
		return id;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public String getIdFlashCard() {
		return idFlashCard;
	}

	public void setIdFlashCard(String idFlashCard) {
		this.idFlashCard = idFlashCard;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public int getCondicao() {
		return condicao;
	}

	public void setCondicao(int condicao) {
		this.condicao = condicao;
	}

	public String getExplicacao() {
		return explicacao;
	}

	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}

	
}
