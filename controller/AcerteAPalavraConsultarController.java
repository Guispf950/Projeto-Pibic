package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AcerteAPalavraConsultarController implements Initializable{
	
	@FXML ListView<String> listView = new ListView<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Criar ListView
        
        listView.getItems().addAll("Palavrass em ingles", "Ciências", "Filmes e Séries");

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
    						+ "-fx-border-width: 1px;" + "-fx-font-size: 20;"+ "-fx-text-fill: white;" + "fx-backgroud-radius: 5;" + "fx-border-radius: 10;"

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
		mudarCena("/view/QuizCriarView.fxml");	
	}
	
	@FXML
	public void onBttnConsultar() {
		mudarCena("/view/QuizConsultarView.fxml");	
	}
	
	@FXML
	public void onBttnAlterar() {
		mudarCena("/view/QuizAlterarView.fxml");	
	}
	
	@FXML
	public void onBttnExcluir() {
		mudarCena("/view/QuizExcluirView.fxml");	
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

}

/*

public class ListViewComImagensE Botoes extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ListView com Imagens e Botões");

        // Criar ListView
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Maçã", "Banana", "Cereja");

        // Definir a fábrica de células com imagens e botões
        listView.setCellFactory(param -> new ListCell<String>() {
            private final HBox hbox = new HBox();
            private final ImageView imageView = new ImageView();
            private final Button button = new Button("Executar");

            {
                // Configurar o layout da célula
                hbox.getChildren().addAll(imageView, button);
                hbox.setSpacing(10); // Ajuste o espaçamento conforme necessário
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    // Carregar a imagem
                    Image image = new Image(getClass().getResourceAsStream("caminho/para/sua/imagem/" + item.toLowerCase() + ".png"));
                    imageView.setImage(image);
                    imageView.setFitWidth(20); // Ajuste o tamanho conforme necessário
                    imageView.setFitHeight(20);

                    // Definir a ação do botão
                    button.setOnAction(event -> {
                        // Lógica para ação do botão
                        System.out.println("Executando ação para: " + item);
                    });

                    setGraphic(hbox);
                    setText(item);
                }
            }
        });

        // Criar cena
        Scene scene = new Scene(listView, 300, 200);

        // Configurar a cena e exibir a janela
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
*/