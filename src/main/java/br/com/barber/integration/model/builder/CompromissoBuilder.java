package br.com.barber.integration.model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.model.Produto;

public class CompromissoBuilder {

	private Compromisso compromisso;
	
	public CompromissoBuilder() {
		compromisso = new Compromisso();
	}
	
	public CompromissoBuilder setId(Long id) {
		compromisso.setId(id);
		return this;
	}
	
	public CompromissoBuilder setData(LocalDate data) {
		compromisso.setData(data);
		return this;
	}
	
	public CompromissoBuilder setHora(LocalTime hora) {
		compromisso.setHora(hora);
		return this;
	}
	
	public CompromissoBuilder setCliente(Cliente cliente) {
		compromisso.setCliente(cliente);
		return this;
	}
	
	public CompromissoBuilder setServico(Produto servico) {
		compromisso.setServico(servico);
		return this;
	}

	public Compromisso build() {
		return compromisso;
	}
}
