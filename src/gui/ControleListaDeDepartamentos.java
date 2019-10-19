package gui;

import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entidades.Categoria;

public class ControleListaDeDepartamentos implements Initializable {

	// variáveis para controle dos itens de fórmulario
	@FXML
	private TableView<Categoria> tableViewCategoria; //referência para tableView
	
	@FXML
	private TableColumn<Categoria, Integer> ColunaId;
	
	@FXML
	private TableColumn<Categoria, Integer> ColunaNome;
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovaAcao() {
		System.out.println("onBtNovaAcao");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		InicializeNodes();
	}
	
	private void InicializeNodes() {
		ColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewCategoria.prefHeightProperty().bind(stage.heightProperty());
	}

}
