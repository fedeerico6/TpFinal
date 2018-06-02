package Clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import Clases.Habitacion.Disponibilidad;
import Clases.Habitacion.TipoHabitacion;

public class Hotel {
	private String nombre;
	private String localidad;
	private String domicilio;
	private HashMap<Integer,Habitacion> habitaciones;
	private HashMap<String,Reserva> reservasActivas;
	private HashMap<String,Reserva> reservasAntiguas;
	private HashMap<String,Cliente> clientes;
	private HashMap<String,Empleado> empleados;
	
	public Hotel(String nombre, String localidad, String domicilio) {
		
		/*
		 * CONSTRUCTOR DEL HOTEL
		 */
		
		this.nombre = nombre;
		this.localidad = localidad;
		this.domicilio = domicilio;
		habitaciones = new HashMap<Integer,Habitacion>();
		
		/*BaseDeDatos<Integer, Habitacion> archivoHabitaciones = new BaseDeDatos<>();
		habitaciones = archivoHabitaciones.leerArchivo("habitacion.dat");*/
		
		
		reservasActivas = new HashMap<String,Reserva>();
		//reservasActivas = BaseDeDatos.leerArchivoReservasActuales();
		reservasAntiguas = new HashMap<String,Reserva>();
		//reservasAntiguas = BaseDeDatos.leerArchivoReservasAntiguas();
		clientes = new HashMap<String,Cliente>();
		//clientes = BaseDeDatos.leerArchivoClientes();
		empleados = new HashMap<String,Empleado>();
		//empleados = BaseDeDatos.leerArchivoEmpleado();
	}
	
	public HashSet<Habitacion> mismoTipoHabitacion(TipoHabitacion tipo){
		
		/*
		 * Este metodo recibe como parametro un tipo de habitacion (SIMPLE DOBLE TRIPLE CUADRUPLE ETC)
		 * y recorre el mapa de habitaciones y encuentra las que son del mismo tipo y las devuelve en un HashSet
		 * 
		 */
		HashSet<Habitacion> mismoTipoHabitaciones = new HashSet<Habitacion>();
		Habitacion aux;
		Iterator<Entry<Integer, Habitacion>> it = habitaciones.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer,Habitacion> recorrer = (Entry<Integer,Habitacion>)it.next();
			aux = recorrer.getValue();
			if((aux.getTipoHabitacion().equals(tipo))&&(aux.getDisponible().equals(Disponibilidad.DISPONIBLE))) {
				mismoTipoHabitaciones.add(aux);
			}
		}
		return mismoTipoHabitaciones;
	}
	
	public void setHabitaciones(Habitacion habitacion) {
		/*
		 * SETEA UNA NUEVA HABITACION EN EL MAPA DE HABITACIONES
		 */
		habitaciones.put(habitacion.getNumeroHabitacion(), habitacion);
	}
	
	public Habitacion comprobarDisponibilidadDeReserva(Reserva comprobar, TipoHabitacion tipo) {
		/*
		 * ESTE METODO RECIBE COMO PARAMETRO UNA RESERVA LA CUAL SE QUIERE COMPROBAR SI ESTA DISPONIBLE
		 * TAMBIEN RECIBE UN TIPO DE HABITACION POR EJEMPLO SI LA RESERVA ES PARA UNA HABITACION DOBLE RECIBE COMO PARAMETRO DOBLE
		 * PARA PODER HACER UN FILTRADO PREVIO Y NO TENES QUE COMPROBAR TODAS LAS RESERVAS
		 */
		boolean estado = false;
		HashSet<Habitacion> habitaciones = mismoTipoHabitacion(tipo);
		for(Habitacion habitacion: habitaciones) {
			if(!estado) {
				estado  = habitacion.habitacionDisponible(comprobar);
				if(estado) {
					return habitacion;
				}
			}
		}
		return null;
	}
	
}
