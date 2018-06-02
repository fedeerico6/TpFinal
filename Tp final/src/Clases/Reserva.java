package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva {
	private ArrayList<Integer> habitaciones;
	private String dni;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private boolean estado;
	private String codigoReserva;
	
	public Reserva(ArrayList<Integer> habitaciones, String dni, LocalDate fechaInicio, LocalDate fechaFin) {
		/*
		 * CONSTRUCTOR
		 */
		this.habitaciones = new ArrayList<Integer>(habitaciones);
		this.dni = dni;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		estado = true;
	}

	public Reserva() {
		/*
		 * CONSTRUCTOR
		 */
		habitaciones = new ArrayList<Integer>();
		dni = "";
		codigoReserva = "";
		estado = true;
	}

	
	public void setReserva(LocalDate inicio, LocalDate fin) {
		fechaInicio = inicio;
		fechaFin = fin;
	}

	public void setCodigoreserva(String codigoReserva) {
		codigoReserva = codigoReserva;
	}
	
	public String getCodigoReserva() {
		return codigoReserva;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
