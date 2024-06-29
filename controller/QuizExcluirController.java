package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.bo.QuizBO;
import model.dao.QuizDAO;
import model.vo.QuizVO;
import view.util.Alerts;

public class QuizExcluirController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private AnchorPane anchorPane2;
	@FXML
	private Button bttnCriar;
	@FXML
	private Button bttnConsultar;
	@FXML
	private Button bttnAlterar;
	@FXML
	private Button bttnExcluir;
	@FXML
	private Button bttnVoltar;
	@FXML
	private Label lblCriar;
	@FXML
	private Label lblConsultar;
	@FXML
	private Label lblAlterar;
	@FXML
	private Label lblExcluir;
	@FXML
	private Label lblVoltar;
	@FXML
	private TextField txtTituloQuiz;
	@FXML
	private Label lblQuestao;
	@FXML
	private TextArea txtPergunta;
	@FXML
	private TextField txtAlternativaA;
	@FXML
	private TextField txtAlternativaB;
	@FXML
	private TextField txtAlternativaC;
	@FXML
	private TextField txtAlternativaD;
	@FXML
	private RadioButton rbAlternativaA;
	@FXML
	private RadioButton rbAlternativaB;
	@FXML
	private RadioButton rbAlternativaC;
	@FXML
	private RadioButton rbAlternativaD;
	@FXML
	private Button bttnVoltarQuestao;
	@FXML
	private Button bttnAdicionarQuestao;
	@FXML
	private Button bttnFinalizarQuiz;
	private String userProf;

	@FXML
	private TextField txtTiuloQuiz;
	@FXML
	private ListView<String> listView = new ListView<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<QuizVO> listaQuiz = new QuizBO().consultarQuizProf(userProf);
		for (int i = 0; i < listaQuiz.size(); i++) {
			listView.getItems().add(listaQuiz.get(i).getNomeQuiz());

		}

		// Definir a fábrica de células com imagens e botões
		listView.setCellFactory(param -> new ListCell<String>() {
			private final HBox hbox = new HBox();
			private final ImageView imageView = new ImageView();
			private final Button button = new Button("Consultar");

			{
				// Configurar o layout da célula
				hbox.setAlignment(Pos.CENTER_LEFT); // Centralizar elementos no HBox
				hbox.getChildren().addAll(imageView, button);
				hbox.setSpacing(10); // Ajuste o espaçamento conforme necessário
				// Adicionar um evento de clique duplo
				hbox.setOnMouseClicked(event -> {

					if (showConfirmationDialog("Você tem certeza que deseja excluir esse quiz?")) {
						new QuizDAO().excluirQuiz(listaQuiz.get(getIndex()).getId());
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizExcluir.fxml"));
							Parent parent = loader.load();
							QuizExcluirController quizExcluirController = loader.getController();
							
							quizExcluirController.setUserProf(userProf);
							quizExcluirController.initialize(null, null);
							 
							Scene scene = new Scene(parent);
							Main.getStage().setScene(scene);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Usuário clicou em Cancelar");
						// Adicione aqui o código que você deseja executar se o usuário clicar em
						// Cancelar
					}
				});
			}

			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null) {
					setGraphic(null);
					setText(null);
				} else {
					// Carregar a imagem
					Image image = new Image(getClass().getResourceAsStream("Questao.png"));
					imageView.setImage(image);
					imageView.setFitWidth(50); // Ajuste o tamanho conforme necessário
					imageView.setFitHeight(50);

					Text text = new Text(item);
					text.setFont(Font.font("Monospaced", FontWeight.BOLD, 25));
					button.setStyle("-fx-background-color: #576CD9;" + "-fx-border-color:  #FFF;"
							+ "-fx-border-width: 1px;" + "-fx-font-size: 20;" + "-fx-text-fill: white;"
							+ "fx-backgroud-radius: 5;" + "fx-border-radius: 10;"

					);

					button.setMinWidth(150); // Ajuste a largura conforme necessário
					button.setMinHeight(40);

					// Definir a ação do botão
					button.setOnAction(event -> {
						// Lógica para ação do botão
						System.out.println("Executando ação para: " + item);
					});

					// Adicionar elementos ao HBox
					hbox.getChildren().clear();
					hbox.getChildren().addAll(imageView, text);

					setGraphic(hbox);
					setText(null);
				}
			}
		});

	}

	private boolean showConfirmationDialog(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText(null);
		alert.setContentText(message);

		// Botões do diálogo
		ButtonType buttonTypeOK = new ButtonType("OK");
		ButtonType buttonTypeCancel = new ButtonType("Cancelar");

		alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

		// Exibe o diálogo e aguarda a resposta do usuário
		return alert.showAndWait().orElse(ButtonType.CANCEL) == buttonTypeOK;
	}

	public void chamarTelaAlterar(String id, String nome) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizConsultarQuestoes.fxml"));
			Parent parent = loader.load();
			QuizConsultarQuestoesController quizAlterarQuestao = loader.getController();
			quizAlterarQuestao.setUserProf(userProf);
			quizAlterarQuestao.getTxtTituloQuiz().setText(nome);
			quizAlterarQuestao.configurarDados(id, userProf);
			quizAlterarQuestao.initialize(null, null);

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onBttnCriar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizCriarView.fxml"));
			Parent parent = loader.load();
			QuizCriarController quizCriarController = loader.getController();
			quizCriarController.setUserProf(userProf);

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
		}
	}

	@FXML
	public void onBttnConsultar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizConsultarView.fxml"));
			Parent parent = loader.load();
			QuizConsultarController quizConsultarController = loader.getController();
			
			quizConsultarController.setUserProf(userProf); // Configurar userProf antes de chamar initialize
			quizConsultarController.initialize(null, null);
			 
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onBttnAlterar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizAlterarView.fxml"));
			Parent parent = loader.load();
			QuizAlterarController quizConsultarController = loader.getController();
			
			quizConsultarController.setUserProf(userProf); // Configurar userProf antes de chamar initialize
			quizConsultarController.initialize(null, null);
			 
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	@FXML
	public void onBttnVoltar() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuProfessorView.fxml"));
		Parent parent;
		try {
			parent = loader.load();
			MenuUsuarioController tela1 = loader.getController();
			tela1.getLblUser().setText(userProf);
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	public void onBttnExcluir() {
		// JA ESTA NESTA TELA
	}

	private void mudarCena(String absoluteView) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteView));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Main.setMainScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TextArea getTxtPergunta() {
		return txtPergunta;
	}

	public void setTxtPergunta(TextArea txtPergunta) {
		this.txtPergunta = txtPergunta;
	}

	public TextField getTxtAlternativaA() {
		return txtAlternativaA;
	}

	public void setTxtAlternativaA(TextField txtAlternativaA) {
		this.txtAlternativaA = txtAlternativaA;
	}

	public TextField getTxtAlternativaB() {
		return txtAlternativaB;
	}

	public void setTxtAlternativaB(TextField txtAlternativaB) {
		this.txtAlternativaB = txtAlternativaB;
	}

	public TextField getTxtAlternativaC() {
		return txtAlternativaC;
	}

	public void setTxtAlternativaC(TextField txtAlternativaC) {
		this.txtAlternativaC = txtAlternativaC;
	}

	public TextField getTxtAlternativaD() {
		return txtAlternativaD;
	}

	public void setTxtAlternativaD(TextField txtAlternativaD) {
		this.txtAlternativaD = txtAlternativaD;
	}

	public RadioButton getRbAlternativaA() {
		return rbAlternativaA;
	}

	public void setRbAlternativaA(RadioButton rbAlternativaA) {
		this.rbAlternativaA = rbAlternativaA;
	}

	public RadioButton getRbAlternativaC() {
		return rbAlternativaC;
	}

	public void setRbAlternativaC(RadioButton rbAlternativaC) {
		this.rbAlternativaC = rbAlternativaC;
	}

	public RadioButton getRbAlternativaD() {
		return rbAlternativaD;
	}

	public void setRbAlternativaD(RadioButton rbAlternativaD) {
		this.rbAlternativaD = rbAlternativaD;
	}

	public String getUserProf() {
		return userProf;
	}

	public TextField getTxtTituloQuiz() {
		return txtTituloQuiz;
	}

	public void setTxtTituloQuiz(TextField txtTituloQuiz) {
		this.txtTituloQuiz = txtTituloQuiz;
	}

	public void setUserProf(String userProf) {
		this.userProf = userProf;
	}

}