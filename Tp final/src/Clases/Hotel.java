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
		this.nombre = nombre;
		this.localidad = localidad;
		this.domicilio = domicilio;
		habitaciones = new HashMap<Integer,Habitacion>();
		//habitaciones = BaseDeDatos.leerArchivoHabitacion();
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
		habitaciones.put(habitacion.getNumeroHabitacion(), habitacion);
	}
	
	public Habitacion comprobarDisponibilidadDeReserva(Reserva comprobar, TipoHabitacion tipo) {
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
