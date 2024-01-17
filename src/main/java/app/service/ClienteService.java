package app.service;

import java.util.List;

import app.entity.Cliente;

public interface ClienteService {
	
	List<Cliente> findAllByOrderByNomeAsc();
	
	Cliente findClienteById(Long id);
	
	boolean isClienteExist(Cliente cliente);
	
	void saveCliente(Cliente cliente);

    void updateCliente(Cliente cliente);
    
    void deleteClienteById(Long id);
    
    void deleteAllClientes();

}
