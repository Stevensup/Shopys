package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Factura;
import co.edu.unbosque.Service.ClienteService;
import co.edu.unbosque.Service.FacturaService;
import co.edu.unbosque.Service.FormaPagoService;
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
@RequestMapping("/facturas")
public class FacturaController {

     @Autowired
    private FacturaService facturaService;

    @Autowired
    private ClienteService clienteService; // Agregado
    @Autowired
    private FormaPagoService formaPagoService; // Agregado

    @GetMapping
    public ResponseEntity<List<Factura>> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable int id) {
        Optional<Factura> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    
    @PostMapping("/guardar-factura")
    public ResponseEntity<String> guardarFactura(@RequestBody Factura factura) {
        try {
            facturaService.crearFactura(factura);
            return ResponseEntity.ok("Factura guardada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la factura: " + e.getMessage());
        }
    }

    @PostMapping("/crear-factura")
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura facturaCreada = facturaService.crearFactura(factura);
        return new ResponseEntity<>(facturaCreada, HttpStatus.CREATED);
    }


}
    

