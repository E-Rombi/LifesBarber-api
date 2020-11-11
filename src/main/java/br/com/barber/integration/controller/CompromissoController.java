package br.com.barber.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barber.integration.controller.dto.CompromissoDto;
import br.com.barber.integration.service.CompromissoService;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {

	@Autowired
	private CompromissoService compromissoService;
	
	@GetMapping
	public Page<CompromissoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		return null; /*implementando*/
	}
	
}
