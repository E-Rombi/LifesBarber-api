package br.com.barber.integration.model.builder;

import java.time.LocalDate;

import br.com.barber.integration.model.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	public UsuarioBuilder() {
		usuario = new Usuario();
	}
	
	public UsuarioBuilder setNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
	
	public UsuarioBuilder setSobrenome(String sobrenome) {
		usuario.setSobrenome(sobrenome);
		return this;
	}
	
	public UsuarioBuilder setTelefone(String telefone) {
		usuario.setTelefone(telefone);
		return this;
	}
	
	public UsuarioBuilder setDataNascimento(LocalDate dataNascimento) {
		usuario.setDataNascimento(dataNascimento);
		return this;
	}
	
	public UsuarioBuilder setCpf(String cpf) {
		usuario.setCpf(cpf);
		return this;
	}
	
	public UsuarioBuilder setEmail(String email) {
		usuario.setEmail(email);
		return this;
	}
	
	public UsuarioBuilder setSenha(String senha) {
		usuario.setSenha(senha);
		return this;
	}
	
	public Usuario build() {
		return usuario;
	}
}
