package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Notificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The NotificacionRepository interface represents a repository for managing
 * Notificacion entities.
 */
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    /**
     * Retrieves a list of notifications by the specified client ID.
     *
     * @param idCliente The ID of the client.
     * @return A list of notifications associated with the client.
     */
    List<Notificacion> findByCliente(int idCliente);
}
