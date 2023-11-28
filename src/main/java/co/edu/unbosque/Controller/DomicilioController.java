package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.DetalleFactura;
import co.edu.unbosque.Model.Domicilio;
import co.edu.unbosque.Service.DomicilioService;
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
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    /**
     * @return ResponseEntity<List<Domicilio>>
     */

    @GetMapping
    @Operation(summary = "Obtener todos los domicilios", description = "Obtiene todos los domicilios existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Domicilios encontrados exitosamente", content = @Content(schema = @Schema(implementation = Domicilio.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron domicilios")
    })
    public ResponseEntity<List<Domicilio>> obtenerTodosLosDomicilios() {
        List<Domicilio> domicilios = domicilioService.obtenerTodosLosDomicilios();
        return ResponseEntity.ok(domicilios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un domicilio por ID", description = "Obtiene un domicilio existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Domicilio encontrado exitosamente", content = @Content(schema = @Schema(implementation = Domicilio.class))),
            @ApiResponse(responseCode = "404", description = "Domicilio no encontrado")
    })
    public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
        Optional<Domicilio> domicilio = domicilioService.obtenerDomicilioPorId(id);
        return domicilio.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Guardar un domicilio", description = "Guarda un nuevo domicilio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Domicilio guardado exitosamente", content = @Content(schema = @Schema(implementation = Domicilio.class)))
    })
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio) {
        Domicilio nuevoDomicilio = domicilioService.guardarDomicilio(domicilio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDomicilio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un domicilio por ID", description = "Elimina un domicilio existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Domicilio eliminado exitosamente", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Domicilio no encontrado")
    })
    public ResponseEntity<String> eliminarDomicilio(@PathVariable int id) {
        boolean eliminado = domicilioService.eliminarDomicilio(id);
        if (eliminado) {
            return ResponseEntity.ok("Domicilio eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Domicilio no encontrado.");
        }
    }
}
