package app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import app.entity.Cliente;
import app.service.ClienteService;
import app.service.impl.ClienteServiceImpl;
import app.util.ConverterUtil;
import app.util.CustomErrorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Rest Cliente")
@RestController
@RequestMapping("/api")
public class ClienteController {

	public static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	ClienteService clienteService;

	// Lista todos os Clientes
	@ApiOperation(value = "Lista todos os Clientes")
	@RequestMapping(value = "/cliente/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listAllClientes() {
		List<Cliente> clientes = clienteService.findAllByOrderByNomeAsc();
		if (clientes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	// Pega um Cliente
	@ApiOperation(value = "Pega um Cliente por ID ")
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCliente(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.findClienteById(id);
		if (cliente == null) {
			logger.error("Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Cria o Cliente
	@ApiOperation(value = "Cria o Cliente")
	@RequestMapping(value = "/cliente/", method = RequestMethod.POST)
	public ResponseEntity<?> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
		if (clienteService.isClienteExist(cliente)) {
			logger.error("Não é possível criar. Cliente com nome {} já existe", cliente.getNome());
			return new ResponseEntity(
					new CustomErrorType("Não é possível criar. Cliente com nome " + cliente.getNome() + " já existe."),
					HttpStatus.CONFLICT);
		}

		 String senha = cliente.getPassword();
 	     
	     cliente.setPassword(ConverterUtil.criptografaSenha(cliente.getPassword()));
	     cliente.setPorcentagem(ConverterUtil.senhaForte(senha));
   

		clienteService.saveCliente(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// Atualiza o Cliente
	@ApiOperation(value = "Atualiza o Cliente")
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		if (clienteService.findClienteById(id) == null) {
			logger.error("Não é possível atualizar. Cliente com id {} não encontrado.", id);
			return new ResponseEntity(
					new CustomErrorType("Não é possível atualizar. Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}

		clienteService.updateCliente(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Exclui o Cliente
	@ApiOperation(value = "Exclui o Cliente by ID")
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCliente(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.findClienteById(id);
		if (cliente == null) {
			logger.error("Não é possível excluir. Cliente com id {} não encontrado.", id);
			return new ResponseEntity(
					new CustomErrorType("Não é possível excluir. Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		clienteService.deleteClienteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	// Exclui todos os Clientes
	@ApiOperation(value = "Exclui todos os Clientes")
	@RequestMapping(value = "/cliente/", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deleteAllClientes() {
		clienteService.deleteAllClientes();
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}


}
