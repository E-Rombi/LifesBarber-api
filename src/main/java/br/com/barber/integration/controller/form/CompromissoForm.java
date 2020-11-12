package br.com.barber.integration.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.service.ClienteService;
import br.com.barber.integration.service.ProdutoService;


public class CompromissoForm {

	@NotNull
	private LocalDate data;
	
	@NotNull
	private LocalTime hora;
	
	@NotNull
	private Long cliente;
	
	@NotNull
	private Long servico;	

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getServico() {
		return servico;
	}

	public void setServico(Long servico) {
		this.servico = servico;
	}
	
	public Compromisso converter(ClienteService clienteService, ProdutoService produtoService) {
		Compromisso compromisso = new Compromisso();
		compromisso.setData(data);
		compromisso.setHora(hora);
		compromisso.setCliente(clienteService.findById(cliente));
		compromisso.setServico(produtoService.findById(servico));
		return compromisso;
	}
	
}
