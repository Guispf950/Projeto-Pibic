package model.vo;

public class AgrupePalavrasQuestaoVO {

	private int id;
	private String idAgrupePalavras;
	private String palavra;
	private int grupo;

	public AgrupePalavrasQuestaoVO(int id, String idAgrupePalavras, String palavra, int grupo) {
		this.id = id;
		this.idAgrupePalavras = idAgrupePalavras;
		this.palavra = palavra;
		this.grupo = grupo;
	}

	public AgrupePalavrasQuestaoVO(String idAgrupePalavras, String palavra, int grupo) {
		this.idAgrupePalavras = idAgrupePalavras;
		this.palavra = palavra;
		this.grupo = grupo;
	}

	public AgrupePalavrasQuestaoVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdAgrupePalavras() {
		return idAgrupePalavras;
	}

	public void setIdAgrupePalavras(String idAgrupePalavras) {
		this.idAgrupePalavras = idAgrupePalavras;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
}