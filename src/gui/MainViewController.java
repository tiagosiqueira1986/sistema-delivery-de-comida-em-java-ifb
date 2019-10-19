package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("onMenuItemCategoriaAcao");
	}
	
	@FXML
	public void onMenuItemAjudaAcao() {
		System.out.println("onMenuItemAjudaAcao");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {	
	}
}
