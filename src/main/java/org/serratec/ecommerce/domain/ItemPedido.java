package org.serratec.ecommerce.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Identificador único do Item Pedido")
	@Column(name = "id_item_pedido")
	private Long id;
	
	@NotNull(message = "O campo não pode ser inválido")
	@ApiModelProperty(value = "Define a quantidade dos itens no pedido ")
	private Integer quantidade;
	
	@NotNull(message = "O campo não pode ser inválido")
	@Column(name = "preco_venda")
	@ApiModelProperty(value = "Define o preço de venda dos itens no pedido ")
	private Double precoVenda;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	@ApiModelProperty(value = "Define a chave estrangeira de produto na tabela de item pedido ")
	private Produto produto;
	
	@Transient
	private Double total;

	public ItemPedido() {
	}

	public ItemPedido(Long id, Integer quantidade, double precoVenda, Pedido pedido, Produto produto) {
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedido [quantidade=" + quantidade + ", precoVenda=" + precoVenda + ", produto=" + produto
				+ ", total=" + total + "]";
	}
	
}