package Clases;

public class Empleado extends Persona {
	
	private String usuario;
	private String pass;
	
	public Empleado(String nombreCompleto, String dni, String domicilio, String usuario, String pass) {
		/*
		 * CONSTRUCTOR DEL EMPLEADO
		 */
		
		super(nombreCompleto, dni, domicilio);
		this.usuario = usuario;
		this.pass = pass;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
