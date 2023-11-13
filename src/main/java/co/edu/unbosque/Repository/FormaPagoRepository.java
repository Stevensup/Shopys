package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
    // Puedes añadir consultas específicas si es necesario
}