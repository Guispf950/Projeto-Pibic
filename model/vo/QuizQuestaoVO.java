package model.vo;

public class QuizQuestaoVO {

	private int id;
	private String id_quiz;
	private String pergunta;
	private String alternativaA;
	private String alternativaB;
	private String alternativaC;
	private String alternativaD;
	private String resposta;

	public QuizQuestaoVO(int id, String id_quiz, String pergunta, String alternativaA, String alternativaB,
			String alternativaC, String alternativaD, String resposta) {
		super();
		this.id = id;
		this.id_quiz = id_quiz;
		this.pergunta = pergunta;
		this.alternativaA = alternativaA;
		this.alternativaB = alternativaB;
		this.alternativaC = alternativaC;
		this.alternativaD = alternativaD;
		this.resposta = resposta; 
	}

	
	public QuizQuestaoVO(String id_quiz, String pergunta, String alternativaA, String alternativaB, String alternativaC,
			String alternativaD, String resposta) {
		 
		this.id_quiz = id_quiz;
		this.pergunta = pergunta;
		this.alternativaA = alternativaA;
		this.alternativaB = alternativaB;
		this.alternativaC = alternativaC;
		this.alternativaD = alternativaD;
		this.resposta = resposta;
	}


	public QuizQuestaoVO() {

	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getAlternativaA() {
		return alternativaA;
	}

	public void setAlternativaA(String alternativaA) {
		this.alternativaA = alternativaA;
	}

	public String getAlternativaB() {
		return alternativaB;
	}

	public void setAlternativaB(String alternativaB) {
		this.alternativaB = alternativaB;
	}

	public String getAlternativaC() {
		return alternativaC;
	}

	public void setAlternativaC(String alternativaC) {
		this.alternativaC = alternativaC;
	}

	public String getAlternativaD() {
		return alternativaD;
	}

	public void setAlternativaD(String alternativaD) {
		this.alternativaD = alternativaD;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_quiz() {
		return id_quiz;
	}

	public void setId_quiz(String id_quiz) {
		this.id_quiz = id_quiz;
	}

}