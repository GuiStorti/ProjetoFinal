package sistema.beans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import sistema.modelos.Produto;
import sistema.service.ProdutoService;

public class ProdutoDataModel extends ListDataModel<Produto> implements SelectableDataModel<Produto>{
	
	private ProdutoService servico = new ProdutoService();
	
	public ProdutoDataModel() {
		
	}
	
	public ProdutoDataModel(List<Produto> list) {
		super(list);
	}
	
	@Override
	public Produto getRowData(String rowKey) {
		for(Produto p: servico.getProdutos())
			if(Integer.parseInt(rowKey) == p.getCodigo())
				return p;
		
		return null;
	}
	
	@Override
	public Object getRowKey(Produto produto) {
		return produto.getCodigo();
	}
	
}
