package Clases;

import java.io.Serializable;

/**
 * CLASE QUE NO TIENE COMPORTAMIENTO PARTICULAR SOLAMENTE EXTIENDE DE EMPLEADO
 * SE UTILIZA PARA DIFERENCIAR LOS DISTINTOS TIPOS DE EMPLEADOS QUE SE ENCUENTRAN EN EL HOTEL
 * @author Federico
 *
 */
public class Administrativo extends Empleado implements Serializable{

    public Administrativo(String nombreCompleto, String dni, String domicilio, String usuario, String pass) {
        super(nombreCompleto, dni, domicilio, usuario, pass);
    }

}
