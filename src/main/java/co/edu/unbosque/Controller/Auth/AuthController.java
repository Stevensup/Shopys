package co.edu.unbosque.Controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> autenticarCliente(@RequestParam String email, @RequestParam String userPassword) {
        Cliente clienteAutenticado = clienteService.autenticarCliente(email, userPassword);

        if (clienteAutenticado != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Sesión iniciada: "+ email); // Autenticación exitosa
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Autenticación fallida
        }
    }
}

