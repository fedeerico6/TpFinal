package Excepciones;

public class CodigoDeReservaInexsistente extends RuntimeException {
	
	public CodigoDeReservaInexsistente(String msj) {
		super(msj);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
