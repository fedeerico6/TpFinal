package Excepciones;

public class paswordIncorrecto extends RuntimeException{
	
	public paswordIncorrecto(String mensaje) {
		super(mensaje);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
