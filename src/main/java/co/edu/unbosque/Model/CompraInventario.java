package co.edu.unbosque.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompraInventario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int producto;
    private int cantidadAdquirida;
    private Date fecha;
    private int proveedor;
    private int formaPago;

    public CompraInventario(int id, int producto, int cantidadAdquirida, Date fecha, int proveedor, int formaPago) {
        this.id = id;
        this.producto = producto;
        this.cantidadAdquirida = cantidadAdquirida;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.formaPago = formaPago;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidadAdquirida() {
		return cantidadAdquirida;
	}

	public void setCantidadAdquirida(int cantidadAdquirida) {
		this.cantidadAdquirida = cantidadAdquirida;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public int getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}

    
}
