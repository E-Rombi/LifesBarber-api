package br.com.barber.integration.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.barber.integration.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private LocalDate dataNascimento;
	private String cpf;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.cpf = cliente.getCpf();
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}

	public static Page<ClienteDto> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDto::new);
	}
	
	
	
	
}
