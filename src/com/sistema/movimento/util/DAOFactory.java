package com.sistema.movimento.util;

import com.sistema.movimento.HibernateUtil;
import com.sistema.movimento.cliente.ClienteDAO;
import com.sistema.movimento.cliente.ClienteDAOHibernate;

public class DAOFactory {

	public static ClienteDAO criaClienteDAO() {
		ClienteDAOHibernate clienteDAOHibernate = new ClienteDAOHibernate();
		
		clienteDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		
		return clienteDAOHibernate;
	}
	
}
