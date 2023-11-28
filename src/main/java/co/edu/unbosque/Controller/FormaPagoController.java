package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Factura;
import co.edu.unbosque.Model.FormaPago;
import co.edu.unbosque.Service.FormaPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for managing FormaPago entities.
 * It handles HTTP requests related to FormaPago operations.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/FormaPago")
public class FormaPagoController {

    @Autowired
    private FormaPagoService formaPagoService;

    /**
     * @return List<FormaPago>
     */

    @GetMapping
    @Operation(summary = "Obtener todas las formas de pago", description = "Obtiene todas las formas de pago.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formas de pago obtenidas exitosamente", content = @Content(schema = @Schema(implementation = FormaPago.class)))
    })
    public List<FormaPago> listarFormasPago() {
        return formaPagoService.listarFormasPago();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una forma de pago por su ID", description = "Obtiene una forma de pago por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forma de pago obtenida exitosamente", content = @Content(schema = @Schema(implementation = FormaPago.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pago no encontrada")
    })
    public ResponseEntity<FormaPago> obtenerFormaPagoPorId(@PathVariable int id) {
        Optional<FormaPago> formaPago = formaPagoService.obtenerFormaPagoPorId(id);
        return formaPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
