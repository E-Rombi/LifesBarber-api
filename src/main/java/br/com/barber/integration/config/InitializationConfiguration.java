package br.com.barber.integration.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;
import br.com.barber.integration.service.ClienteService;
import br.com.barber.integration.service.ProdutoService;
import br.com.barber.integration.service.UsuarioService;

@Configuration
@Profile(value = {"dev", "test"})
public class InitializationConfiguration implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario  = criaUsuario();
		List<Produto> produtos = Arrays.asList(criaProduto(), criaProduto2());
		List<Cliente> clientes = Arrays.asList(criaCliente(), criaCliente2());
			
		usuarioService.save(usuario);
		clienteService.saveAll(clientes);
		produtoService.saveAll(produtos);
	}

	private Produto criaProduto() {
		return new Produto(1L, "Corte de Cabelo", new BigDecimal(40.00), ProdutoStatus.ATIVO, ProdutoTipo.SERVICO);
	}

	private Produto criaProduto2() {
		return new Produto(2L, "Shampoo L'oreal", new BigDecimal(160.99), ProdutoStatus.ATIVO, ProdutoTipo.REVENDA);
	}
	
	private Usuario criaUsuario() {
		return new Usuario(1L, "Usuario", "1", "9999-9999", LocalDate.now(), "88888888","aluno@email.com", "123", null);
	}

	private Cliente criaCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("99999999");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setNome("Aluno 1");
		cliente.setSobrenome("UNIMEP");
		cliente.setTelefone("11111111");
		return cliente;
	}
	
	private Cliente criaCliente2() {
		Cliente cliente = new Cliente();
		cliente.setId(2L);
		cliente.setCpf("999999299");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setNome("Aluno 2");
		cliente.setSobrenome("UNIMEP");
		cliente.setTelefone("11111111");
		return cliente;
	}
	
}
