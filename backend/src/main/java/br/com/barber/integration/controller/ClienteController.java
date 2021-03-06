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

import br.com.barber.integration.controller.dto.ClienteDto;
import br.com.barber.integration.controller.dto.validacao.MessageDto;
import br.com.barber.integration.controller.form.ClienteForm;
import br.com.barber.integration.controller.form.ClienteFormAtualizacao;
import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags = "Cliente", description = "Operações de Clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Lista todos os clientes de acordo com parametros passados")
	public Page<ClienteDto> listar(@PageableDefault(direction = Direction.ASC, sort = "id", size = 10) Pageable page) {
		Page<ClienteDto> clientes = ClienteDto.converter(clienteService.findAll(page));
		return clientes;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Mais informações do Cliente")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		ClienteDto cliente = new ClienteDto(clienteService.findById(id));
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Cadastrar novo Cliente")
	public ResponseEntity<ClienteDto> inserir(@Valid @RequestBody ClienteForm form, UriComponentsBuilder builder) {
		Cliente cliente = form.converter();
		clienteService.save(cliente);
		
		URI uri = builder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@PutMapping("/{id}")
	@Transactional	
	@ApiOperation(value = "Atualizar o Cliente")
	public ResponseEntity<ClienteDto> atualizar(@RequestBody @Valid ClienteFormAtualizacao form, @PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		form.atualizar(cliente);
		return ResponseEntity.ok(new ClienteDto(cliente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Deletar o Cliente")
	public ResponseEntity<MessageDto> deletar(@PathVariable Long id) {
		clienteService.deleteById(id);
		return ResponseEntity.ok(new MessageDto("Registro com id (" + id + ") deletado com sucesso !"));
	}
	
}
