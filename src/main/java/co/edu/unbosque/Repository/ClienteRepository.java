package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The ClienteRepository interface is responsible for managing the persistence
 * of Cliente entities.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * Cliente entity.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	/**
	 * Finds a Cliente entity by its email.
	 *
	 * @param email the email of the Cliente to find
	 * @return the found Cliente entity, or null if not found
	 */
	Cliente findByEmail(String email);
}
