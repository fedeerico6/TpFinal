package Clases;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	private String nombreCompleto;
	private String dni;
	private String domicilio;
	
	public Persona(String nombreCompleto, String dni, String domicilio) {
		/*
		 * CONSTRUCTOR
		 */
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
		this.domicilio = domicilio;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String toString() {
		return "Nombre y apellido: " + nombreCompleto + " D.N.I: " + dni + " Domicilio: " + domicilio;
	}
}