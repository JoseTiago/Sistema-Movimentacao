package com.sistema.movimento;

import org.hibernate.Session;


public class Connector {
	
	
	public static void main(String[] args) {
		Session sessao = null;
		
		try {
			sessao = HibernateUtil.getSession().openSession();
			boolean connected = sessao.isConnected();
			
			if(connected)
				System.out.println("Conectou");
			
		}finally {
			sessao.close();
			System.out.println("Fechou conex�o");
		} 
	}
	
}
