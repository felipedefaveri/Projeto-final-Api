package org.serratec.ecommerce.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.domain.Endereco;


public class ClienteInserirDTO {
	private Long id;
	private String email;
	private String nomeCompleto;
	private String telefone;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private String cpf;
	private Integer numero;
	private String complemento;
	private String nomeUsuario;
	private String senha;

	public ClienteInserirDTO() {
	
	}
	
	public ClienteInserirDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.email = cliente.getEmail();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.endereco = cliente.getEndereco();
		this.cpf = cliente.getCpf();
		this.numero = cliente.getNumero();
		this.complemento = cliente.getComplemento();
		this.nomeUsuario = cliente.getNomeUsuario();
		this.senha = cliente.getSenha();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}