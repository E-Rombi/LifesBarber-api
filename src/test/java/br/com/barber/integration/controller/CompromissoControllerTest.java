package br.com.barber.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

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
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest
public class CompromissoControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	private String jsonPost = "{\"data\":\"2018-09-15\", \"hora\":\"15:53:00\", \"cliente\":\"1\", \"servico\":\"2\"}";
	private String jsonPut  = "{\"data\":\"2020-09-15\", \"hora\":\"20:53:00\", \"cliente\":\"1\", \"servico\":\"2\"}";
	
	@Test
	public void deveRetornarUmCompromisso() throws Exception {
		mock.perform(MockMvcRequestBuilders
						.get("/compromissos/1")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
		
	}
	
	@Test
	public void deveAtualizarUmCompromisso() throws Exception {
		mock.perform(MockMvcRequestBuilders
						.put(new URI("/compromissos/1"))
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPut))
						.andDo(print())
						.andExpect(status().isOk());
	}
	
	@Test
	public void deveDeletarUmCompromisso() throws URISyntaxException, Exception {
		mock.perform(MockMvcRequestBuilders
						.delete(new URI("/compromissos/1")))
						.andDo(print())
						.andExpect(status().isOk());
	}
	
}
