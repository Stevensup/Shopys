package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Domicilio;
import co.edu.unbosque.Model.Factura;
import co.edu.unbosque.Repository.ClienteRepository;
import co.edu.unbosque.Service.FacturaService;
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

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;
    private ClienteRepository clienteRepository;

    /**
     * @param facturaService
     * @param clienteRepository
     * @return
     */
    @Autowired
    public FacturaController(FacturaService facturaService, ClienteRepository clienteRepository) {
        this.facturaService = facturaService;
        this.clienteRepository = clienteRepository;
    }

    /**
     * @return ResponseEntity<List<Factura>>
     */
    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Obtiene todas las facturas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturas obtenidas exitosamente", content = @Content(schema = @Schema(implementation = Factura.class)))
    })
    public ResponseEntity<List<Factura>> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener factura por ID", description = "Obtiene una factura por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura obtenida exitosamente", content = @Content(schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable int id) {
        Optional<Factura> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/guardar-factura")
    @Operation(summary = "Guardar una factura", description = "Guarda una nueva factura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura guardada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al guardar la factura")
    })
    public ResponseEntity<String> guardarFactura(@RequestBody Factura factura) {
        try {
            facturaService.crearFactura(factura);

            return ResponseEntity.ok("Factura guardada correctamente." + factura);
        } catch (Exception e) {
            e.printStackTrace();
            // Imprimir el body de la factura en caso de error
            System.out.println("Body de la factura en caso de error: " + factura);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la factura: " + e.getMessage());
        }
    }

    @PostMapping("/crear-factura")
    @Operation(summary = "Crear una factura", description = "Crea una nueva factura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada exitosamente", content = @Content(schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "500", description = "Error al crear la factura")
    })
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura facturaCreada = facturaService.crearFactura(factura);
        return new ResponseEntity<>(facturaCreada, HttpStatus.CREATED);
    }

}
