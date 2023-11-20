package co.edu.unbosque.Controller.Auth;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Transactional
@RestController
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
public class ClienteLoginRequest {
    private String email;
    private String userPassword;

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
