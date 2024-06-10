package test.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.dao.IProdutoDAO;
import main.dao.ProdutoDAO;
import main.domain.Produto;

public class ProdutoTest {
	
	@Test
	public void cadastrarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Nescal");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Nescal");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertTrue(qtdDel == 1);
	}
	
	@Test
	public void excluirTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Nescal");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertTrue(qtdDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Nescal");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produto2 = new Produto();
		produto2.setCodigo("02");
		produto2.setNome("Doritos");
		Integer qtd2 = dao.cadastrar(produto2);
		assertTrue(qtd2 == 1);
		
		List<Produto> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Produto prod : list) {
			dao.excluir(prod);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = dao.buscarTodos();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Nescal");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar("01");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		produtoBD.setCodigo("02");
		produtoBD.setNome("Doritos");
		Integer countUpdate = dao.atualizar(produtoBD);
		assertTrue(countUpdate == 1);
		
		Produto produtoBD1 = dao.consultar("01");
		assertNull(produtoBD1);
		
		Produto produtoBD2 = dao.consultar("02");
		assertNotNull(produtoBD2);
		assertEquals(produtoBD2.getId(), produtoBD.getId());
		assertEquals(produtoBD2.getCodigo(), produtoBD.getCodigo());
		assertEquals(produtoBD2.getNome(), produtoBD.getNome());
		
		List<Produto> list = dao.buscarTodos();
		for (Produto prod : list) {
			dao.excluir(prod);
		}
	}
}
