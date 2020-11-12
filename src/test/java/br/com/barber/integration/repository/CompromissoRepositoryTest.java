package br.com.barber.integration.repository;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.JVM)
@ActiveProfiles("test")
public class CompromissoRepositoryTest {

	@Autowired
	private CompromissoRepository repository;
	
	@Before
	public void init() {
		
	}

	@Test
	public void deveRetornarUmCompromisso() {
		
	}
	
}
