package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Factura;
import co.edu.unbosque.Model.Notificacion;
import co.edu.unbosque.Service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for managing notifications.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    /**
     * Retrieves all notifications for a given client.
     *
     * @param idCliente The ID of the client.
     * @return A ResponseEntity containing a list of notifications.
     */
    @GetMapping("/{idCliente}")
    @Operation(summary = "Obtener todas las notificaciones", description = "Obtiene todas las notificaciones por cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificaciones obtenidas exitosamente", content = @Content(schema = @Schema(implementation = Notificacion.class)))
    })
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorCliente(@PathVariable int idCliente) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorCliente(idCliente);
        return ResponseEntity.ok(notificaciones);
    }

    /**
     * Saves a new notification.
     *
     * @param notificacion The notification to be saved.
     * @return A ResponseEntity containing the saved notification.
     */
    @PostMapping
    @Operation(summary = "Guardar una notificación", description = "Guarda una nueva notificación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación guardada exitosamente", content = @Content(schema = @Schema(implementation = Notificacion.class)))
    })
    public ResponseEntity<Notificacion> guardarNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.guardarNotificacion(notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNotificacion);
    }

    /**
     * Deletes a notification by its ID.
     *
     * @param idNotificacion The ID of the notification to be deleted.
     * @return A ResponseEntity containing a success message if the notification was
     *         deleted successfully, or an error message if the notification was not
     *         found.
     */
    @DeleteMapping("/{idNotificacion}")
    @Operation(summary = "Eliminar una notificación", description = "Elimina una notificación por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación eliminada exitosamente", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> eliminarNotificacion(@PathVariable int idNotificacion) {
        boolean eliminada = notificacionService.eliminarNotificacion(idNotificacion);
        if (eliminada) {
            return ResponseEntity.ok("Notificación eliminada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificación no encontrada.");
        }
    }
}
