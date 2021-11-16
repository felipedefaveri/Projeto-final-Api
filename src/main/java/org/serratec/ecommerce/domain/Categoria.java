package org.serratec.ecommerce.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	@ApiModelProperty(value = "Identificador único da categoria")
	private Long id;

	@NotBlank(message = "Esta campo não pode ser vazio")
	@Size(max = 30, message = "Limite de carateres excedido")
	@ApiModelProperty(value = "Define o nome da categoria ")
	private String nome;

	@Size(max = 150, message = "Limite de carateres excedido")
	@ApiModelProperty(value = "Define a descrição da categoria ")
	private String descricao;

	@JsonManagedReference
	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	@ApiModelProperty(value = "Uma categoria pode ter vários produtos")
	private List<Produto> produtos;

	public Categoria() {

	}

	public Categoria(Long id, String nome, String descricao, List<Produto> produtos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}