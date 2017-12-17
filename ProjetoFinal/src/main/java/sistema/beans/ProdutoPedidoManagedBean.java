package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Produto;
import sistema.modelos.ProdutoPedido;
import sistema.modelos.Pedido;
import sistema.service.ProdutoPedidoService;
import sistema.service.ProdutoService;
import sistema.service.PedidoService;

@ManagedBean(name="produtoPedidoManagedBean")
@ViewScoped
public class ProdutoPedidoManagedBean {
	
	private ProdutoPedido produtoPedido = new ProdutoPedido();
	private Produto produto;
	private Pedido pedido;
	private ProdutoPedidoService servico = new ProdutoPedidoService();
	private ProdutoService pergServico = new ProdutoService();
	private PedidoService provServico = new PedidoService();
	private List<ProdutoPedido> produtoPedidos;
	
	public void salvar() {
		produto.addProdutoPedido(produtoPedido);
		produtoPedido.setProduto(produto);
		
		pedido.addProdutoPedido(produtoPedido);
		produtoPedido.setPedido(pedido);
		
		produtoPedidos.add(produtoPedido);
		servico.salvar(produtoPedido);
		
		produtoPedido = new ProdutoPedido();
		produto = null;
		pedido = null;
		
	}
	
	public List <Produto> getProdutos() {
		return pergServico.getProdutos();
	}
	
	public List <Pedido> getPedidos() {
		return provServico.getPedidos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ProdutoPedido getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(ProdutoPedido produtoPedido) {
		this.produtoPedido = produtoPedido;
	}

	public List<ProdutoPedido> getProdutoPedidos() {
		if(produtoPedidos == null)
			produtoPedidos = servico.getProdutosPedido();
		
		return produtoPedidos;
	}
	
	public void remove(ProdutoPedido produtoPedido) {
		produtoPedidos.remove(produtoPedido);
		servico.remover(produtoPedido);
	}
	
	public void onRowEdit(RowEditEvent event) {
		ProdutoPedido pp = ((ProdutoPedido) event.getObject());
		servico.alterar(pp);
	}
	
}
