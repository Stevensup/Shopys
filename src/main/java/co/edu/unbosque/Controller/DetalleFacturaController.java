package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.DetalleFactura;
import co.edu.unbosque.Service.DetalleFacturaService;
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
@RequestMapping("/detalles-factura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> obtenerTodosLosDetallesFactura() {
        List<DetalleFactura> detallesFactura = detalleFacturaService.obtenerTodosLosDetallesFactura();
        return ResponseEntity.ok(detallesFactura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerDetalleFacturaPorId(@PathVariable int id) {
        Optional<DetalleFactura> detalleFactura = detalleFacturaService.obtenerDetalleFacturaPorId(id);
        return detalleFactura.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping({"/guardar"})
    public ResponseEntity<DetalleFactura> guardarDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        DetalleFactura nuevoDetalleFactura = detalleFacturaService.guardarDetalleFactura(detalleFactura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalleFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalleFactura(@PathVariable int id) {
        boolean eliminado = detalleFacturaService.eliminarDetalleFactura(id);
        if (eliminado) {
            return ResponseEntity.ok("Detalle de factura eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de factura no encontrado.");
        }
    }
}
