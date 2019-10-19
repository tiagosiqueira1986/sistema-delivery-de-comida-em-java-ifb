package model.servicos;

import java.util.ArrayList;
import java.util.List;

import model.entidades.Categoria;

public class CategoriaServico {
	
	public List<Categoria> buscarTodos() {
		List<Categoria> lista = new ArrayList<>();
		lista.add(new Categoria(1, "Lanches"));
		lista.add(new Categoria(2, "Bebidas"));
		lista.add(new Categoria(3, "Acompanhamentos"));
		return lista;
	}
}
