package model.vo;

public class AcertePalavraQuestaoVO {

	private int id;
	private String idAcertePalavra;
	private String dica;
	private String palavra;

	public AcertePalavraQuestaoVO(int id, String idAcertePalavra, String dica, String palavra) {
		this.id = id;
		this.idAcertePalavra = idAcertePalavra;
		this.dica = dica;
		this.palavra = palavra;
	} // para consultas no bd, pois tem o id que ja é auto_increment no banco

	public AcertePalavraQuestaoVO(int id, String dica, String palavra) {
		this.id = id;
		this.dica = dica;
		this.palavra = palavra;
	}

	public AcertePalavraQuestaoVO(String idAcertePalavra, String dica, String palavra) {
		this.idAcertePalavra = idAcertePalavra;
		this.dica = dica;
		this.palavra = palavra;
	}

	public AcertePalavraQuestaoVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdAcertePalavra() {
		return idAcertePalavra;
	}

	public void setIdAcertePalavra(String idAcertePalavra) {
		this.idAcertePalavra = idAcertePalavra;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

}
