package Excepciones;

public class FaltaCheckIn extends RuntimeException {
	public FaltaCheckIn(String msj) {
		super(msj);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
