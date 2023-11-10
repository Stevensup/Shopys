package co.edu.unbosque.Repository;

import co.edu.unbosque.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
        
}
