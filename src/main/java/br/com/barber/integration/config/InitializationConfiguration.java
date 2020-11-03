package br.com.barber.integration.config;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.repository.UsuarioRepository;
import br.com.barber.integration.service.ClienteService;

@Configuration
@Profile(value = {"dev", "test"})
public class InitializationConfiguration implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario = criaUsuario();
		Cliente cliente  = criaCliente();
		
			
		userRepository.save(usuario);
		clienteService.save(cliente);
	}

	private Usuario criaUsuario() {
		return new Usuario(1L, "aluno", "1", "9999-9999", LocalDate.now(), "88888888","aluno@email.com", "123", null);
	}

	private Cliente criaCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setCpf("99999999");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setNome("Aluno 1");
		cliente.setSobrenome("UNIMEP");
		cliente.setTelefone("11111111");
		return cliente;
	}
	
}
