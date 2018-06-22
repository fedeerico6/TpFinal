package Clases;

import java.io.Serializable;
/**
 * CLASE EN LA CUAL SE ALMACENAN TODOS LOS DATOS DE LOS CLIENTES
 * @author Federico
 *
 */
public class Cliente extends Persona implements Serializable{
	/**
	 * ATRIBUTOS
	 */
	private String origen;
	
	/**
	 * METODOS
	 */
	/**
	 * CONSTRUCTOS DEL CLIENTE
	 * @param origen
	 * @param nombreCompleto
	 * @param dni
	 * @param domicilio
	 */
	public Cliente(String origen, String nombreCompleto, String dni, String domicilio) {
		
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
