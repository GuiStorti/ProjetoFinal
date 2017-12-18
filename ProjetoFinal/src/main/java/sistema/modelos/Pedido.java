package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Vendedor vendedor;

	@OneToMany(mappedBy="pedido")
	private List<ProdutoPedido> produtos_pedido = new ArrayList<ProdutoPedido>();

	

	public Pedido(long codigo, Cliente cliente, Vendedor vendedor) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.vendedor = vendedor;
	}

	public Pedido() {
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public List<ProdutoPedido> getProdutos_pedido() {
		return produtos_pedido;
	}

	public void setProdutos_pedido(List<ProdutoPedido> produtos_pedido) {
		this.produtos_pedido = produtos_pedido;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void addProdutoPedido(ProdutoPedido produtoPedido) {
		produtos_pedido.add(produtoPedido);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + "]";
	}

}
