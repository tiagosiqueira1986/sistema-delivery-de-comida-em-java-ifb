package model.dao;

import db.DB;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
/*
 * Serve para no momento da instânciação o objeto não acessar a implementação 
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
