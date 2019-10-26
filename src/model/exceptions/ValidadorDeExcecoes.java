package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidadorDeExcecoes extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//chave valor
	private Map<String, String> erros = new HashMap<>();
	
	public ValidadorDeExcecoes(String msg) {
		super(msg);
	}
	
	public Map<String, String> getErros(){
		return erros;
	}
	
	public void adicionaErro(String nomeDoCampo, String mensagemDeErro) {
		erros.put(nomeDoCampo, mensagemDeErro);
	}
}
