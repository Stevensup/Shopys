package co.edu.unbosque.Service;

import co.edu.unbosque.Model.Notificacion;
import co.edu.unbosque.Repository.NotificacionRepository;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    private final Logger logger = LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionRepository notificacionRepository;

    /**
     * @param idCliente
     * @return
     */
    public List<Notificacion> obtenerNotificacionesPorCliente(int idCliente) {
        return notificacionRepository.findByCliente(idCliente);
    }

    /**
     * @param notificacion
     * @return Notificacion
     */
    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public boolean eliminarNotificacion(int idNotificacion) {
        if (notificacionRepository.existsById(idNotificacion)) {
            notificacionRepository.deleteById(idNotificacion);
            return true;
        } else {
            return false;
        }
    }
}
