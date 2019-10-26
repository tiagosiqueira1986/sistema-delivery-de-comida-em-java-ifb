package model.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.Produto;

public interface ProdutoDao {
	
	void inserirProduto(Produto obj);
	void atualizarProduto(Produto obj);
	void deletarProdutoPorId(Integer id);
	Produto buscarProdutoPorId(Integer id);
	List<Produto> buscarTodosProdutos();
	List<Produto> buscarProdutoPorDepartamento(Categoria departamento);
}
