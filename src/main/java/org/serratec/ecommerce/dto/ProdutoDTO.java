package org.serratec.ecommerce.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.domain.Produto;


public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer qtdEstoque;
    private LocalDate dataCadastro;
    private Double valorUnitario;
    private String url;

    public ProdutoDTO() {
        
    }

    public ProdutoDTO(Produto produto) {
        this.id=produto.getId();
        this.nome=produto.getNome();
        this.descricao=produto.getDescricao();
        this.qtdEstoque=produto.getQtdEstoque();
        this.dataCadastro=produto.getDataCadastro();
        this.valorUnitario=produto.getValorUnitario();
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}