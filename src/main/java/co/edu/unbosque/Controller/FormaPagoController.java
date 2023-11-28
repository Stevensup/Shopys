package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.FormaPago;
import co.edu.unbosque.Service.FormaPagoService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/FormaPago")
public class FormaPagoController {

    @Autowired
    private FormaPagoService formaPagoService;

    /**
     * @return List<FormaPago>
     */

    @GetMapping
    public List<FormaPago> listarFormasPago() {
        return formaPagoService.listarFormasPago();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPago> obtenerFormaPagoPorId(@PathVariable int id) {
        Optional<FormaPago> formaPago = formaPagoService.obtenerFormaPagoPorId(id);
        return formaPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
