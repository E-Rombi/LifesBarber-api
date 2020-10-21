package br.com.barber.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barber.integration.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
}
