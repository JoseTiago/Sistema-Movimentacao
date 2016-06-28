package com.sistema.movimento;

import org.hibernate.Session;
import org.jboss.jandex.Main;

public class Connector {
	
	public static void main(String[] args) {
		Session sessao = null;
		
		try {
			sessao = HibernateUtil.getSession().openSession();
			System.out.println("Conectou");
			
		}finally {
			System.out.println("Fechou conexão");
		} 
	}
	
}
