package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

/**
 * This class represents the controller for managing clients in the application.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * @param id
	 * @return ResponseEntity<Cliente>
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtener Cliente por ID", description = "Obtiene un cliente existente según su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente", content = @Content(schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "412", description = "Precondición fallida")
	})
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable int id) {
		Cliente cliente = clienteService.obtenerClientePorId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}

	/**
	 * @param cliente
	 * @return ResponseEntity<Cliente>
	 */
	@PostMapping("/registrar")
	@Operation(summary = "Crear Clientes", description = "crea un cliente de acuerdo a un cuerpo de json.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteController.class))))
	})
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteService.crearCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar Cliente", description = "Actualiza un cliente existente según su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente", content = @Content(schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
		Cliente actualizadoCliente = clienteService.actualizarCliente(id, cliente);
		if (actualizadoCliente != null) {
			return ResponseEntity.ok(actualizadoCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar Cliente", description = "Elimina un cliente existente según su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
		boolean eliminado = clienteService.eliminarCliente(id);
		if (eliminado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
