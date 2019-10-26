package model.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {
	
	void InserirCategoria(Categoria obj);
	void atualizarCategoria(Categoria obj);
	void DeletarPorId(Integer id);
	Categoria buscarPorId(Integer id);
	List<Categoria> buscarTodasCategorias();
	
}
