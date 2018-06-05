package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import Excepciones.fechaIncorrecta;

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
	
	public void comprobarFormatoFecha(int anio, int mes, int dia) throws fechaIncorrecta {
		if(anio>2017 && mes<=12) {
			if(mes == 2) {
				if(dia>28) {
					throw new fechaIncorrecta("Febrero no tiene mas de 28 dias");
				}
			} else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12) {
				if(dia>31) {
					throw new fechaIncorrecta("Ingreso mal el dia");
				}
			} else {
				if(dia>30) {
					throw new fechaIncorrecta("Ingreso mal el dia");
				}
			}
		}
		else {
			throw new fechaIncorrecta("Ingreso mal el año o el mes");
		}
	}
	
}
