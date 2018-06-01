package Clases;

public class Cliente extends Persona {
	private String origen;
	
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
