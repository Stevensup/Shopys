package co.edu.unbosque.Controller.Auth;

import co.edu.unbosque.Controller.Auth.ClienteLoginRequest;
import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private ClienteService clienteService;
    private Map<String, Integer> intentosFallidos = new HashMap<>();

    /**
     * @param clienteLoginRequest
     * @return ResponseEntity<String>
     */

    @PostMapping
    @Operation(summary = "Autenticar un cliente", description = "Autentica a un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente autenticado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "412", description = "Error de precondición")
    })
    public ResponseEntity<String> autenticarCliente(@RequestBody ClienteLoginRequest clienteLoginRequest) {
        String email = clienteLoginRequest.getEmail();
        String userPassword = clienteLoginRequest.getUserPassword();

        Cliente clienteAutenticado = clienteService.autenticarCliente(email, userPassword);

        if (clienteAutenticado != null && !clienteAutenticado.isCtaBloqueada()) {
            // Restablecer el contador de intentos fallidos si la autenticación es exitosa
            intentosFallidos.remove(email);
            int clienteId = clienteAutenticado.getId();
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\":\"Sesión iniciada\",\"correo\":\"" + email + "\",\"id\":" + clienteId + "}");
        } else if (clienteAutenticado != null && clienteAutenticado.isCtaBloqueada()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La cuenta está bloqueada.");
        } else {
            // Incrementar el contador de intentos fallidos
            intentosFallidos.put(email, intentosFallidos.getOrDefault(email, 0) + 1);

            // Verificar si se superó el límite de intentos fallidos
            if (intentosFallidos.getOrDefault(email, 0) >= 3) {
                clienteService.bloquearCliente(email);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La cuenta está bloqueada.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña inválida");
            }
        }
    }

}
