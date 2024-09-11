package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.converter.CharacterStringConverter;
import model.bo.AcertePalavraQuestaoBO;
import model.dao.AcertePalavraQuestaoDAO;
import model.dao.FlashCardQuestaoDAO;
import model.vo.AcertePalavraQuestaoVO;
import model.vo.FlashCardQuestaoVO;
import servicos.Servicos;

public class AcerteAPalavraController implements Initializable {
	int acertos = 0;
	private TextField[][] salvarTextFields;
	private TextField[][] salvarTextFields1;

	TextField salvarPalavra[];
	Timestamp tempoInicial;
	String id_jogo;
	private String userAluno;

	@FXML
	private Label lblDica;
	@FXML
	private AnchorPane rootPane;

	@FXML
	private GridPane grid1;
	@FXML
	private GridPane grid2;

	private Boolean init = false;

	@FXML
	private ImageView finalizarJogo;

	@FXML
	private ImageView sairJogo;

	List<AcertePalavraQuestaoVO> Questoes;
	List<AcertePalavraQuestaoVO> questoes;
	List<AcertePalavraQuestaoVO> questoes1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (init) {

			Questoes = new AcertePalavraQuestaoDAO().consultarQuestoes(id_jogo);
			questoes = Questoes.subList(0, Questoes.size() / 2);
			questoes1 = Questoes.subList(Questoes.size() / 2, Questoes.size());

			salvarTextFields = new TextField[questoes.size()][];

			for (int i = 0; i < questoes.size(); i++) {

				String palavra = questoes.get(i).getPalavra();
				salvarTextFields[i] = new TextField[questoes.get(i).getPalavra().length()];
				for (int j = 0; j < palavra.length(); j++) {
					TextField textField = createAlphanumericAutoFocusTextField();
					TextFormatter<Character> textFormatter = new TextFormatter<>(new CharacterStringConverter(), null,
							change -> {
								String newText = change.getControlNewText();
								if (newText.length() > 1) {
									return null;
								}
								return change;
							});

					textField.setTextFormatter(textFormatter);
					Font font = Font.font("Monospaced", FontWeight.BOLD, 16);
					textField.setFont(font);
					textField.setStyle("-fx-background-color:  #08165B;" + "-fx-border-color:  #FFF;"
							+ "-fx-border-width: 1px;" + "-fx-text-fill: white;"

					);
					textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					GridPane.setFillHeight(textField, true);
					GridPane.setFillWidth(textField, true);

					salvarTextFields[i][j] = textField;
					System.out.println(salvarTextFields[i][j]);
					final int linha = i;

					textField.setOnMouseClicked(event -> {
						lblDica.setText(questoes.get(linha).getDica());
					});

					grid2.add(textField, j, i);
				}
			}

			salvarTextFields1 = new TextField[questoes1.size()][];

			for (int i = 0; i < questoes1.size(); i++) {

				String palavra = questoes1.get(i).getPalavra();
				salvarTextFields1[i] = new TextField[questoes1.get(i).getPalavra().length()];
				for (int j = 0; j < palavra.length(); j++) {
					TextField textField = createAlphanumericAutoFocusTextField();
					TextFormatter<Character> textFormatter = new TextFormatter<>(new CharacterStringConverter(), null,
							change -> {
								String newText = change.getControlNewText();
								if (newText.length() > 1) {
									return null;
								}
								return change;
							});

					textField.setTextFormatter(textFormatter);
					Font font = Font.font("Monospaced", FontWeight.BOLD, 16);
					textField.setFont(font);
					textField.setStyle("-fx-background-color:  #08165B;" + "-fx-border-color:  #FFF;"
							+ "-fx-border-width: 1px;" + "-fx-text-fill: white;"

					);
					textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					GridPane.setFillHeight(textField, true);
					GridPane.setFillWidth(textField, true);

					salvarTextFields1[i][j] = textField;
					System.out.println(salvarTextFields1[i][j]);
					final int linha = i;
					System.out.println("linhaaa: " + linha);

					textField.setOnMouseClicked(event -> {
						lblDica.setText(questoes1.get(linha).getDica());
					});

					grid1.add(textField, j, i);
				}
			}

			// Configurando dinamicamente as restrições de linha
			for (int i = 0; i < questoes.size(); i++) {
				RowConstraints rowConstraints = new RowConstraints();
				rowConstraints.setVgrow(Priority.SOMETIMES);
				grid1.getRowConstraints().add(rowConstraints);
			}

			for (int i = 0; i < questoes.size(); i++) {
				RowConstraints rowConstraints = new RowConstraints();
				rowConstraints.setVgrow(Priority.SOMETIMES);
				grid2.getRowConstraints().add(rowConstraints);
			}

			// Configurando dinamicamente as restrições de coluna
			int maxColumns = questoes.stream().mapToInt(palavra -> palavra.getPalavra().length()).max().orElse(0);
			for (int i = 0; i < maxColumns; i++) {
				ColumnConstraints columnConstraints = new ColumnConstraints();
				columnConstraints.setHgrow(Priority.SOMETIMES);
				grid1.getColumnConstraints().add(columnConstraints);
			}
			for (int i = 0; i < maxColumns; i++) {
				ColumnConstraints columnConstraints = new ColumnConstraints();
				columnConstraints.setHgrow(Priority.SOMETIMES);
				grid2.getColumnConstraints().add(columnConstraints);
			}

			finalizarJogo.setOnMouseClicked(event -> {
				onFinalizarAcertePalavra();

			});
		} else {
			init = true;
		}

	}

	private TextField createAlphanumericAutoFocusTextField() {
		TextField textField = new TextField();

		textField.setOnKeyTyped(this::handleKeyTyped);

		textField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.BACK_SPACE) {
				moveFocusToPreviousTextField(textField);
			}
		});

		return textField;
	}

	private void handleKeyTyped(KeyEvent event) {
		String typedText = event.getCharacter();
		if (isAlphanumeric(typedText)) {
			TextField textField = (TextField) event.getSource();
			textField.setText(typedText.toUpperCase());
			moveFocusToNextTextField(textField);
		}
	}

	private boolean isAlphanumeric(String text) {
		return text.matches("[a-zA-Z0-9]");
	}

	private void moveFocusToNextTextField(TextField currentTextField) {
		// Encontrar o índice do TextField atual no pai
		int index = ((GridPane) currentTextField.getParent()).getChildren().indexOf(currentTextField);

		// Encontrar o próximo TextField no pai
		int nextIndex = index + 1;
		if (nextIndex < ((GridPane) currentTextField.getParent()).getChildren().size()) {
			TextField nextTextField = (TextField) ((GridPane) currentTextField.getParent()).getChildren()
					.get(nextIndex);
			nextTextField.requestFocus();
		}
	}

	private void moveFocusToPreviousTextField(TextField currentTextField) {
		int index = ((GridPane) currentTextField.getParent()).getChildren().indexOf(currentTextField);
		int previousIndex = index - 1;
		if (previousIndex >= 0) {
			TextField previousTextField = (TextField) ((GridPane) currentTextField.getParent()).getChildren()
					.get(previousIndex);
			previousTextField.requestFocus();
		}
	}

	@FXML
	private void onSairAcertePalavra() {
		Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);
	}

	@FXML
	private void onFinalizarAcertePalavra() {
		List<AcertePalavraQuestaoVO> questoes = new ArrayList<>();
		questoes = new AcertePalavraQuestaoBO().consultarQuestoesId(id_jogo);
	 
		String palavra = "";

		for (int i = 0; i < questoes.size() && i < salvarTextFields.length; i++) {
			palavra = "";
			for (int j = 0; j < questoes.get(i).getPalavra().length(); j++) {
				palavra += salvarTextFields[i][j].getText().toUpperCase();
			}
			
			 
			boolean acertou = new AcertePalavraQuestaoBO().verificarResposta(palavra, id_jogo, i);
			
			if (acertou) {
				acertos++;
				for (int l = 0; l < questoes.get(i).getPalavra().length(); l++) {
					this.getSalvarTextFields()[i][l].setStyle("-fx-background-color: green;");
				}
			}
		}
		
		for (int i = 0; i < questoes.size() && i < salvarTextFields1.length; i++) {
			palavra = "";

			for (int j = 0; j < questoes.get(i+5).getPalavra().length(); j++) {
	 
				 
				palavra += salvarTextFields1[i][j].getText().toUpperCase();
			}
			
			// verificando as ultimas 5 palavras
			boolean acertou = new AcertePalavraQuestaoBO().verificarResposta(palavra, id_jogo, i + 5);
			
			
			if (acertou) {
				acertos++;
				
				for (int l = 0; l < questoes.get(i+5).getPalavra().length(); l++) {
					
					this.getSalvarTextFields1()[i][l].setStyle("-fx-background-color: green;");
				}
			}

		}


		  Platform.runLater(() -> { //usado para dar um delay na aplicaçao(para dar tempo dos quadrados ficarem verdes)
			  JOptionPane.showMessageDialog(null, "Você acertou "+acertos+" Palavras", "Acertos", JOptionPane.INFORMATION_MESSAGE);
			  Servicos.chamarTela("/view/MenuUsuarioView.fxml", userAluno, MenuUsuarioController.class);
			  Object[] options = { "Sim", "Não" };
				int opcao = JOptionPane.showOptionDialog(null, "Quer ser direcionado ao Formulario?", "Google Forms", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//se opcao for igual a 0 o usuario clicou no botão sim; se for igual a 1 clicou em nao; se somente fechou o aviso é -1
				if(opcao==0) {
					String link = "https://forms.gle/QpZABsBGxx4nrHWu7"; //adcionar o link do formulario
					Servicos.chamarLink(link);
				}
		     
		    });

	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public TextField[][] getSalvarTextFields() {
		return salvarTextFields;
	}

	public void setSalvarTextFields(TextField[][] salvarTextFields) {
		this.salvarTextFields = salvarTextFields;
	}

	public TextField[] getSalvarPalavra() {
		return salvarPalavra;
	}

	public void setSalvarPalavra(TextField[] salvarPalavra) {
		this.salvarPalavra = salvarPalavra;
	}

	public Timestamp getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Timestamp tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public String getId_jogo() {
		return id_jogo;
	}

	public void setId_jogo(String id_jogo) {
		this.id_jogo = id_jogo;
	}

	public Label getLblDica() {
		return lblDica;
	}

	public void setLblDica(Label lblDica) {
		this.lblDica = lblDica;
	}

	public String getUserAluno() {
		return userAluno;
	}

	public void setUserAluno(String userAluno) {
		this.userAluno = userAluno;
	}

	public AnchorPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(AnchorPane rootPane) {
		this.rootPane = rootPane;
	}

	public GridPane getGrid1() {
		return grid1;
	}

	public void setGrid1(GridPane grid1) {
		this.grid1 = grid1;
	}

	public TextField[][] getSalvarTextFields1() {
		return salvarTextFields1;
	}

	public void setSalvarTextFields1(TextField[][] salvarTextFields1) {
		this.salvarTextFields1 = salvarTextFields1;
	}
}
