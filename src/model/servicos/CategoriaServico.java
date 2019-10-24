package model.servicos;

import java.util.List;

import model.dao.CategoriaDao;
import model.dao.FabricaDao;
import model.entidades.Categoria;

public class CategoriaServico {
	
	private CategoriaDao dao = FabricaDao.criaCategoriaDao();
	
	public List<Categoria> buscarTodos() {
		return dao.buscarTodasCategorias();
	}
	
	public void SalvarOuAtualizar(Categoria obj) {
		if(obj.getId() == null) {
			dao.InserirCategoria(obj);
		}
		else {
			dao.atualizarCategoria(obj);
		}
	}
}
