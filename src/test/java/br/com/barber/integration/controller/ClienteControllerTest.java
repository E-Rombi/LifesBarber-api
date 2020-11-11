package br.com.barber.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;
import java.net.URISyntaxException;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.JVM)
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	private String jsonPost;
	private String jsonPut;
	
	@Before
	public void init() {
		jsonPost = "{ \"nome\":\"Alex\", \"sobrenome\":\"Jhonson\", \"cpf\": \"444.444.444-44\"}";
		jsonPut = "{ \"nome\":\"Alex\", \"sobrenome\":\"Jhonson\", \"cpf\": \"444.444.123-44\"}";
	}
	
	@Test
	public void deveRetornarUmCliente() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.get(new URI("/clientes/888"))
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("ALUNO")));
		
	}
	
	@Test
	public void deveAtualizarUmCliente() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.put(new URI("/clientes/888"))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPut))
					.andDo(print())
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.nome", not("ALUNO")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(888)));
		
	}
	
	@Test
	public void deveCadastrarUmCliente() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.post(new URI("/clientes"))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPost))
					.andDo(print())
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(4)));
						
	}
	
	@Test
	public void deveDeletarUmCliente() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.delete(new URI("/clientes/2")))
						.andDo(print())
						.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	

}
