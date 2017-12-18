package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import sistema.beans.datamodel.PedidoDataModel;
import sistema.modelos.Produto;
import sistema.modelos.Cliente;
import sistema.modelos.Vendedor;
import sistema.modelos.ProdutoPedido;
import sistema.modelos.Pedido;
import sistema.service.PedidoService;

@ManagedBean
@ViewScoped
public class PedidoManagedBean {
	
	private Pedido pedido = new Pedido();
	private Pedido pedidoSelecionado;
	private PedidoService servico = new PedidoService();
	private List<Pedido> pedidos;
	private Cliente cliente;
	private List<Cliente> clientes;
	private Vendedor vendedor;
	private List<Vendedor> vendedores;
	private Produto produto;
	private List<Produto> produtos;
	
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Vendedor> getVendedores() {
		return vendedores;
	}
	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}
	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}
	
	public PedidoService getServico() {
		return servico;
	}
	public void setServico(PedidoService servico) {
		this.servico = servico;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public void salvar()
	{
		servico.salvar(pedido);
		
		if(pedidos != null)
			pedidos.add(pedido);
		
		
		pedido = new Pedido();
	}
	
	public DataModel<Pedido> getPedidos() {
		if(pedidos == null)
			pedidos = servico.getPedidos();
		
		return new PedidoDataModel(pedidos);
	}
	
	public void remove(Pedido pedido) {
		if(servico.pesquisarProdutosPedido(pedido).size() > 0)
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Não é possível remover pedido","Pedido tem produtos!"));
		}
		else
		{
			servico.remover(pedido);
			pedidos.remove(pedido);
		}
	}
	
	public List <ProdutoPedido> getProdutosPedido() {
		if(pedidoSelecionado != null)
		{
			return servico.pesquisarProdutosPedido(pedidoSelecionado);
		}
		else
			return null;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Pedido p = ((Pedido) event.getObject());
		servico.alterar(p);
	}
	
}
