package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
