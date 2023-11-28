package co.edu.unbosque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "FACTURA")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO")
    private Producto producto;

    private int cantidad;
    private BigDecimal subtotalProducto;

    /**
     * @return
     */

    public DetalleFactura() {
    }

    public DetalleFactura(Factura factura, Producto producto, int cantidad, BigDecimal subtotalProducto) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotalProducto = subtotalProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotalProducto() {
        return subtotalProducto;
    }

    public void setSubtotalProducto(BigDecimal subtotalProducto) {
        this.subtotalProducto = subtotalProducto;
    }
}
