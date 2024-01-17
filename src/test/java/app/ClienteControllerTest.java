/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import app.controller.ClienteController;
import app.entity.Cliente;
import app.repository.ClienteRepository;
import app.service.ClienteService;
import app.service.impl.ClienteServiceImpl;

/**
 *
 * @author Danilo
 */
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
	
	@InjectMocks
    ClienteService clienteService = new ClienteServiceImpl();
	
	
	@Mock
	ClienteRepository repository;

    public ClienteControllerTest() {
    }
    


    @BeforeClass
    public static void setUpClass() {
    	
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    	 MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createCliente() {
        // Cria objeto cliente
        Cliente cliente = new Cliente("Cliente", "123456", "10%");

        // Valida id do cliente
        assertNull(cliente.getId());
        
        when(repository.findByNome(cliente.getNome())).thenReturn(cliente);
 
        // Verifica se o cliente já existe
        clienteService.isClienteExist(cliente);
        
        // Localiza cliente
        clienteService.findClienteById(cliente.getId());

        // Lista todos os clientes
        clienteService.findAllByOrderByNomeAsc();

        // Salva os dados
        clienteService.saveCliente(cliente);
    }
    
    @Test
    public void deleteCliente() {
    	
    	repository.deleteById(1L);
        // Deleta cliente
        clienteService.deleteClienteById(new Long(1));        
    }
    
    @Test
    public void listAllClientes() {
       	Cliente cliente = new Cliente("Cliente", "123456", "10%");
    	
    	
    	List<Cliente> clientes = new ArrayList<>();
    	clientes.add(cliente);
 
    	
    	
    	when(repository.findAllByOrderByNomeAsc()).thenReturn(clientes);
    	
    	
    	List<Cliente> lista = clienteService.findAllByOrderByNomeAsc();
    	
    	assertEquals(1, lista.size());
    }
    
    
    @Test
    public void getCliente() {
       	Cliente cliente = new Cliente(1L,"Cliente", "123456", "10%");
    	
     
    	
    	when(repository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
    	
    	
    	Cliente cliente1 = clienteService.findClienteById(1L);
    	
    	assertEquals("1", cliente1.getId().toString());
    }
    
    @Test
    public void updateCliente() {
    	Cliente cliente = new Cliente(1L,"Cliente", "123456", "10%");
    	
    	
    	when(repository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
    	
    	
    	Cliente cliente1 = clienteService.findClienteById(1L);
        
    	assertEquals("1", cliente1.getId().toString());
    	
    	clienteService.updateCliente(cliente);
    }
    
    
    @Test
    public void deleteAllClientes() {
    	
    	repository.deleteAll();
    	
    	clienteService.deleteAllClientes();
    }
}
