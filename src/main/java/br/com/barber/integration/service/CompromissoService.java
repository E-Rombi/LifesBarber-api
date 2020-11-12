package br.com.barber.integration.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.barber.integration.exception.TipoInvalidoException;
import br.com.barber.integration.model.Compromisso;
import br.com.barber.integration.model.enums.ProdutoTipo;
import br.com.barber.integration.repository.CompromissoRepository;

@Service
public class CompromissoService {
	
	@Autowired
	private CompromissoRepository repository;
	
	public Page<Compromisso> findAll(Pageable page) {
		return repository.findAll(page);
		
	}

	public void save(Compromisso compromisso) throws TipoInvalidoException {
		if (compromisso.getServico().getTipo() != ProdutoTipo.SERVICO) {
			throw new TipoInvalidoException("Compromisso somente com produtos do tipo SERVIÇO !");
		}
		
		repository.save(compromisso);
	}
	
	public void saveAll(List<Compromisso> compromissos) throws TipoInvalidoException {
		List<Compromisso> validados = compromissos.stream().filter(c -> c.getServico().getTipo() == ProdutoTipo.SERVICO).collect(Collectors.toList());
		if (validados.size() != compromissos.size()) {
			repository.saveAll(validados);
			throw new TipoInvalidoException("Compromisso somente com produtos do tipo SERVIÇO !");
		}
		
		repository.saveAll(compromissos);
	}

	public Compromisso findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
