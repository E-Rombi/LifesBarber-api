package br.com.barber.integration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.barber.integration.model.Cliente;
import br.com.barber.integration.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public void saveAll(List<Cliente> clientes) {
		clienteRepository.saveAll(clientes);
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		return clienteOptional.get();
	}
	
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
}
