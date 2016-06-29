package com.sistema.movimento.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sistema.movimento.HibernateUtil;
import com.sistema.movimento.produto.Produto;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import antlr.collections.List;

public class ProdutoTest {

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
	
	@Before
	public void setup(){
		
		Produto p1 = new Produto("lote2", "Regua", new Date(), 30, 2.5f);
		Produto p2 = new Produto("fardo", "Papel", new Date(), 300, 1.5f);
		Produto p3 = new Produto("edicao", "Livro", new Date(), 60, 30.0f);
		Produto p4 = new Produto("caixa", "Caneta", new Date(), 90, 1.5f);
		Produto p5 = new Produto("lote", "Caderno", new Date(), 50, 7.0f);
		
		sessao.save(p1);
		sessao.save(p2);
		sessao.save(p3);
		sessao.save(p4);
		sessao.save(p5);
	}
	
	@Test
	public void salvarProdutoTest(){
		
		Query consulta = pesquisar("Re");
		Produto produtoPesquisa = (Produto) consulta.uniqueResult();
		
		assertEquals("lote2", produtoPesquisa.getUnidade());
	}
	
	@Test
	public void listarProdutosTest(){
		Criteria lista = sessao.createCriteria(Produto.class);
		java.util.List<Produto> produto = lista.list();
		assertEquals(5, produto.size());
	}
	
	@Test
	public void excluirProdutoTest(){
		Query consulta = pesquisar("Papel");
		Produto produtoDeletar = (Produto) consulta.uniqueResult();
		sessao.delete(produtoDeletar);
		
		produtoDeletar = (Produto) consulta.uniqueResult();
		assertNull(produtoDeletar);
		
		
	}
	
	@Test
	public void alteracaoProdutoTest(){
		
		Query consulta = pesquisar("Livro");
		Produto produtoAlterar = (Produto) consulta.uniqueResult();
		produtoAlterar.setEstoque(100);
		
		sessao.update(produtoAlterar);
		
		produtoAlterar = (Produto) consulta.uniqueResult();
		
		assertEquals(100, produtoAlterar.getEstoque().intValue());
	}

	private Query pesquisar(String parametro) {
		String sql = "from Produto p where p.descricao like :descricao";
		Query consulta = sessao.createQuery(sql);
		consulta.setString("descricao", "%"+parametro+"%");
		return consulta;
	}
	
	
}
