package br.com.barber.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barber.integration.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
	
}
