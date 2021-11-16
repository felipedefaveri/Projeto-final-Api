package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.domain.ItemPedido;
import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.enums.StatusPedido;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class PedidoDTO {
	private Long id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataPedido;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataEntrega;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataEnvio;
	
	private StatusPedido status;
	private Double valorTotal;
	private ClienteDTO clienteDTO;
	private List<ItemPedido> itensPedidos;
	@JsonIgnore
	private List<ItemPedidoDTO> itensPedidosDTO;
	
	
	public PedidoDTO() {
	
	}

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.dataPedido = pedido.getDataPedido();
		this.status = pedido.getStatus();
		this.clienteDTO = new ClienteDTO(pedido.getCliente());
		this.itensPedidos = pedido.getItensPedidos();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		pedido.setValorTotal(valorTotal);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<ItemPedido> getItensPedidos() {
		return itensPedidos;
	}

	public List<ItemPedidoDTO> getItensPedidosDTO() {
		return itensPedidosDTO;
	}

	public void setItensPedidosDTO(List<ItemPedidoDTO> itensPedidosDTO) {
		this.itensPedidosDTO = itensPedidosDTO;
	}

	public void setItensPedidos(List<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
}