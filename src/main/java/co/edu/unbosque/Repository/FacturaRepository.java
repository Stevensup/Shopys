/**
 * This interface represents a repository for the Factura entity.
 * It extends the JpaRepository interface, providing CRUD operations for the Factura entity.
 */
package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
