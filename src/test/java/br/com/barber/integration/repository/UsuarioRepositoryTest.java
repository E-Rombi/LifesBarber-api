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

import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.model.builder.UsuarioBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
@FixMethodOrder(MethodSorters.JVM)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	private String nomeEsperado;
	private LocalDate dataNascimentoEsperado;
	private Usuario usuario;
	
	@Before
	public void init() {
		nomeEsperado = "USUARIO";
		dataNascimentoEsperado = LocalDate.now();
		usuario = new UsuarioBuilder()
							.setCpf("222.222.222-22")
							.setDataNascimento(LocalDate.now())
							.setEmail("testes@email.com")
							.setNome("Mike")
							.setSenha("123")
							.setSobrenome("Jenkins")
							.build();
	}
	
	@Test
	public void deveCadastrarUmUsuario() {
		repository.save(usuario);
		
		Optional<Usuario> optional = repository.findByEmail(usuario.getEmail());
		assertTrue(optional.isPresent());
		assertEquals(optional.get().getNome(), usuario.getNome());
	}
	
	
	@Test
	public void deveCarregarPeloId() {
		Optional<Usuario> optional = repository.findById(888L);
		
		assertEquals(nomeEsperado, optional.get().getNome());
		assertEquals(dataNascimentoEsperado, optional.get().getDataNascimento());
	}
	
	@Test
	public void naoDeveRetornarCliente() {
		Optional<Usuario> optional = repository.findById(999L);
		assertFalse(optional.isPresent());
	}

}
