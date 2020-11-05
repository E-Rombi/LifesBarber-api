package br.com.barber.integration.controller;

import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Test
	public void deveRetornarUmCliente() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.get(new URI("/clientes/1"))
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("Aluno 1")));
		
	}

}
