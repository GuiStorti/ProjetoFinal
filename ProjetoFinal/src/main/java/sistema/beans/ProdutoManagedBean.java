package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import sistema.beans.datamodel.ProdutoDataModel;
import sistema.modelos.Cliente;
import sistema.modelos.Produto;
import sistema.modelos.ProdutoPedido;
//import sistema.modelos.Pedido;
import sistema.service.ClienteService;
import sistema.service.ProdutoService;

@ManagedBean(name="produtoManagedBean")
@ViewScoped
public class ProdutoManagedBean {
	
	private Produto produto = new Produto();
	private Produto produtoSelecionada;
	private ProdutoService servico = new ProdutoService();
	private List<Produto> produtos;
	
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Produto getProdutoSelecionada() {
		return produtoSelecionada;
	}
	public void setProdutoSelecionada(Produto produtoSelecionada) {
		this.produtoSelecionada = produtoSelecionada;
	}
	
	public void salvar()
	{
		produtos.add(produto);
		servico.salvar(produto);
		
		produto = new Produto();
		
	}
	
	public DataModel<Produto> getProdutos() {
		if(produtos == null)
			produtos = servico.getProdutos();
		
		return new ProdutoDataModel(produtos);
	}
	
	public void remove(Produto produto) {
		if(servico.pesquisarProdutosPedido(produto).size() > 0)
		{
		   FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Não é possível remover produto","Produto esta em pedidos!"));
		}
		else
		{
			servico.remover(produto);
			produtos.remove(produto);
		}
	}
	
	public List <ProdutoPedido> getProdutosPedido() {
		if(produtoSelecionada != null)
		{
			return servico.pesquisarProdutosPedido(produtoSelecionada);
		}
		else
			return null;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Produto p = ((Produto) event.getObject());
		servico.alterar(p);
	}
	
}
