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

import br.com.barber.integration.controller.dto.ProdutoDto;
import br.com.barber.integration.controller.dto.validacao.MessageDto;
import br.com.barber.integration.controller.form.ProdutoForm;
import br.com.barber.integration.controller.form.ProdutoFormAtualizacao;
import br.com.barber.integration.model.Produto;
import br.com.barber.integration.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
@Api(tags = "Produto", description = "Operações de Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	@ApiOperation(value = "Lista todos os produtos de acordo com parâmetros passados")
	public Page<ProdutoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<ProdutoDto> produtos = ProdutoDto.converter(produtoService.findAll(page));
		
		return produtos;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Mais informações do Produto")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
		ProdutoDto produto = new ProdutoDto(produtoService.findById(id));
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Cadastrar novo Produto")
	public ResponseEntity<ProdutoDto> inserir(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder builder) {
		Produto produto = form.converter();
		produtoService.save(produto);
		
		URI uri = builder.path("/clientes/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Atualizar o Produto")
	public ResponseEntity<ProdutoDto> atualizar(@RequestBody @Valid ProdutoFormAtualizacao form, @PathVariable Long id) {
		Produto produto = produtoService.findById(id);
		form.atualizar(produto);
		
		return ResponseEntity.ok(new ProdutoDto(produto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Deletar o Produto")
	public ResponseEntity<MessageDto> deletar(@PathVariable Long id) {
		produtoService.deleteById(id);
		
		return ResponseEntity.ok(new MessageDto("Registro com id("+ id +") deletado com sucesso !"));
	}
	
}
