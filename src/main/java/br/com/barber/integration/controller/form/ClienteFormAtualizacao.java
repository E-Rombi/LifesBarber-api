package br.com.barber.integration.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.barber.integration.model.Cliente;

public class ClienteFormAtualizacao {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@NotBlank
	private String cpf;
	
	private String telefone;
	private LocalDate dataNascimento;
	
	public ClienteFormAtualizacao() {	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Cliente converter() {
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setDataNascimento(dataNascimento);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setTelefone(telefone);
		return cliente;
		
	}
	
	public void atualizar(Cliente cliente) {
		cliente.setCpf(cpf);
		cliente.setDataNascimento(dataNascimento);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setTelefone(telefone);
	}
	
}
