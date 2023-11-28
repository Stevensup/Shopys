package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Producto;
import co.edu.unbosque.Service.ClienteService;
import co.edu.unbosque.Service.FormaPagoService;
import co.edu.unbosque.Service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/producto")
public class ProductoController {

    // private static final Logger LOGGER =
    // LogManager.getLogger(ShopysApplication.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService; // Agregado

    @Autowired
    private FormaPagoService formaPagoService; // Agregado

    /**
     * @param id
     * @return ResponseEntity<Producto>
     */

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        if (!productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/safe")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        Producto actualizadoProducto = productoService.actualizarProducto(id, producto);
        if (actualizadoProducto != null) {
            // LOGGER.info("Producto actualizado");
            return ResponseEntity.ok(actualizadoProducto);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoService.eliminarProducto(id);
        if (eliminado) {
            // LOGGER.info("Producto eliminado");
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @PostMapping("/actualizar-inventario/{id}/{cantidadVendida}")
    public ResponseEntity<String> actualizarInventario(@PathVariable int id, @PathVariable int cantidadVendida) {
        try {
            System.out.println("cantidadVendida: " + cantidadVendida + " id: " + id);
            // Obten la cantidad disponible actual del producto
            Producto producto = productoService.obtenerProductoPorId(id);
            int cantidadDisponible = producto.getCantidadInventario();

            // Verifica si la cantidad a vender es mayor a la cantidad disponible
            if (cantidadVendida > cantidadDisponible) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: La cantidad a vender es mayor a la cantidad disponible en el inventario");
            }

            // Si la verificación pasa, llama al servicio para actualizar el inventario
            productoService.actualizarInventario(id, cantidadVendida);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Inventario actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el inventario del producto");
        }
    }

}
