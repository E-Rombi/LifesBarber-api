package br.com.barber.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.model.builder.ProdutoBuilder;
import br.com.barber.integration.model.enums.ProdutoStatus;
import br.com.barber.integration.model.enums.ProdutoTipo;
import br.com.barber.integration.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ProdutoRepository repository;
	
	private String jsonPost = "{\"nome\":\"Barba\", \"valor\":\"30.00\", \"status\":\"ATIVO\", \"tipo\":\"SERVICO\"}";
	private String jsonPut  = "{\"nome\":\"Barba 2020\", \"valor\":\"40.00\", \"status\":\"ATIVO\", \"tipo\":\"SERVICO\"}";

	@Before
	public void init() {
		List<Produto> produtos = Arrays.asList(new ProdutoBuilder().setNome("Sobrancelha").setStatus(ProdutoStatus.ATIVO).setTipo(ProdutoTipo.SERVICO).setValor(new BigDecimal(15.00)).build(),
											   new ProdutoBuilder().setNome("Combo").setStatus(ProdutoStatus.ATIVO).setTipo(ProdutoTipo.SERVICO).setValor(new BigDecimal(75.00)).build());
				
		repository.saveAll(produtos);
	}
	
	@Test
	public void deveRetornarUmProduto() throws Exception {
		mock.perform(MockMvcRequestBuilders
						.get("/produtos/1")
						.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
	}
	
	@Test 
	public void deveAtualizarUmProduto() throws Exception {
		mock.perform(MockMvcRequestBuilders.put("/produtos/1")
					 	.accept(MediaType.APPLICATION_JSON)
					 	.contentType(MediaType.APPLICATION_JSON)
					 	.content(jsonPut))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
	}
	
	@Test
	public void deveCadastrarUmProduto() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/produtos")
			 	.accept(MediaType.APPLICATION_JSON)
			 	.contentType(MediaType.APPLICATION_JSON)
			 	.content(jsonPost))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("Barba")));
	}
	
	@Test
	public void deveDeletarUmProduto() throws Exception {
		mock.perform(MockMvcRequestBuilders
				.delete("/produtos/2")
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
