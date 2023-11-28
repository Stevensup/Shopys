package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Domicilio;
import co.edu.unbosque.Service.DomicilioService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    /**
     * @return ResponseEntity<List<Domicilio>>
     */

    @GetMapping
    public ResponseEntity<List<Domicilio>> obtenerTodosLosDomicilios() {
        List<Domicilio> domicilios = domicilioService.obtenerTodosLosDomicilios();
        return ResponseEntity.ok(domicilios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
        Optional<Domicilio> domicilio = domicilioService.obtenerDomicilioPorId(id);
        return domicilio.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio) {
        Domicilio nuevoDomicilio = domicilioService.guardarDomicilio(domicilio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDomicilio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable int id) {
        boolean eliminado = domicilioService.eliminarDomicilio(id);
        if (eliminado) {
            return ResponseEntity.ok("Domicilio eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Domicilio no encontrado.");
        }
    }
}
