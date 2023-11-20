package co.edu.unbosque.Service;

import co.edu.unbosque.Model.Producto;
import co.edu.unbosque.Repository.ProductoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class ProductoService {
    

    private final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Producto crearProducto(Producto producto) {
        logger.info("Llegó al método crearProducto. Producto: {}", producto);
        Producto nuevoProducto = productoRepository.save(producto);
        logger.info("Producto guardado en la base de datos. Nuevo producto: {}", nuevoProducto);
        return nuevoProducto;
    }

    public Producto actualizarProducto(int id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        } else {
            return null;
        }
    }

    @Transactional
    public void actualizarInventario(int id, int cantidadVendida) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            int nuevoInventario = producto.getCantidadInventario() - cantidadVendida;
            producto.setCantidadInventario(nuevoInventario);
            productoRepository.save(producto);
        }
    }

    public boolean eliminarProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    
public List<Producto> obtenerTodosLosProductos() {
    return productoRepository.findAll();
}




    


}
