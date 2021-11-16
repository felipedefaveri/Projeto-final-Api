package org.serratec.ecommerce.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	@ApiModelProperty(value = "Identificador único do Produto")
	private Long id;

	@NotBlank(message = "campo não pode ser vazio")
	@Size(max = 30, message = "tamanho máximo excedido")
	@ApiModelProperty(value = "Define o nome do produto ")
	private String nome;

	@Size(max = 100, message = "tamanho máximo excedido")
	@ApiModelProperty(value = "Define a descrição do produto ")
	private String descricao;

	@NotNull(message = "campo não pode ser vazio")
	@Column(name = "qtd_estoque")
	@ApiModelProperty(value = "Define a quantidade de estoque do produto ")
	private Integer qtdEstoque;

	@Past(message = "Data inválida")
	@Column(name = "data_cadastro")
	@ApiModelProperty(value = "Define a data de cadastro do produto ")
	private LocalDate dataCadastro;

	@PositiveOrZero(message = "O valor do produto não pode ser negativo")
	@NotNull(message = "campo não pode ser vazio")
	@Column(name = "valor_unitario")
	@ApiModelProperty(value = "Define o valor unitário do produto ")
	private Double valorUnitario;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	@ApiModelProperty(value = "Define a chave estrangeira da categoria na tabela produto ")
	private Categoria categoria;

	public Produto() {

	}

	public Produto(Long id, String nome, String descricao, Integer qtdEstoque, LocalDate dataCadastro,
			Double valorUnitario, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.categoria = categoria;
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

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", descricao=" + descricao + ", valorUnitario=" + valorUnitario + "]";
	}
	
	

}