package model.entities;

import java.io.Serializable;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Integer qtdEstoque;
	private Double preco;

	private Categoria categoria; // o produto deve possuir uma categoria
	
	//construtor
	public Produto() { //construtor padrão
	}

	public Produto(Integer id, String descricao, Integer qtdEstoque, Double preco, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.preco = preco;
		this.categoria = categoria;
	}

	//get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	//hascode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	//toString
	@Override
	public String toString() {
		return "Produto [id=" + id 
				+ ", descricao=" + descricao 
				+ ", qtdEstoque=" + qtdEstoque 
				+ ", preco=" + preco
				+ ", categoria=" + categoria + "]";
	}
	

	
}
