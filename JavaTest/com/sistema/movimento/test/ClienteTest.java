package com.sistema.movimento.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sistema.movimento.HibernateUtil;
import com.sistema.movimento.cliente.Cliente;
import com.sistema.movimento.cliente.ClienteRN;

import junit.framework.Assert;

public class ClienteTest {
	
	private static Session sessao;
	private static Transaction transacao;
	
	
	@BeforeClass
	public static void abreConexao(){
		sessao = HibernateUtil.getSession().getCurrentSession();
		transacao = sessao.beginTransaction();
		
	}
	
	@AfterClass
	public static void fechaConexao(){
		
		try {
			transacao.commit();
		} catch (Throwable e) {
			System.out.println("Deu problema no Commit: " + e.getMessage());
		}finally{
			
			try {
				if(sessao.isOpen()){
					sessao.close();
				}
			} catch (Exception e2) {
				System.out.println("Deu problema no fechamento da conexao : " + e2.getMessage());
			}
			
		}
	}
	
	@Test
	public void salvarTest(){
		
		Cliente c1 = new Cliente();
		
		c1.setNome("Ronaldo");
		c1.setEndereco("Rua Teste");
		c1.setRenda(5000f);
		c1.setCpf("123456789");
		
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(c1);
		
		assertEquals(true, true);
	}

}
