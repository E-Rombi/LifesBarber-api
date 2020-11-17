package br.com.barber.integration.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.builder.ClienteBuilder;
import br.com.barber.integration.model.builder.CompromissoBuilder;
import br.com.barber.integration.model.builder.ProdutoBuilder;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;

@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.JVM)
@ActiveProfiles("test")
public class CompromissoRepositoryTest {

	@Autowired
	private CompromissoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	private Compromisso compromissoEsperado;
	private Produto produtoEsperado;
	private Cliente clienteEsperado;
	
	@Before
	public void init() {
		clienteEsperado = clienteRepository.save(new ClienteBuilder()
										.setCpf("777.555.666-33")
										.setNome("Angela")
										.setSobrenome("Montenegro")
										.build());
		produtoEsperado = produtoRepository.save(new ProdutoBuilder()
										.setNome("Dread")
										.setStatus(ProdutoStatus.ATIVO)
										.setTipo(ProdutoTipo.SERVICO)
										.setValor(new BigDecimal(95.00))
										.build());
		
		compromissoEsperado = repository.save(new CompromissoBuilder()
														.setData(LocalDate.now())
														.setHora(LocalTime.now())
														.setCliente(clienteEsperado)
														.setServico(produtoEsperado)
														.build());
	}

	@Test
	public void naoDeveRetornarUmCompromisso() {
		Optional<Compromisso> optional = repository.findById(999L);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void deveRetornarUmCompromisso() {
		Compromisso compromisso = repository.findById(1L).get();
		assertEquals(compromissoEsperado, compromisso);
		assertEquals(compromissoEsperado.getCliente(), compromisso.getCliente());
		assertEquals(compromissoEsperado.getServico(), compromisso.getServico());
	}
	
	@Test
	public void deveCadastrarUmCompromisso() {
		Compromisso compromissoEsperado = new CompromissoBuilder()
				.setData(LocalDate.MIN)
				.setHora(LocalTime.MIN)
				.setCliente(clienteRepository.findByCpf("777.555.666-33").get())
				.setServico(produtoRepository.findByNome("Dread").get())
				.build();
		repository.save(compromissoEsperado);
		System.out.println(compromissoEsperado);
		
		Optional<Compromisso> optional = repository.findById(2L);
		assertTrue(optional.isPresent());
		assertEquals(compromissoEsperado.getCliente(), optional.get().getCliente());
	}
		
}
