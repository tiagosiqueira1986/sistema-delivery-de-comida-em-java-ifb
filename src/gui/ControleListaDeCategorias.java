package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entidades.Categoria;
import model.servicos.CategoriaServico;

public class ControleListaDeCategorias implements Initializable {
	
	//declaração de dependencia
	private CategoriaServico servico;
	
	// variáveis para controle dos itens de fórmulario
	@FXML
	private TableView<Categoria> tableViewCategoria; //referência para tableView
	
	@FXML
	private TableColumn<Categoria, Integer> ColunaId;
	
	@FXML
	private TableColumn<Categoria, Integer> ColunaNome;
	
	@FXML
	private Button btNovo;
	
	private ObservableList<Categoria> obsList;
	
	@FXML
	public void onBtNovaAcao() {
		System.out.println("onBtNovaAcao");
	}
	
	public void setCategoriaServico(CategoriaServico servico) {
		this.servico = servico;
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
	
	public void atualizaTableView() {
		if(servico == null) {
			throw new IllegalStateException("O serviço é nulo");
		}
		List<Categoria> lista = servico.buscarTodos();
		obsList = FXCollections.observableArrayList(lista);
		tableViewCategoria.setItems(obsList);
	}

}
