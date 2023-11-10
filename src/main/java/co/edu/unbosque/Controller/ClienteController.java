package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;
import co.edu.unbosque.ShopysApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private static final Logger LOGGER = LogManager.getLogger(ShopysApplication.class);

		
	@Autowired
	private ClienteService clienteService;

	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable int id) {
		Cliente cliente = clienteService.obtenerClientePorId(id);
		if (cliente != null) {
			LOGGER.info("Cliente encontrado");
			return ResponseEntity.ok(cliente);	
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}

	@PostMapping("/safe")
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteService.crearCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}
	


	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
		Cliente actualizadoCliente = clienteService.actualizarCliente(id, cliente);
		if (actualizadoCliente != null) {
			return ResponseEntity.ok(actualizadoCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
		boolean eliminado = clienteService.eliminarCliente(id);
		if (eliminado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
