package org.serratec.ecommerce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	@ApiModelProperty(value = "Identificador único da foto")
	private Long id;
	
	@Lob
	@ApiModelProperty(value = "Define os dados de uma foto ")
	private byte[] dados;
	
	@ApiModelProperty(value = "Define o nome de uma foto ")
	private String nome;
	
	@ApiModelProperty(value = "Define o tipo de uma foto ")
	private String tipo;
	
	@OneToOne
	@JoinColumn(name = "id_produto")
	@ApiModelProperty(value = "Uma foto pode existir em um só produto")
	private Produto produto;

	public Foto() {

	}
	
	public Foto(Long id, byte[] dados, String nome, String tipo, Produto produto) {
		super();
		this.id = id;
		this.dados = dados;
		this.nome = nome;
		this.tipo = tipo;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		Foto other = (Foto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}