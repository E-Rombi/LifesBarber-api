package br.com.barber.integration.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.builder.ProdutoBuilder;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.JVM) 
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repository;
	
	@Before
	public void init() {
		List<Produto> produtos = Arrays.asList(new ProdutoBuilder().setNome("Barba").setStatus(ProdutoStatus.ATIVO).setTipo(ProdutoTipo.SERVICO).setValor(new BigDecimal(30.00)).build(),
											   new ProdutoBuilder().setNome("Sobrancelha").setStatus(ProdutoStatus.ATIVO).setTipo(ProdutoTipo.SERVICO).setValor(new BigDecimal(15.00)).build());
		
		repository.saveAll(produtos);
	}
	
	@Test
	public void naoDeveRetornarUmProduto() {
		Optional<Produto> optional = repository.findById(999L);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void deveRetornarUmProduto() {
		Produto produto = repository.getOne(1L);
		assertEquals("Barba", produto.getNome());
	}
	
	@Test
	public void deveCadastrarUmProduto() {
		Produto produto = new ProdutoBuilder().setNome("Combo").setStatus(ProdutoStatus.ATIVO).setTipo(ProdutoTipo.SERVICO).setValor(new BigDecimal(70.00)).build();
		repository.save(produto);
		
		Optional<Produto> optional = repository.findByNome(produto.getNome());
		assertTrue(optional.isPresent());
		assertEquals(produto.getNome(), optional.get().getNome());
	}

	@Test
	public void deveAtualizarUmProduto() {
		
	}
	
}
