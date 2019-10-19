package db;
/*
 * Criação de classe para possíveis erros no acesso aos dados no banco de dados
 * 
 */
public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) { // força a criação da exceção através de um construtor 
		super(msg);
	}
}
