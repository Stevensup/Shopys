package co.edu.unbosque.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int cliente;
	private String direccionEntrega;
	private Date fecha;

	public
	/**
	 * @param id
	 * @param cliente
	 * @param direccionEntrega
	 * @param fecha
	 * @return
	 */
	Domicilio(int id, int cliente, String direccionEntrega, Date fecha) {
		this.id = id;
		this.cliente = cliente;
		this.direccionEntrega = direccionEntrega;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
