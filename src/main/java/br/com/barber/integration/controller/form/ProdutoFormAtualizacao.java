package br.com.barber.integration.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.builder.ProdutoBuilder;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;

public class ProdutoFormAtualizacao {

	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private ProdutoStatus status;
	
	@NotNull
	private ProdutoTipo tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ProdutoStatus getStatus() {
		return status;
	}

	public void setStatus(ProdutoStatus status) {
		this.status = status;
	}

	public ProdutoTipo getTipo() {
		return tipo;
	}

	public void setTipo(ProdutoTipo tipo) {
		this.tipo = tipo;
	}

	public Produto converter() {
		return new ProdutoBuilder()
						.setNome(this.nome)
						.setStatus(this.status)
						.setTipo(this.tipo)
						.setValor(this.valor)
						.build();
	}
	
	public void atualizar(Produto produto) {
		produto.setNome(nome);
		produto.setStatus(status);
		produto.setTipo(tipo);
		produto.setValor(valor);
	}
}
