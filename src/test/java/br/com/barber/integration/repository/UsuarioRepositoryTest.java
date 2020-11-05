package br.com.barber.integration.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.barber.integration.model.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	private String nomeEsperado;
	private LocalDate dataNascimentoEsperado;
	
	@Before
	public void init() {
		nomeEsperado = "ALUNO";
		dataNascimentoEsperado = LocalDate.now();
	}
	
	
	@Test
	public void deveCarregarPeloId() {
		Optional<Usuario> optional = repository.findById(1L);
		
		assertEquals(nomeEsperado, optional.get().getNome());
		assertEquals(dataNascimentoEsperado, optional.get().getDataNascimento());
	}
	
	@Test
	public void naoDeveRetornarCliente() {
		Optional<Usuario> optional = repository.findById(999L);
		assertFalse(optional.isPresent());
	}

}
