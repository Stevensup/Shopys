package co.edu.unbosque.Controller;

import co.edu.unbosque.Service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/correo")
public class CorreoController {

    private final EmailService emailService;

    @Autowired
    public CorreoController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    public String enviarCorreo(@RequestBody String json) {
        emailService.enviarCorreo(json);
        return "Correo enviado exitosamente";
    }
}
