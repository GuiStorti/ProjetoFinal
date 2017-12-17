package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.modelos.Produto;
import sistema.modelos.ProdutoPedido;
import sistema.modelos.Pedido;

public class PedidoService extends Service{
	
	public void salvar(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(pedido);
		em.getTransaction().commit();	
	    em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getPedidos() {
		List<Pedido> pedidos;
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select p From Pedido p");
		pedidos = q.getResultList();
		em.close();
		
		return pedidos;
	}
	
	public void alterar(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.merge(pedido);
		em.getTransaction().commit();	
	    em.close();
	}
	
	public void remover(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			pedido = em.find(Pedido.class, pedido.getCodigo());
			em.remove(pedido);
		em.getTransaction().commit();	
		em.close();
	}
	
	public Pedido pesquisar(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		pedido = em.find(Pedido.class, pedido.getCodigo());
		em.close();
	    
	    return pedido;
	}
	
	public List<ProdutoPedido> pesquisarProdutosPedido(Pedido pedido){
		List<ProdutoPedido> produtos;
		
		EntityManager em = emf.createEntityManager();
		
		    pedido = em.merge(pedido);
		   	em.refresh(pedido);
			produtos = pedido.getProdutos_pedido();
		
		em.close();
    
		return produtos;
	}
	
}
