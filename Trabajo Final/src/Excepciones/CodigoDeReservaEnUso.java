package Excepciones;

public class CodigoDeReservaEnUso extends RuntimeException{
	

	public CodigoDeReservaEnUso(String mensaje) {
		super(mensaje);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
