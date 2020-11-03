package br.com.barber.integration.controller.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.barber.integration.model.Perfil;
import br.com.barber.integration.model.Usuario;

public class UsuarioForm {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@Size(max = 13)
	private String telefone;
	
	private LocalDate dataNascimento;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String senha;
	
	private List<Perfil> perfis;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public Usuario converter() {
		Usuario usuario = new Usuario();
		usuario.setCpf(cpf);
		usuario.setDataNascimento(dataNascimento);
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setSobrenome(sobrenome);
		usuario.setTelefone(telefone);
		return usuario;
	}

	public void atualizar(Usuario usuario) {
		usuario.setCpf(cpf);
		usuario.setDataNascimento(dataNascimento);
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setSobrenome(sobrenome);
		usuario.setTelefone(telefone);
	}
	
}
