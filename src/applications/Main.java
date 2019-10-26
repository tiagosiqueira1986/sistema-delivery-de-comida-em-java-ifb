package applications;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene; // guarda a cena da tela principal
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane); //cena da janela principal
			primaryStage.setScene(mainScene); // palco da janela principal
			primaryStage.setTitle("Burguer Delivery");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//método para pegar a cena principal
	public static Scene getMainScene() {
		return mainScene;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
