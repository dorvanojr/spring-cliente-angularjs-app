package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.repository.ClienteRepository;
import app.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;
    

	public Cliente findClienteById(Long id) {
        return repository.findById(id).orElse(new Cliente());
    }

    public List<Cliente> findAllByOrderByNomeAsc() {
        return repository.findAllByOrderByNomeAsc();
    }

    public Cliente findByNome(String nome) {
        return repository.findByNome(nome);
    }
    

    public void saveCliente(Cliente cliente) {
        repository.save(cliente);
    }

    public void updateCliente(Cliente cliente) {
        repository.save(cliente);
    }

    public void deleteClienteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllClientes() {
        repository.deleteAll();
    }

    public boolean isClienteExist(Cliente cliente) {
        return findByNome(cliente.getNome()) != null;
    }

 

}
