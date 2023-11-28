package co.edu.unbosque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Notificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mensaje;
	private Timestamp fecha;
	private int cliente;

	public
	/**
	 * @return
	 */
	Notificacion() {
	}

	public
	/**
	 * @param id
	 * @param mensaje
	 * @param fecha
	 * @param cliente
	 * @return
	 */
	Notificacion(int id, String mensaje, Timestamp fecha, int cliente) {
		this.id = id;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

}
