package br.com.barber.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> findAll(Pageable page) {
		return usuarioRepository.findAll(page);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).get();
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
