package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldBordaColorida extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();

        // Adicionando um listener para o evento de tecla pressionada
        textField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            // Verificando se o texto está vazio
            if (textField.getText().isEmpty()) {
                // Se estiver vazio, definir a cor da borda como vermelha
                textField.setStyle("-fx-border-color: red;");
            } else {
                // Se não estiver vazio, definir a cor da borda como verde
                textField.setStyle("-fx-border-color: green;");
            }
        });

        VBox root = new VBox(textField);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("TextField com Borda Colorida");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



