package Excepciones;

public class fechaIncorrecta extends RuntimeException {

	public fechaIncorrecta(String mensaje) {
		super(mensaje);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
