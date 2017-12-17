package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.modelos.ProdutoPedido;

public class ProdutoPedidoService extends Service{
	
	public void salvar(ProdutoPedido produtoPedido)
	{
	    
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(produtoPedido);
		em.getTransaction().commit();	
	    em.close();
		
	}

	
	@SuppressWarnings("unchecked")
	public List <ProdutoPedido> getProdutosPedido() {
		
		List <ProdutoPedido> produtoPedidos;
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select pp From ProdutoPedido pp");
		produtoPedidos = q.getResultList();
		em.close();
		
		return produtoPedidos;
	}
	
	
	public void alterar(ProdutoPedido produtoPedido) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.merge(produtoPedido);
		em.getTransaction().commit();	
	    em.close();
	}
	
	
	public void remover(ProdutoPedido produtoPedido) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			produtoPedido = em.find(ProdutoPedido.class,produtoPedido.getId());
			em.remove(produtoPedido);
		em.getTransaction().commit();	
	    em.close();
	}
	
}
