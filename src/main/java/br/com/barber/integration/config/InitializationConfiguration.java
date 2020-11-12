package br.com.barber.integration.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.barber.integration.exception.TipoInvalidoException;
import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.Usuario;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;
import br.com.barber.integration.service.ClienteService;
import br.com.barber.integration.service.CompromissoService;
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
	
	@Autowired
	private CompromissoService compromissoService;
	
	private Usuario usuario;
	private List<Produto> produtos;
	private List<Cliente> clientes;
	private List<Compromisso> compromissos;
	
	@Override
	public void run(String... args) {
		try {
			usuario  = criaUsuario();
			produtos = Arrays.asList(criaProduto(), criaProduto2(), criaProduto3());
			clientes = Arrays.asList(criaCliente(), criaCliente2());
			compromissos = Arrays.asList(criaCompromisso(), criaCompromisso2());
				
			usuarioService.save(usuario);
			clienteService.saveAll(clientes);
			produtoService.saveAll(produtos);
			compromissoService.saveAll(compromissos);
		} catch (TipoInvalidoException e) {
			System.err.println(e.getMessage());
		}
	}

	private Produto criaProduto() {
		return new Produto(1L, "Corte de Cabelo", new BigDecimal(40.00), ProdutoStatus.ATIVO, ProdutoTipo.SERVICO);
	}

	private Produto criaProduto2() {
		return new Produto(2L, "Cabelo + Barba", new BigDecimal(80.99), ProdutoStatus.ATIVO, ProdutoTipo.SERVICO);
	}
	
	private Produto criaProduto3() {
		return new Produto(3L, "Pomada", new BigDecimal(60.99), ProdutoStatus.ATIVO, ProdutoTipo.REVENDA);
	}
	
	private Usuario criaUsuario() {
		return new Usuario(1L, "Usuario", "1", "9999-9999", LocalDate.now(), "88888888","aluno@email.com", "123", null);
	}
	
	private Compromisso criaCompromisso() {
		return new Compromisso(LocalDate.now(), LocalTime.now(), clientes.get(0), produtos.get(0));
	}
	
	private Compromisso criaCompromisso2() {
		return new Compromisso(LocalDate.now(), LocalTime.now(), clientes.get(1), produtos.get(1));
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
