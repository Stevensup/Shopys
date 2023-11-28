package co.edu.unbosque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuariosDrogueria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String userPassword;
	private boolean administrador;
	private boolean ctaBloqueada;

	public
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param userPassword
	 * @param administrador
	 * @param ctaBloqueada
	 * @return
	 */
	UsuariosDrogueria(int id, String nombre, String apellido, String email, String userPassword,
			boolean administrador, boolean ctaBloqueada) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.userPassword = userPassword;
		this.administrador = administrador;
		this.ctaBloqueada = ctaBloqueada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean isCtaBloqueada() {
		return ctaBloqueada;
	}

	public void setCtaBloqueada(boolean ctaBloqueada) {
		this.ctaBloqueada = ctaBloqueada;
	}

}
