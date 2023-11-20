package co.edu.unbosque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;


@Entity
public class Factura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    private Cliente cliente;
    private Date fechaFacturacion;
    private double valorCompra;
    private double valorIva;
    private double totalFacturado;
	@ManyToOne
    @JoinColumn(name = "formaPago", referencedColumnName = "id")
    private FormaPago formaPago;


	public Factura() {
	}
	

	public Factura(int id, Cliente cliente, Date fechaFacturacion, double valorCompra, double valorIva, double totalFacturado, FormaPago formaPago) {
		this.id = id;
		this.cliente = cliente;
		this.fechaFacturacion = fechaFacturacion;
		this.valorCompra = valorCompra;
		this.valorIva = valorIva;
		this.totalFacturado = totalFacturado;
		this.formaPago = formaPago;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorIva() {
		return valorIva;
	}

	public void setValorIva(double valorIva) {
		this.valorIva = valorIva;
	}

	public double getTotalFacturado() {
		return totalFacturado;
	}

	public void setTotalFacturado(double totalFacturado) {
		this.totalFacturado = totalFacturado;
	}



	public FormaPago getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
    
    
}

