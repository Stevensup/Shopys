package co.edu.unbosque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Cliente {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	 private int id;  
	    private String nombre;
	    private String apellido;
	    private String email;
	    private String telefono;
	    private String direccion;
	    private String userPassword;
	    private boolean frecuente;
	    private LocalDateTime fechaRegistro;
	    private boolean ctaBloqueada;
	    private int intentosFallidos;
	    public Cliente() {

	    }
	    
		
	    public Cliente(String nombre, String apellido, String email, String telefono, String direccion, String userPassword, boolean frecuente, LocalDateTime fechaRegistro, boolean ctaBloqueada) {
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.email = email;
	        this.telefono = telefono;
	        this.direccion = direccion;
	        this.userPassword = userPassword;
	        this.frecuente = frecuente;
	        this.fechaRegistro = fechaRegistro;
	        this.ctaBloqueada = ctaBloqueada;
	    }
	    

		public int getIntentosFallidos() {
			return intentosFallidos;
		}

		public void setIntentosFallidos(int intentosFallidos) {
			this.intentosFallidos = intentosFallidos;
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


		public String getTelefono() {
			return telefono;
		}


		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}


		public String getDireccion() {
			return direccion;
		}


		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}


		public String getUserPassword() {
			return userPassword;
		}


		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}


		public boolean isFrecuente() {
			return frecuente;
		}


		public void setFrecuente(boolean frecuente) {
			this.frecuente = frecuente;
		}


		public LocalDateTime getFechaRegistro() {
			return fechaRegistro;
		}


		public void setFechaRegistro(LocalDateTime fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}


		public boolean isCtaBloqueada() {
			return ctaBloqueada;
		}


		public void setCtaBloqueada(boolean ctaBloqueada) {
			this.ctaBloqueada = ctaBloqueada;
		}
		
		 @Override
		    public String toString() {
		        return "Cliente{" +
		                "id=" + id +
		                ", nombre='" + nombre + '\'' +
		                ", apellido='" + apellido + '\'' +
		                ", email='" + email + '\'' +
		                ", telefono='" + telefono + '\'' +
		                ", direccion='" + direccion + '\'' +
		                ", userPassword='" + userPassword + '\'' +
		                ", frecuente=" + frecuente +
		                ", fechaRegistro=" + fechaRegistro +
		                ", ctaBloqueada=" + ctaBloqueada +
		                '}';
		    }
	    
	    

}
