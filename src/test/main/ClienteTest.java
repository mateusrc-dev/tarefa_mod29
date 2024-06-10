package test.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.dao.ClienteDAO;
import main.dao.IClienteDAO;
import main.domain.Cliente;

public class ClienteTest {
	
	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Mateus Carvalho");
		
		Integer qtd = dao.cadastrar(cliente);
		assertTrue(qtd == 1);
		
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Mateus Carvalho");
		Integer qtdCad = dao.cadastrar(cliente);
		assertTrue(qtdCad == 1);
		
		Cliente clienteBD = dao.consultar("01");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertTrue(qtdDel == 1);
	}
	
	@Test
	public void excluirTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Mateus Carvalho");
		Integer qtdCad = dao.cadastrar(cliente);
		assertTrue(qtdCad == 1);
		
		Cliente clienteBD = dao.consultar("01");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertTrue(qtdDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Mateus Carvalho");
		Integer qtdCad = dao.cadastrar(cliente);
		assertTrue(qtdCad == 1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setCodigo("02");
		cliente2.setNome("Pedro Silva");
		Integer qtdCad2 = dao.cadastrar(cliente2);
		assertTrue(qtdCad2 == 1);
		
		List<Cliente> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Cliente cli : list) {
			dao.excluir(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = dao.buscarTodos();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Mateus Carvalho");
		Integer qtdCad = dao.cadastrar(cliente);
		assertTrue(qtdCad == 1);
		
		Cliente clienteBD = dao.consultar("01");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		clienteBD.setCodigo("02");
		clienteBD.setNome("Mateus Raimundo");
		Integer countUpdate = dao.atualizar(clienteBD);
		assertTrue(countUpdate == 1);
		
		Cliente clienteBD1 = dao.consultar("01");
		assertNull(clienteBD1);
		
		Cliente clienteBD2 = dao.consultar("02");
		assertNotNull(clienteBD2);
		assertEquals(clienteBD2.getId(), clienteBD.getId());
		assertEquals(clienteBD2.getCodigo(), clienteBD.getCodigo());
		assertEquals(clienteBD2.getNome(), clienteBD.getNome());
		
		List<Cliente> list = dao.buscarTodos();
		for (Cliente cli : list) {
			dao.excluir(cli);
		}
	}
}
