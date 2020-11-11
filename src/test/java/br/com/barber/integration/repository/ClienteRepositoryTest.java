package br.com.barber.integration.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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
import br.com.barber.integration.model.builder.ClienteBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.JVM)
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository repository;
	private String nomeEsperado;
	private LocalDate dataNascimentoEsperado;
	private Cliente cliente;
	
	@Before
	public void init() {
		nomeEsperado = "ALUNO";
		dataNascimentoEsperado = LocalDate.now();
		cliente = new ClienteBuilder()
						.setNome("Matheus")
						.setSobrenome("Viera")
						.setCpf("772.654.152-54")
						.setDataNascimento(LocalDate.now())
						.setTelefone("(77)7777-7777")
						.build();
		
	}
	
	@Test
	public void naoDeveRetornarCliente() {
		Optional<Cliente> optional = repository.findById(999L);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void deveCadastrarUmCliente() {
		repository.save(cliente);
		Optional<Cliente> optional = repository.findByCpf(cliente.getCpf());
		assertTrue(optional.isPresent());
		assertEquals(optional.get().getNome(), cliente.getNome());
	}
	
	@Test
	public void deveCarregarPeloId() {
		Optional<Cliente> optional = repository.findById(888L);
		assertEquals(nomeEsperado, optional.get().getNome());
		assertEquals(dataNascimentoEsperado, optional.get().getDataNascimento());
	}

}
