package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Model.DetalleFactura;
import co.edu.unbosque.Service.DetalleFacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a controller for managing DetalleFactura objects.
 * It handles HTTP requests related to DetalleFactura entities, such as
 * retrieving all details of a factura,
 * retrieving a detail of a factura by ID, saving a new detail of a factura, and
 * deleting a detail of a factura.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/detalles-factura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    /**
     * @return ResponseEntity<List<DetalleFactura>>
     */

    @GetMapping
    @Operation(summary = "Obtener todos los detalles de factura", description = "Obtiene todos los detalles de factura existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de factura encontrados exitosamente", content = @Content(schema = @Schema(implementation = DetalleFactura.class))),
            @ApiResponse(responseCode = "412", description = "Precondición fallida")
    })
    public ResponseEntity<List<DetalleFactura>> obtenerTodosLosDetallesFactura() {
        List<DetalleFactura> detallesFactura = detalleFacturaService.obtenerTodosLosDetallesFactura();
        return ResponseEntity.ok(detallesFactura);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de factura por ID", description = "Obtiene un detalle de factura existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de factura encontrado exitosamente", content = @Content(schema = @Schema(implementation = DetalleFactura.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de factura no encontrado")
    })
    public ResponseEntity<DetalleFactura> obtenerDetalleFacturaPorId(@PathVariable int id) {
        Optional<DetalleFactura> detalleFactura = detalleFacturaService.obtenerDetalleFacturaPorId(id);
        return detalleFactura.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping({ "/guardar" })
    @Operation(summary = "Guardar detalle de factura", description = "Guarda un nuevo detalle de factura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de factura guardado exitosamente", content = @Content(schema = @Schema(implementation = DetalleFactura.class)))
    })
    public ResponseEntity<DetalleFactura> guardarDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        DetalleFactura nuevoDetalleFactura = detalleFacturaService.guardarDetalleFactura(detalleFactura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalleFactura);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de factura por ID", description = "Elimina un detalle de factura existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de factura eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle de factura no encontrado")
    })
    public ResponseEntity<String> eliminarDetalleFactura(@PathVariable int id) {
        boolean eliminado = detalleFacturaService.eliminarDetalleFactura(id);
        if (eliminado) {
            return ResponseEntity.ok("Detalle de factura eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de factura no encontrado.");
        }
    }
}
