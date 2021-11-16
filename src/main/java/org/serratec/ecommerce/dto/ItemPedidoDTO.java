package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.domain.ItemPedido;
import org.serratec.ecommerce.domain.Produto;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ItemPedidoDTO {
	
	private Integer quantidade;
	private Produto produto;
	@JsonIgnore
	private Long id_pedido;
	private Double precoVenda;
	private Double total;
	
	public ItemPedidoDTO() {
	
	}
	
	public ItemPedidoDTO(ItemPedido itemPedido) {
		this.quantidade = itemPedido.getQuantidade();
		this.produto = itemPedido.getProduto();
		this.id_pedido = itemPedido.getPedido().getId();
		this.total = itemPedido.getTotal();
		this.precoVenda = itemPedido.getPrecoVenda();
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Double getTotal() {
	     return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

}