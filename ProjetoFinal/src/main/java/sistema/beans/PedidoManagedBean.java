package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import sistema.beans.datamodel.PedidoDataModel;
//import sistema.modelos.Produto;
import sistema.modelos.ProdutoPedido;
import sistema.modelos.Pedido;
import sistema.service.PedidoService;

@ManagedBean
@ViewScoped
public class PedidoManagedBean {
	
	private Pedido pedido = new Pedido();
	private Pedido pedidoSelecionada;
	private PedidoService servico = new PedidoService();
	private List<Pedido> pedidos;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Pedido getPedidoSelecionada() {
		return pedidoSelecionada;
	}
	public void setPedidoSelecionada(Pedido pedidoSelecionada) {
		this.pedidoSelecionada = pedidoSelecionada;
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
		if(pedidoSelecionada != null)
		{
			return servico.pesquisarProdutosPedido(pedidoSelecionada);
		}
		else
			return null;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Pedido p = ((Pedido) event.getObject());
		servico.alterar(p);
	}
	
}
