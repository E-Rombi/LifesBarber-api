package br.com.barber.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barber.integration.controller.dto.ProdutoDto;
import br.com.barber.integration.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	public Page<ProdutoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<ProdutoDto> produtos = ProdutoDto.converter(produtoService.findAll(page));
		
		return produtos;
	}
	
}
