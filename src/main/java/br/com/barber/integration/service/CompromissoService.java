package br.com.barber.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.repository.CompromissoRepository;

public class CompromissoService {
	
	@Autowired
	private CompromissoRepository repository;
	
	public Page<Compromisso> findAll(Pageable page) {
		return repository.findAll(page);
		
	}

}
