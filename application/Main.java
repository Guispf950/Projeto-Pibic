

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene mainScene;
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void setMainScene(Scene mainScene) {
		Main.mainScene = mainScene;
	}

	@Override
	public void start(Stage stage) {
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			mainScene = new Scene(parent);
			stage.setScene(mainScene);
			stage.setMaximized(true);
			stage.show(); 
			setStage(stage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}
	//--module-path "C:\java-libs\javafx-sdk\lib" --add-modules=javafx.fxml,javafx.controls
}