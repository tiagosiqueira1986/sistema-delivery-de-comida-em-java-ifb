package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Restricoes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Categoria;

public class FormControleCategoria implements Initializable{
	
	//dependencia para catgoria - entidade relacionada ao formulario
	@FXML
	private Categoria categ;
	
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
	
	//controle dos eventos dos botões
	@FXML
	public void acaoDeSalvar() {
		System.out.println("onBtSaveAction");
	}
	
	@FXML
	public void acaoDeCancelar() {
		System.out.println("onBtCancelarAction");
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
