package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
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
import model.entities.Categoria;
import model.exceptions.ValidadorDeExcecoes;
import model.services.CategoriaServico;

public class FormControleCategoria implements Initializable{
	
	//dependencia para catgoria - entidade relacionada ao formulario
	private Categoria categ;
	
	//dependencia categoria servico
	private CategoriaServico servico;
	
	//cria uma lista com os dados modificados para atualizar a lista após as alterações
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	//sobrescrever os dados da lista
	public void subscribeDataChanceListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
			notifyDataChangeListeners();
			Utils.StageAtual(event).close();
		}
		catch(ValidadorDeExcecoes e) {
			setMensagemDeErro(e.getErros());
		}
		catch (DbException e){
			Alertas.mostrarAlerta("Erro ao salvar...", null,e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.atualizarDados();
		}
		
	}

	private Categoria getFormData() {
		Categoria obj = new Categoria();
		
		ValidadorDeExcecoes excecao = new ValidadorDeExcecoes("Erro de validação...");
		obj.setId(Utils.passarParaInt(txtId.getText()));
		
		if(txtNome.getText() == null || txtNome.getText().trim().equals(" "));
			excecao.adicionaErro("nome","O campo não pode ser vazio...");
		obj.setNome(txtNome.getText());
		
		if(excecao.getErros().size() > 0) {
			throw excecao;
		}
		
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
	
	private void setMensagemDeErro(Map<String, String> erros) {
		Set<String> campos = erros.keySet();
		
		if(campos.contains("nome")) {
			labelDeErroNome.setText(erros.get("nome"));		}
	}
}
