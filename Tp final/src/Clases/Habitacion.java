package Clases;

import java.io.Serializable;
import java.util.HashSet;

public class Habitacion implements Serializable {
	private boolean ocupado;
	private Disponibilidad disponible;
	private TipoHabitacion tipo;
	private int numero;
	private double precio;
	private Reserva reservaActual;
	private HashSet<Reserva> reservas;
	
	public Habitacion() {
		/*
		 * CONSTRUCTOR DE LA HABITACION VACIO, INICIALIZA TODAS SUS VARIABLES
		 */
		this.tipo = null;
		this.numero = 0;
		this.precio = 0;
		ocupado = false;
		disponible = Disponibilidad.DISPONIBLE;
		reservas = new HashSet<Reserva>();
		reservaActual = new Reserva();
	}
	
	public Habitacion(TipoHabitacion tipo, double precio,int numero) {
		/*
		 * CONSTRUCTOR EN LA CUAL SE RECIBE UN TIPO DE HABITACION EL PRECIO Y EL NUMERO DE LA HABITACION.
		 */
		this.tipo = tipo;
		this.numero = numero;
		this.precio = precio;
		ocupado = false;
		disponible = Disponibilidad.DISPONIBLE;
		reservaActual = null;
		reservas = new HashSet<Reserva>();
	}
	
	public enum TipoHabitacion {
		/*
		 *  ENUMERACION EN LA CUAL FIGURA QUE TIPOS DE HABITACIONES SE ENCUENTRAN EN EL HOTEL
		 */
		SIMPLE,
		DOBLE,
		TRIPLE,
		CUADRUPLE,
		SEXTUPLE;
	}
	
	public enum Disponibilidad{
		/*
		 * ENUMERACION QUE TE DICE EN QUE ESTADO ESTA LA HABITACION
		 */
		DISPONIBLE,
		LIMPIEZA,
		DESINFECCION,
		REPARACION,
	}
	
	@Override
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitacion other = (Habitacion) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Disponibilidad getDisponible() {
		return disponible;
	}

	public void setDisponible(Disponibilidad disponible) {
		this.disponible = disponible;
	}
	
	public boolean getOcupado() {
		return ocupado;
	}

	public double getPrecio() {
		return precio;
	}

	public TipoHabitacion getTipoHabitacion() {
		return tipo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getNumeroHabitacion() {
		return numero;
	}
	
	public void setReserva(Reserva reserva) {
		reservas.add(reserva);
	}
	
	public boolean fechaDisponible(Reserva reserva, Reserva comprobar) {
		/*
		 * METODO EN EL CUAL RECIBE DOS RESERVAS Y EVALUA QUE 
		 * AMBAS RESERVAS NO SE SUPERPONGAN 
		 */
		if(!reserva.getFechaInicio().isAfter(comprobar.getFechaFin())) {
			if((!reserva.getFechaInicio().isBefore(comprobar.getFechaFin()))||(!reserva.getFechaFin().isBefore(comprobar.getFechaInicio()))) {
				if((reserva.getFechaInicio().isBefore(comprobar.getFechaFin()))&&(comprobar.getFechaInicio().isBefore(reserva.getFechaFin()))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean habitacionDisponible(Reserva comprobar) {
		/*
		 * METODO EN EL CUAL RECIBE UNA RESERVA PARA LUEGO COMPROBAR
		 * EN LA LISTA DE RESERVAS DE LA HABITACION SI ESTA RESERVA 
		 * ENVIADA POR PARAMETRO SE ENCUENTRA DISPONIBLE
		 */
		boolean estado = true; 
		for(Reserva reserva: reservas) {
			if(estado) {
				estado = fechaDisponible(reserva, comprobar);
			}else {
				return false;
			}
		}
		return estado;
	}

	public String toString() {
		return "Numero: " + numero + " Tipo: " + tipo;
	}
}
