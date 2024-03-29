package model.dao;

import db.DB;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
/*
 * Serve para no momento da inst�ncia��o o objeto n�o acessar a implementa��o 
 * e sim a Interface
 */
public class FabricaDao {
	
	public static ProdutoDao criaProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
	
	public static CategoriaDao criaCategoriaDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}
}
