package org.serratec.ecommerce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	@ApiModelProperty(value = "Identificador único do endereço")
	private Long id;
	
	@NotBlank(message = "Campo não pode ser vazio")
	@Size(max = 9, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o cep do endereço")
	private String cep;
	
	@NotBlank(message = "Campo não pode ser vazio")
	@Size(max = 100, message = "Número de caracteres excedido")
	@Column(name = "rua")
	@ApiModelProperty(value = "Define a rua do endereço")
	private String logradouro;
	
	@NotBlank(message = "Campo não pode ser vazio")
	@Size(max = 50, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o bairro do endereço")
	private String bairro;
	
	@Size(max = 30, message = "Número de caracteres excedido")
	@Column(name = "cidade")
	@ApiModelProperty(value = "Define a cidade do endereço")
	private String localidade;
	
	@Size(max = 2, message = "Número de caracteres excedido")
	@Column(name = "estado")
	@ApiModelProperty(value = "Define o estado do endereço")
	private String uf;
	
	public Endereco() {
		
	}

	public Endereco(Long id, String cep, String logradouro, String bairro, String localidade, String uf) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}