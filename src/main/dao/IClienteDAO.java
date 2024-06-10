package main.dao;

import java.util.List;

import main.domain.Cliente;

public interface IClienteDAO {
	public Integer cadastrar(Cliente cliente) throws Exception;
	
	public Integer atualizar(Cliente cliente) throws Exception;

	public Cliente consultar(String codigo) throws Exception;
	
	public List<Cliente> buscarTodos() throws Exception;

	public Integer excluir(Cliente cliente) throws Exception;
}
