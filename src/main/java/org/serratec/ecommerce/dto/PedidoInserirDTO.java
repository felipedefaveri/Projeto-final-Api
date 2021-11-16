package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.enums.StatusPedido;


public class PedidoInserirDTO {
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private StatusPedido status;
	private Long idCliente;
	private List<ItemPedidoInserirDTO> itensPedidosDTO;
	
	
	public PedidoInserirDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PedidoInserirDTO(LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio, StatusPedido status, Long idCliente, List<ItemPedidoInserirDTO> itensPedidosDTO) {
		super();
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.idCliente = idCliente;
		this.itensPedidosDTO = itensPedidosDTO;
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

	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public List<ItemPedidoInserirDTO> getItensPedidosDTO() {
		return itensPedidosDTO;
	}
	public void setItensPedidosDTO(List<ItemPedidoInserirDTO> itensPedidosDTO) {
		this.itensPedidosDTO = itensPedidosDTO;
	}

}
