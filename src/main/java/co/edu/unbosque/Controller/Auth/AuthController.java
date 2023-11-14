package co.edu.unbosque.Controller.Auth;

import co.edu.unbosque.Controller.Auth.ClienteLoginRequest;
import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;
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
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> autenticarCliente(@RequestBody ClienteLoginRequest clienteLoginRequest) {
        String email = clienteLoginRequest.getEmail();
        String userPassword = clienteLoginRequest.getUserPassword();

        Cliente clienteAutenticado = clienteService.autenticarCliente(email, userPassword);

        if (clienteAutenticado != null) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Sesión iniciada: " + email + "\"}");
        } else {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("usuario o contrasena invalida"); // Autenticación exitosa
        }
    }
}
