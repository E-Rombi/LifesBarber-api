package br.com.barber.integration.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;

public class ProdutoDto {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private ProdutoStatus status;
	private ProdutoTipo tipo;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.status = produto.getStatus();
		this.tipo = produto.getTipo();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public ProdutoStatus getStatus() {
		return status;
	}

	public ProdutoTipo getTipo() {
		return tipo;
	}

	public static Page<ProdutoDto> converter(Page<Produto> list) {
		return list.map(ProdutoDto::new);
	}
	
}
