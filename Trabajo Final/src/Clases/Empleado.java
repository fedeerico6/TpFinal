package Clases;

import static App.Main.hotel;
import Interfaces.Administracion;
import java.io.Serializable;
import java.util.ArrayList;

import Excepciones.FaltaCheckIn;
import Excepciones.fechaIncorrecta;

public class Empleado extends Persona implements Serializable, Administracion {

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

    @Override
    public void CheckIn(Reserva reserva) {

        ArrayList<Integer> hab = reserva.GetHabitaciones();
        int tamaño = hab.size();
        int h = 0;
        
        /*while (h < tamaño) {

            Habitacion room = hotel.GetHabitacion(hab.get(h));
            System.out.println("nro" + h);
            room.setOcupado(true);
            hotel.setHabitaciones(room);
            h++;
        }*/
        
        for(Integer numero: hab){
        	Habitacion room = hotel.GetHabitacion(hab.get(h));
        	room.setOcupado(true);
        	h++;
        }

        System.out.println("CheckIn Exitoso");

    }

    public void CheckOut(Reserva reserva) throws fechaIncorrecta {

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
    	if(!room.getOcupado()) {
    		throw new FaltaCheckIn("No se realizo el check in");
    	}
    }
}
