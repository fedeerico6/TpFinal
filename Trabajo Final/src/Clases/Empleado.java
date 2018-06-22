package Clases;

import static App.Main.hotel;
import Interfaces.Administracion;
import java.io.Serializable;
import java.util.ArrayList;

import Excepciones.FaltaCheckIn;
import Excepciones.fechaIncorrecta;
/**
 * SE UTILIZA PARA CONTENTER LOS DISTINTOS TIPOS DE EMPLEADOS QUE SE ENCUENTRAN EN EL HOTEL Y PODER DARLES UN COMPORTAMIENTO
 * A ESTOS EMPLEADOS
 * @author Federico
 *
 */
public class Empleado extends Persona implements Serializable, Administracion {
	/**
	 * ATRIBUTOS
	 */
    private String usuario;
    private String pass;

    /**
     * METODOS
     */
    /**
     * CONSTRUCTOR
     * @param nombreCompleto
     * @param dni
     * @param domicilio
     * @param usuario
     * @param pass
     */
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

    @Override
    public void CheckIn(Reserva reserva) {
    	/**
    	 * METODO EN EL CUAL SE LE REALIZA EL CHECK IN A UNA HABITACION MEDIANTA EL CODIGO DE UNA RESERVA
    	 */
        ArrayList<Integer> hab = reserva.GetHabitaciones();
        int tamaño = hab.size();
        int h = 0;
        
        for(Integer numero: hab){
        	Habitacion room = hotel.GetHabitacion(hab.get(h));
        	room.setOcupado(true);
        	h++;
        }

        System.out.println("CheckIn Exitoso");

    }

    public void CheckOut(Reserva reserva) throws fechaIncorrecta {
    	/**
    	 * METODO EN EL CUAL SE LE REALIZA EL CHECK OUT A UNA HABITACION MEDIANTA EL CODIGO DE RESERVA
    	 */
        ArrayList<Integer> hab = reserva.GetHabitaciones();
        double total = 0;
        double precio = 0;
        int h = 0;
        for(Integer numero: hab){
            	
            Habitacion room = hotel.GetHabitacion(hab.get(h));
            verificarPrevioCheckIn(room);
           	int i = reserva.longitudReserva();
           	precio = i * room.getPrecio();
           	total = total + precio;
           	room.setOcupado(false);
           	h++;
        }
        System.out.println("Usted debera abonar: "+total);
        System.out.println("CheckOut Exitoso");
        
        
    }
    
    public String toString() {
    	return super.toString()+" usuario: "+usuario+" pass: "+pass;
    }
    
    public void verificarPrevioCheckIn(Habitacion room) throws FaltaCheckIn {
    	/**
    	 * VERIFICA QUE NO SE LE PUEDA REALIZAR EL CHECK OUT A UNA HABITACION QUE PREVIAMENTE NO SE LE HIZO EL CHECK IN
    	 * 
    	 */
    	if(!room.getOcupado()) {
    		throw new FaltaCheckIn("No se realizo el check in");
    	}
    }
}
