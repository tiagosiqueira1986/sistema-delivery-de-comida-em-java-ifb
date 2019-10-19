package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	// itens de controle de tela que vão corresponder ao controles visuais da tela
	@FXML
	private MenuItem menuItemProduto;
	
	@FXML
	private MenuItem menuItemCategoria;
	
	@FXML
	private MenuItem menuItemAjuda;
	
	// métodos que irão tratar as ações dos item do menu
	@FXML
	public void onMenuItemProdutoAcao() {
		System.out.println("onMenuItemProdutoAcao");
	}
	
	@FXML
	public void onMenuItemCategoriaAcao() {
		carregarTela("/gui/ListaDeCategorias.fxml");
	}
	
	@FXML
	public void onMenuItemAjudaAcao() {
		carregarTela("/gui/Sobre.fxml");
	}
	
	// função que abre uma tela secundária a partir da tela principal
	private synchronized void carregarTela(String caminhoAbsoluto) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoAbsoluto));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); //VBox da janela principal, chama o primeiro elemento da janela principal
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch(IOException e) {
			Alertas.showAlert("IO Exception", "Erro ao carregar a Tela", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
}
