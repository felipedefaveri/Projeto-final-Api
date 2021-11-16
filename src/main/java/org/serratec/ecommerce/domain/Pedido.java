package org.serratec.ecommerce.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.serratec.ecommerce.enums.StatusPedido;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	@ApiModelProperty(value = "Identificador único do Pedido")
	private Long id;
	
	@NotNull(message = "Este campo não pode ser vazio")
	@Column(name = "data_pedido", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@ApiModelProperty(value = "Define a data do pedido ")
	private LocalDate dataPedido;
	
	//@Past(message = "Entre com uma data válida")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "data_entrega")
	@ApiModelProperty(value = "Define a data de entrega do pedido ")
	private LocalDate dataEntrega;
	
	//@Past(message = "Entre com uma data válida")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "data_envio")
	@ApiModelProperty(value = "Define a data de envio do pedido ")
	private LocalDate dataEnvio;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Define o status do pedido ")
	private StatusPedido status;
	
	@Transient
	@ApiModelProperty(value = "Define o valor total do pedido ")
	private Double valorTotal;
	

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@ApiModelProperty(value = "Define a chave estrangeira do cliente na tabela pedido ")
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	@ApiModelProperty(value = "Um pedido pode ter vários itens pedido ")
	private List<ItemPedido> itensPedidos;

	public Pedido() {

	}

	public Pedido(Long id, LocalDate dataPedido, StatusPedido status, Cliente cliente, List<ItemPedido> itensPedidos, LocalDate dataEntrega, LocalDate dataEnvio) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.status = status;
		this.cliente = cliente;
		this.itensPedidos = itensPedidos;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.valorTotal = getValorTotal();
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedidos() {
		return itensPedidos;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "|Pedido efetuado|" + "Data de entrega: " + dataEntrega + " Data de envio: " + dataEnvio + " Valor total do pedido: " + valorTotal
				+  "Itens do pedido: " + itensPedidos ;
	}

	
}