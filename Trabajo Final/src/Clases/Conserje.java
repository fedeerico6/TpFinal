package Clases;
/**
 * SE UTILIZA PARA DIFERENCIAR LOS DISTINTOS TIPOS DE EMPLEADOS QUE SE ENCUENTRAN EN EL HOTEL
 * @author Federico
 *
 */
public class Conserje extends Empleado {
	/**
	 * CONSTRUCTOS DE LA CLASE 
	 * @param nombreCompleto
	 * @param dni
	 * @param domicilio
	 * @param usuario
	 * @param pass
	 */
	public Conserje(String nombreCompleto, String dni, String domicilio, String usuario, String pass) {
		super(nombreCompleto, dni, domicilio, usuario, pass);
	}

}
