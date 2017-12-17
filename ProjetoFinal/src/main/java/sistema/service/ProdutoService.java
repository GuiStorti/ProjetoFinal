package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.modelos.Produto;
import sistema.modelos.ProdutoPedido;
//import sistema.modelos.Pedido;

public class ProdutoService extends Service {
	
	public void salvar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(produto);
		em.getTransaction().commit();	
	    em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> getProdutos() {
		List<Produto> produtos;
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select p From Produto p");
		produtos = q.getResultList();
		em.close();
		
		return produtos;
	}
	
	public void alterar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.merge(produto);
		em.getTransaction().commit();	
	    em.close();
	}
	
	public void remover(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			produto = em.find(Produto.class, produto.getCodigo());
			em.remove(produto);
		em.getTransaction().commit();	
		em.close();
	}
	
	public Produto pesquisar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		produto = em.find(Produto.class, produto.getCodigo());
		em.close();
	    
	    return produto;
	}
	
	public List<ProdutoPedido> pesquisarProdutosPedido(Produto produto){
		List<ProdutoPedido> pedidos;
		
		EntityManager em = emf.createEntityManager();
		
			//Torna gerenciavel a entidade
		    produto = em.merge(produto);
		    
		    //Atualiza a entidade para não usar dados antigos no cache do JPA
		    //Ver https://wiki.eclipse.org/EclipseLink/Examples/JPA/Caching
			em.refresh(produto);
			
			//Recupera a lista de produtos
			pedidos = produto.getProdutos_pedido();
		
		em.close();
    
		return pedidos;
	}
	
}
