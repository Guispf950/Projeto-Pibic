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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.bo.QuizBO;
import model.bo.QuizQuestaoBO;
import model.vo.QuizQuestaoVO;
import model.vo.QuizVO;

public class FlashCardAlterarController implements Initializable{
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
	private TextField txtTiuloQuiz;
	@FXML
	private ListView<String> listView = new ListView<>();

	private String userProf;

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
					if (event.getClickCount() == 2) {
						String selectedItem = getItem(); // Obtém o item clicado
						chamarTelaAlterar(listaQuiz.get(getIndex()).getId(), listaQuiz.get(getIndex()).getNomeQuiz());

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
					//Image image = new Image(getClass().getResourceAsStream("Questao.png"));
					//imageView.setImage(image);
					//imageView.setFitWidth(50); // Ajuste o tamanho conforme necessário
					//imageView.setFitHeight(50);

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

	}

	@FXML
	public void onBttnExcluir() {
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

	public void chamarTelaAlterar(String id, String nome) {
		QuizQuestaoVO questao1 = new QuizQuestaoVO();
		questao1 = new QuizQuestaoBO().primeiraQuestaoQuiz(id);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizAlterarQuestao.fxml"));
			Parent parent = loader.load();
			QuizAlterarQuestao quizAlterarQuestao = loader.getController();
			quizAlterarQuestao.setId_jogo(id);
			quizAlterarQuestao.setUserProf(userProf);
			quizAlterarQuestao.getTxtTiuloQuiz().setText(nome);
			quizAlterarQuestao.getTxtTiuloQuiz().setEditable(false);
			quizAlterarQuestao.getTxtPergunta().setText(questao1.getPergunta());
			quizAlterarQuestao.getTxtAlternativaA().setText(questao1.getAlternativaA());
			quizAlterarQuestao.getTxtAlternativaB().setText(questao1.getAlternativaB());
			quizAlterarQuestao.getTxtAlternativaC().setText(questao1.getAlternativaC());
			quizAlterarQuestao.getTxtAlternativaD().setText(questao1.getAlternativaD());
			if (questao1.getResposta().equals(questao1.getAlternativaA())) {
			    quizAlterarQuestao.getRbAlternativaA().setSelected(true);
			} else if (questao1.getResposta().equals(questao1.getAlternativaB())) {
			    quizAlterarQuestao.getRbAlternativaB().setSelected(true);
			} else if (questao1.getResposta().equals(questao1.getAlternativaC())) {
			    quizAlterarQuestao.getRbAlternativaC().setSelected(true);
			} else if (questao1.getResposta().equals(questao1.getAlternativaD())) {
			    quizAlterarQuestao.getRbAlternativaD().setSelected(true);
			}
			quizAlterarQuestao.initialize(null, null);//Setar todos os valores da primeira questao

			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public String getUserProf() {
		return userProf;
	}

	public void setUserProf(String idAutor) {
		this.userProf = idAutor;
	}
}
