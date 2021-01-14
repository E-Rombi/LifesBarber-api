package br.com.barber.integration.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.model.Produto;

public class CompromissoDto {

	private Long id;
	private LocalDate data;
	private LocalTime hora;
	private Cliente cliente;
	private Produto servico;
	
	public CompromissoDto(Compromisso compromisso) {
		this.id = compromisso.getId();
		this.data = compromisso.getData();
		this.hora = compromisso.getHora();
		this.cliente = compromisso.getCliente();
		this.servico = compromisso.getServico();
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public LocalTime getHora() {
		return hora;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public Produto getServico() {
		return servico;
	}
	
	public static Page<CompromissoDto> converter(Page<Compromisso> compromissos) {
		return compromissos.map(CompromissoDto::new);
	}
	
}
