package co.edu.unbosque.Controller;

import co.edu.unbosque.Model.Producto;
import co.edu.unbosque.Service.ClienteService;
import co.edu.unbosque.Service.FormaPagoService;
import co.edu.unbosque.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador para la gestión de productos.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/producto")
public class ProductoController {

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
    @Operation(summary = "Obtener un producto por su ID", description = "Obtiene un producto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/productos")
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista de todos los productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron productos", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        if (!productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/safe")
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente", content = @Content(schema = @Schema(implementation = Producto.class)))
    })
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        Producto actualizadoProducto = productoService.actualizarProducto(id, producto);
        if (actualizadoProducto != null) {
            return ResponseEntity.ok(actualizadoProducto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "412", description = "Error de precondición")
    })
    public ResponseEntity<Producto> eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoService.eliminarProducto(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    // ...

    @PostMapping("/actualizar-inventario/{id}/{cantidadVendida}")
    @Operation(summary = "Actualizar inventario de un producto", description = "Actualiza el inventario de un producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Error: La cantidad a vender es mayor a la cantidad disponible en el inventario", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error al actualizar el inventario del producto", content = @Content(schema = @Schema(implementation = String.class)))
    })
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
