package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.domain.Endereco;
import org.serratec.ecommerce.domain.Pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ClienteDTO {
	
	private Long id;
	private String email;
	private String nomeCompleto;
	private String telefone;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private String cpf;
	
	@JsonIgnore
	private List<Pedido> pedidos;
	
	public ClienteDTO() {
	
	}
	
	public ClienteDTO (Cliente cliente) {
		this.email = cliente.getEmail();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.endereco = cliente.getEndereco();
		this.cpf = cliente.getCpf();
		this.id = cliente.getId();
		this.pedidos = cliente.getPedidos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}