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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.barber.integration.controller.dto.ClienteDto;
import br.com.barber.integration.controller.form.ClienteForm;
import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public Page<ClienteDto> listar(@PageableDefault(direction = Direction.ASC, sort = "id", size = 10) Pageable page) {
		Page<ClienteDto> clientes = ClienteDto.converter(clienteService.findAll(page));
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		ClienteDto cliente = new ClienteDto(clienteService.findById(id));
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> inserir(@Valid @RequestBody ClienteForm form, UriComponentsBuilder builder) {
		Cliente cliente = form.converter();
		clienteService.save(cliente);
		
		URI uri = builder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteForm> atualizar(@RequestBody @Valid ClienteForm form, @PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		form.atualizar(cliente);
		clienteService.save(cliente);
		return ResponseEntity.ok(form);
	}
	
}
