package App;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


import Clases.*;
import Clases.Habitacion.TipoHabitacion;;

public class Main {

	public static void main(String[] args) {
		/*LocalDate fechaInicio = LocalDate.of(2018, 12, 25);
		LocalDate fechaFin = LocalDate.of(2019, 01, 10);
		int contador = 0;
		while(fechaInicio.isBefore(fechaFin) || fechaInicio.isEqual(fechaFin)) { //COMPARA QUE LA FECHA DE INICIO SEA ANTERIOR A LA DE FIN
																				//O QUE LAS FECHAS SEAN IGUAL POR CADA PASADA SE LE SUMA UNO A LA FECHA DE INICIO
			contador++;
			fechaInicio = fechaInicio.plusDays(1);
		}
		System.out.println("La diferencia de dias es de: " + contador);*/
		/*Hotel hotel = new Hotel("Federico", "Mar del plata", "Garay 5253");
		Cliente cliente = new Cliente("mdp", "Federico Elia", "40794525", "garay 5253");
		BaseDeDatos.escribirArchivoClientes(cliente);
		HashMap<String,Cliente> clientes = new HashMap<String,Cliente>();
		clientes = BaseDeDatos.leerArchivoClientes();
		Iterator <Entry<String,Cliente>> it = clientes.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String,Cliente> mapa = (Entry<String,Cliente>)it.next();
				Cliente cliente2 = mapa.getValue();
				System.out.println(cliente2.toString());
		}*/
		
		
		
		
		
		
		/*Habitacion habitacion = new Habitacion();
		Reserva reserva = new Reserva();
		reserva.setReserva(LocalDate.of(2018, 5, 31), LocalDate.of(2018, 6, 6));
		try {
			habitacion.setReserva(reserva);
		} 
		catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		Reserva reserva2 = new Reserva();
		reserva2.setReserva(LocalDate.of(2018, 6, 20), LocalDate.of(2018, 6, 26));
		habitacion.setReserva(reserva2);
		
		Reserva reserva3 = new Reserva();
		reserva3.setReserva(LocalDate.of(2018, 7, 1), LocalDate.of(2018, 7, 12));
		habitacion.setReserva(reserva3);
		
		Reserva nueva = new Reserva();
		nueva.setReserva(LocalDate.of(2018, 6, 10), LocalDate.of(2018, 6, 19));
		
		System.out.println(habitacion.habitacionDisponible(nueva));*/
		

		
		
		
		
	}

}
