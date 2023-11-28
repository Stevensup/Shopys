package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Notificacion;
import co.edu.unbosque.Service.NotificacionService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    /**
     * @param idCliente
     * @return
     */

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorCliente(@PathVariable int idCliente) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorCliente(idCliente);
        return ResponseEntity.ok(notificaciones);
    }

    @PostMapping
    public ResponseEntity<Notificacion> guardarNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.guardarNotificacion(notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNotificacion);
    }

    @DeleteMapping("/{idNotificacion}")
    public ResponseEntity<String> eliminarNotificacion(@PathVariable int idNotificacion) {
        boolean eliminada = notificacionService.eliminarNotificacion(idNotificacion);
        if (eliminada) {
            return ResponseEntity.ok("Notificación eliminada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificación no encontrada.");
        }
    }
}
