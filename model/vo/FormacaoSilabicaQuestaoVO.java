package model.vo;

public class FormacaoSilabicaQuestaoVO {

	private int id;
	private int idFormacaoSilabica;
	private String palavra;
	
	
	
	
	public FormacaoSilabicaQuestaoVO() {}

	public FormacaoSilabicaQuestaoVO(int idFormacaoSilabica, String palavra) {
		this.idFormacaoSilabica = idFormacaoSilabica;
		this.palavra = palavra;
	} //construtor sem o id, usado para insert, pois no Banco de dados id é auto increment

	public FormacaoSilabicaQuestaoVO(int id, int idFormacaoSilabica, String palavra) {
		this.id = id;
		this.idFormacaoSilabica = idFormacaoSilabica;
		this.palavra = palavra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFormacaoSilabica() {
		return idFormacaoSilabica;
	}

	public void setIdFormacaoSilabica(int idFormacaoSilabica) {
		this.idFormacaoSilabica = idFormacaoSilabica;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	
		
}
