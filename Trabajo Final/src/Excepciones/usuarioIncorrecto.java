package Excepciones;

public class usuarioIncorrecto extends RuntimeException {
	
	public usuarioIncorrecto(String mensaje) {
		super(mensaje);
	}
	public String getMessage() {
		return super.getMessage();
	}
}