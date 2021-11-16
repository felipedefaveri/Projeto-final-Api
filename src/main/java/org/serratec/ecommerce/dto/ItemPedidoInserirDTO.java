package org.serratec.ecommerce.dto;

public class ItemPedidoInserirDTO {
	
	private Integer quantidade;
	private Long id_produto;
	
	public ItemPedidoInserirDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemPedidoInserirDTO(Integer quantidade, Long id_produto) {
		super();
		this.quantidade = quantidade;
		this.id_produto = id_produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Long getId_produto() {
		return id_produto;
	}
	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}
	
}