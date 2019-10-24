package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alertas;
import gui.util.Restricoes;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Categoria;
import model.servicos.CategoriaServico;

public class FormControleCategoria implements Initializable{
	
	//dependencia para catgoria - entidade relacionada ao formulario
	private Categoria categ;
	
	//dependencia categoria servico
	private CategoriaServico servico;
	
	//controle das caixas do formulário de cadastro de categoria
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	//controle da label de erro
	@FXML
	private Label labelDeErroNome;
	
	//controle dos botões 
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	public void setCategoria(Categoria categ) {
		this.categ = categ;
	}
	
	public void setCategoriaServico(CategoriaServico servico) {
		this.servico = servico;
	}
	
	//controle dos eventos dos botões
	@FXML
	public void acaoDeSalvar(ActionEvent event) {
		if(categ == null) {
			throw new IllegalStateException("Categoria Nula...");
		}
		if(servico == null) {
			throw new IllegalStateException("Serviço Nulo...");
		}
		try {
			categ = getFormData();
			servico.SalvarOuAtualizar(categ);
			Utils.StageAtual(event).close();
		}
		catch (DbException e){
			Alertas.mostrarAlerta("Erro ao salvar...", null,e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Categoria getFormData() {
		Categoria obj = new Categoria();
		
		obj.setId(Utils.passarParaInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		
		return obj;
	}

	@FXML
	public void acaoDeCancelar(ActionEvent event) {
		Utils.StageAtual(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		restricoesDeEntrada();	
	}
	
	//retrições de entrada
	private void restricoesDeEntrada() {
		Restricoes.setTextFieldDouble(txtId);
		Restricoes.setTextFieldMaxLength(txtNome, 30);
	}
	
	public void atualizaForm() {
		if(categ == null) {
			throw new IllegalStateException("Categoria nula");
		}
		txtId.setText(String.valueOf(categ.getId()));
		txtNome.setText(categ.getNome());
	}
	
}
