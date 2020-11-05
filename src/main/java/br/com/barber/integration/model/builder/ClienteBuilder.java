package br.com.barber.integration.model.builder;

import java.time.LocalDate;

import br.com.barber.integration.model.Cliente;

public class ClienteBuilder {

	private Cliente cliente;
	
	public ClienteBuilder() {
		cliente = new Cliente();
	}
	
	public ClienteBuilder setNome(String nome) {
		cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder setSobrenome(String sobrenome) {
		cliente.setSobrenome(sobrenome);
		return this;
	}
	
	public ClienteBuilder setTelefone(String telefone) {
		cliente.setTelefone(telefone);
		return this;
	}
	
	public ClienteBuilder setDataNascimento(LocalDate dataNascimento) {
		cliente.setDataNascimento(dataNascimento);
		return this;
	}
	
	public ClienteBuilder setCpf(String cpf) {
		cliente.setCpf(cpf);
		return this;
	}
	
	public Cliente build() {
		return cliente;
	}
}
