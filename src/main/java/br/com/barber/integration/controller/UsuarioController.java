package br.com.barber.integration.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.barber.integration.controller.dto.UsuarioDto;
import br.com.barber.integration.controller.dto.validacao.MessageDto;
import br.com.barber.integration.controller.form.UsuarioForm;
import br.com.barber.integration.controller.form.UsuarioFormAtualizacao;
import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<Usuario> usuarios = usuarioService.findAll(page);
		return UsuarioDto.converter(usuarios);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		return ResponseEntity.ok(new UsuarioDto(usuario));
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> inserir(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder builder) {
		Usuario usuario = form.converter();
		usuarioService.save(usuario);
		
		URI uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@RequestBody @Valid UsuarioFormAtualizacao form, @PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		form.atualizar(usuario);
		
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<MessageDto> deletar(@PathVariable Long id) {
		usuarioService.deleteById(id);
		
		return ResponseEntity.ok(new MessageDto("Registro com id (" + id + ") deletado com sucesso !"));
	}
	
}
 