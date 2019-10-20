package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.Main;
import gui.util.Alertas;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entidades.Categoria;
import model.servicos.CategoriaServico;

public class ControleListaDeCategoria implements Initializable {
	
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
	public void onBtNovaAcao(ActionEvent evento) {
		Stage parentStage = Utils.StageAtual(evento);
		criaFormularioCadastro("/gui/FormularioDeCategoria.fxml", parentStage);
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
	
	private void criaFormularioCadastro(String nomeAbsoluto, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeAbsoluto));
			Pane pane = loader.load();
			
			Stage tempStage = new Stage();
			tempStage.setTitle("Insira os dados da nova categoria");
			tempStage.setScene(new Scene(pane));
			tempStage.setResizable(false);
			tempStage.initOwner(parentStage);
			tempStage.initModality(Modality.WINDOW_MODAL);
			tempStage.showAndWait();
		}
		catch(IOException e) {
			//e.printStackTrace();
			Alertas.mostrarAlerta("IO Exception", "Erro ao Carregar a Tela...", e.getMessage(), AlertType.ERROR);
		}
	}
}
