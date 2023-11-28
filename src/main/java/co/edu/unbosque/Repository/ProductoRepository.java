/**
 * The ProductoRepository interface is responsible for providing access to the Producto entity in the database.
 * It extends the JpaRepository interface, which provides basic CRUD operations for the entity.
 */
package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
