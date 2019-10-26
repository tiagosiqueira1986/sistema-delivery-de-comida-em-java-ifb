package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Categoria;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {
	
	//conexão com o banco de dados
	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void inserirProduto(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO produto "
					+ "(nome, descricao, preco, categoriaId) "
					+ "VALUES "
					+ "(?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getDescricao());
			st.setInt(2, obj.getQtdEstoque());
			st.setDouble(3, obj.getPreco());
			st.setInt(4, obj.getCategoria().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				}else {
					throw new DbException("Erro inexperado! Nenhum registro foi inserido!");
				}
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizarProduto(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE produto " 
					+"SET descricao = ?, qtdEstoque = ?, preco = ?, departamentoId = ? " 
					+"WHERE Id = ? "
					);
			st.setString(1, obj.getDescricao());
			st.setInt(2, obj.getQtdEstoque());
			st.setDouble(3, obj.getPreco());
			st.setInt(4, obj.getCategoria().getId());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarProdutoPorId(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM vendedor WHERE id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Produto buscarProdutoPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.depNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.departamentoId = departamento.id "
					+ "WHERE vendedor.id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) { // testa se trouxe algum resultado na consulta
				Categoria dep = instanciaCategoria(rs);
				Produto obj = instanciaProduto(rs, dep);
				return obj;
			}
			return null;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}
		
	}

	//métodos que instância de vendedor e departamento
	private Produto instanciaProduto(ResultSet rs, Categoria dep) throws SQLException {
		Produto obj = new Produto();
		obj.setId(rs.getInt("id"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setQtdEstoque(rs.getInt("qtdEstoque"));
		obj.setPreco(rs.getDouble("preco"));
		obj.setCategoria(dep);
		return obj;
	}

	private Categoria instanciaCategoria(ResultSet rs) throws SQLException {
		Categoria dep = new Categoria();
		dep.setId(rs.getInt("categoriaId"));
		dep.setNome(rs.getString("catNome"));
		return dep;
	}

	@Override
	public List<Produto> buscarTodosProdutos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT produto.*, departamento.catNome "
					+ "FROM produto INNER JOIN categoria "
					+ "ON produto.departamentoId = categoria.Id "
					+ "ORDER BY nome ");
			
			rs = st.executeQuery();
			
			List<Produto> lista = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();
			
			while (rs.next()) { // testa se trouxe algum resultado na consulta
				
				Categoria dep = map.get(rs.getInt("categoriaId"));
				
				if(dep == null) {
					dep = instanciaCategoria(rs);
					map.put(rs.getInt("categoriaId"), dep);
				}	

				Produto obj = instanciaProduto(rs, dep);
				lista.add(obj);
			}
			return lista;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}	
	}

	@Override
	public List<Produto> buscarProdutoPorDepartamento(Categoria categoria) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT produto.*, categoria.depNome "
					+ "FROM produto INNER JOIN departamento "
					+ "ON produto.categoriaId = categoria.Id "
					+ "WHERE categoriaId = ? "
					+ "ORDER BY nome ");
			
			st.setInt(1, categoria.getId());
			
			rs = st.executeQuery();
			
			List<Produto> lista = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();
			
			while (rs.next()) { // testa se trouxe algum resultado na consulta
				
				Categoria cat = map.get(rs.getInt("categoriaId"));
				
				if(cat == null) {
					cat = instanciaCategoria(rs);
					map.put(rs.getInt("categoriaId"), cat);
				}	

				Produto obj = instanciaProduto(rs, cat);
				lista.add(obj);
			}
			return lista;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}	
	}
}
