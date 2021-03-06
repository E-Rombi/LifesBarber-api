package br.com.barber.integration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.barber.integration.model.Produto;
import br.com.barber.integration.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<Produto> findAll(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public void save(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public void saveAll(List<Produto> produtos) {
		produtoRepository.saveAll(produtos);
	}

	public Produto findById(Long id) {
		return produtoRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}
	
	
	
}
