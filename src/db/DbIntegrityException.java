package db;
/*
 * trata as exce��es dos Ids nos casos de deletar   
 */
public class DbIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
