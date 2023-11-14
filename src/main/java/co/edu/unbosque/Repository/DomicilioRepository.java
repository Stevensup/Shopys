package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
