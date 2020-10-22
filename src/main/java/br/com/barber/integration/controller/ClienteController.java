package br.com.barber.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barber.integration.controller.dto.ClienteDto;
import br.com.barber.integration.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public Page<ClienteDto> lista(@PageableDefault(direction = Direction.ASC, sort = "id", size = 10) Pageable page) {
		Page<ClienteDto> clientes = ClienteDto.converter(clienteService.findAll(page));
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhe(@PathVariable Long id) {
		ClienteDto cliente = new ClienteDto(clienteService.findById(id));
		return ResponseEntity.ok(cliente);
	}
	
}
