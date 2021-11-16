package org.serratec.ecommerce.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@ApiModelProperty(value = "Identificador único do cliente")
	private Long id;

	@NotBlank(message = "Campo não pode ser vazio")
	@Email(message = "Insira um e-mail válido")
	@Size(max = 30, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o email de um cliente ")
	private String email;

	@NotBlank(message = "Campo não pode ser vazio")
	@Size(max = 20, message = "Número de caracteres excedido")
	@Column(name = "nome_usuario")
	@ApiModelProperty(value = "Define o nome de usuário de um cliente ")
	private String nomeUsuario;

	@NotBlank(message = "Campo não pode ser vazio")
	@Size(max = 60, message = "Número de caracteres excedido")
	@Column(name = "nome_completo")
	@ApiModelProperty(value = "Define o nome completo de um cliente ")
	private String nomeCompleto;

	@Size(max = 255, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o email de um cliente ")
	private String senha;

	@NotBlank(message = "Campo não pode ser vazio")
	@CPF(message = "CPF inválido")
	@Column(length = 14)
	@ApiModelProperty(value = "Define o cpf de um cliente ")
	private String cpf;

	@Size(max = 11, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o telefone de um cliente ")
	private String telefone;

	@Past(message = "Data inválida!")
	@Column(name = "data_nascimento")
	@ApiModelProperty(value = "Define a data de nascimento de um cliente ")
	private LocalDate dataNascimento;

	@NotNull(message = "Campo não pode ser vazio")
	@ApiModelProperty(value = "Define o numero do endereço de um cliente ")
	private Integer numero;

	@Size(max = 20, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o complemento do endereço de um cliente ")
	private String complemento;

	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	@ApiModelProperty(value = "Um cliente pode ter vários pedidos")
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public Cliente() {

	}

	public Cliente(Long id, String email, String nomeUsuario, String nomeCompleto, String senha, String cpf,
			String telefone, LocalDate dataNascimento, Integer numero, String complemento, Endereco endereco) {
		this.id = id;
		this.email = email;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.numero = numero;
		this.complemento = complemento;
		this.endereco = endereco;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}