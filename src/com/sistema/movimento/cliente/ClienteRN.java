package com.sistema.movimento.cliente;

import com.sistema.movimento.util.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	public ClienteRN(){
		this.clienteDAO = DAOFactory.criaClienteDAO();
		
	}

	public void salvar(Cliente c1) {
		
		this.clienteDAO.salvar(c1);
		
	}

}
