package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 *  Criação da classe com métodos estáticos para connectar e desconectar
 *  
 */
public class DB {
	
	//objeto que vai realizar a conecção no banco de dados
	private static Connection conn = null;
	
	//método para conectar no banco de dados
	//obs: conectar ao banco de dados é o mesmo que instanciar um objeto do tipo connection
	public static Connection getConnection() {
		if(conn == null) {
			try { // trata uma possível exceção de SQL
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//método para desconectar no banco de dados
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} 
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//carrega os dados da classe properties e guarda em um Objeto
	//abre o aquivo properties, lê e guarda no Objeto
	public static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//métodos auxiliares para fechar objetos
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
