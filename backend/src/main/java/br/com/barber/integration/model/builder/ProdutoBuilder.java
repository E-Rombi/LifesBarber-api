package br.com.barber.integration.model.builder;

import java.math.BigDecimal;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;

public class ProdutoBuilder {

	private Produto produto;
	
	public ProdutoBuilder() {
		produto = new Produto();
	}
	
	public ProdutoBuilder setId(Long id) {
		produto.setId(id);
		return this;
	}
	
	public ProdutoBuilder setNome(String nome) {
		produto.setNome(nome);
		return this;
	}
	
	public ProdutoBuilder setValor(BigDecimal valor) {
		produto.setValor(valor);
		return this;
	}
	
	public ProdutoBuilder setStatus(ProdutoStatus status) {
		produto.setStatus(status);
		return this;
	}
	
	public ProdutoBuilder setTipo(ProdutoTipo tipo) {
		produto.setTipo(tipo);
		return this;
	}
	
	public Produto build() {
		return produto;
	}
}
