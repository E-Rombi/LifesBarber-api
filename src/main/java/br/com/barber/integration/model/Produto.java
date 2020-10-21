package br.com.barber.integration.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.barber.integration.enums.ProdutoStatus;
import br.com.barber.integration.enums.ProdutoTipo;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private BigDecimal valor;
	private ProdutoStatus status;
	private ProdutoTipo tipo;
	
	public Produto() {	}

	public Produto(Long id, String nome, BigDecimal valor, ProdutoStatus status, ProdutoTipo tipo) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.status = status;
		this.tipo = tipo;
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
}
