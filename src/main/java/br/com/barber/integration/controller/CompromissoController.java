package br.com.barber.integration.controller;

import java.net.URI;
import java.time.LocalDateTime;

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

import br.com.barber.integration.controller.dto.CompromissoDto;
import br.com.barber.integration.controller.dto.validacao.ErrorDto;
import br.com.barber.integration.controller.dto.validacao.MessageDto;
import br.com.barber.integration.controller.form.CompromissoForm;
import br.com.barber.integration.controller.form.CompromissoFormAtualizacao;
import br.com.barber.integration.exception.TipoInvalidoException;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.service.ClienteService;
import br.com.barber.integration.service.CompromissoService;
import br.com.barber.integration.service.ProdutoService;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {

	@Autowired
	private CompromissoService compromissoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping
	public Page<CompromissoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<CompromissoDto> compromissos = CompromissoDto.converter(compromissoService.findAll(page));
		
		return compromissos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompromissoDto> detalhar(@PathVariable Long id) {
		CompromissoDto compromissoDto = new CompromissoDto(compromissoService.findById(id));
		
		return ResponseEntity.ok(compromissoDto);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> inserir(@RequestBody @Valid CompromissoForm form, UriComponentsBuilder builder) {
		try {
			Compromisso compromisso = form.converter(clienteService, produtoService);
			compromissoService.save(compromisso);
			
			URI uri = builder.path("/produto/{id}").buildAndExpand(compromisso.getId()).toUri();
			return ResponseEntity.created(uri).body(new CompromissoDto(compromisso));
			
		} catch (TipoInvalidoException e) {
			return ResponseEntity.badRequest().body(new ErrorDto(400, e.getMessage(), LocalDateTime.now()));
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@RequestBody @Valid CompromissoFormAtualizacao form, @PathVariable Long id) {
		try {
			Compromisso compromisso = compromissoService.findById(id);
			form.atualizar(compromisso, clienteService, produtoService);
			
			return ResponseEntity.ok(new CompromissoDto(compromisso));
			
		} catch (TipoInvalidoException e) {
			return ResponseEntity.badRequest().body(new ErrorDto(400, e.getMessage(), LocalDateTime.now()));
		}
		
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<MessageDto> deletar(@PathVariable Long id) {
		compromissoService.deleteById(id);
		return ResponseEntity.ok(new MessageDto("Registro com id (" + id + ") deletado com sucesso !"));
	}
	
}
