package db;
/*
 * Cria��o de classe para poss�veis erros no acesso aos dados no banco de dados
 * 
 */
public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) { // for�a a cria��o da exce��o atrav�s de um construtor 
		super(msg);
	}
}
