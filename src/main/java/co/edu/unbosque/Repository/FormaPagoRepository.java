/**
 * This interface represents a repository for the FormaPago entity.
 * It extends the JpaRepository interface, providing CRUD operations for the FormaPago entity.
 */
package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
    // Puedes añadir consultas específicas si es necesario
}