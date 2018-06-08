package Clases;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable{
	private String origen;
	
	public Cliente(String origen, String nombreCompleto, String dni, String domicilio) {
		/*
		 * CONSTRUCTOR DEL CLIENTE
		 */
		super(nombreCompleto, dni, domicilio);
		this.origen = origen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
