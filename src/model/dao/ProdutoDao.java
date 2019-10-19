package model.dao;

import java.util.List;

import model.entidades.Categoria;
import model.entidades.Produto;

public interface ProdutoDao {
	
	void inserirProduto(Produto obj);
	void atualizarProduto(Produto obj);
	void deletarProdutoPorId(Integer id);
	Produto buscarProdutoPorId(Integer id);
	List<Produto> buscarTodosProdutos();
	List<Produto> buscarProdutoPorDepartamento(Categoria departamento);
}
