package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import applications.Main;
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
import model.services.CategoriaServico;

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
		carregarTela("/gui/ListaDeCategorias.fxml", (ControleListaDeCategoria controle) -> {
			controle.setCategoriaServico(new CategoriaServico());
			controle.atualizaTableView();
		});
	}
	
	@FXML
	public void onMenuItemAjudaAcao() {
		carregarTela("/gui/Sobre.fxml", x -> {});
	}
	
	// função que abre uma tela secundária a partir da tela principal
	private synchronized <T> void carregarTela(String caminhoAbsoluto, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoAbsoluto));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); //VBox da janela principal, chama o primeiro elemento da janela principal
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controle = loader.getController();
			initializingAction.accept(controle); 
		}
		catch(IOException e) {
			Alertas.mostrarAlerta("IO Exception", "Erro ao carregar a Tela", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
}
