package model.vo;


import java.sql.Timestamp;
import java.util.Date;

public class QuizVO {

	private String id;
	private String nomeQuiz;
	private String tempoQuiz;
	private int idAutor;
	private Timestamp horaCriacao;

	public QuizVO() {}

	public QuizVO(String id, String nomeQuiz, int idAutor, Timestamp horaCriacao) {
		this.id = id;
		this.nomeQuiz = nomeQuiz;
		this.idAutor = idAutor;
		this.horaCriacao = horaCriacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeQuiz() {
		return nomeQuiz;
	}

	public void setNomeQuiz(String nomeQuiz) {
		this.nomeQuiz = nomeQuiz;
	}

	public String getTempoQuiz() {
		return tempoQuiz;
	}

	public void setTempoQuiz(String tempoQuiz) {
		this.tempoQuiz = tempoQuiz;
	}

	public int getidAutor() {
		return idAutor;
	}

	public void setidAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public Timestamp getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Timestamp horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	
	
}