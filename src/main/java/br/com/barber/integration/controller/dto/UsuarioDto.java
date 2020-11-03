package br.com.barber.integration.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.barber.integration.model.Perfil;
import br.com.barber.integration.model.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String cpf;
	private List<Perfil> perfis;
	
	
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.perfis = ((List<Perfil>) usuario.getAuthorities());
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public Long getId() {
		return id;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDto::new);
	}

	
	
}
