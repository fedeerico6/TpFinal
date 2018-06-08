package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import Excepciones.CodigoDeReservaEnUso;
import Excepciones.fechaIncorrecta;
import java.io.Serializable;

public class Reserva implements Serializable {

    private ArrayList<Integer> habitaciones;
    private String dni;
    private String nombreCompleto;
    private String origen;
    private String domicilio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean estado;
    private String codigoReserva;

    public Reserva(String dni, LocalDate fechaInicio, LocalDate fechaFin) {
        /*
		 * CONSTRUCTOR
         */
        this.habitaciones = new ArrayList<Integer>();
        this.dni = dni;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        estado = true;
        codigoReserva = fijarCodigoReserva();
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
    public void setHabitacion(int habitacion){
        habitaciones.add(habitacion);
    }
    public void setCodigoreserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String toString() {
        return "Codigo: "+codigoReserva+" Habitaciones: "+habitaciones.toString();
    }

    public void comprobarFormatoFecha(int anio, int mes, int dia) throws fechaIncorrecta {
        if (mes <= 12) {
            if (mes == 2) {
                if (dia > 28) {
                    throw new fechaIncorrecta("Febrero no tiene mas de 28 dias");
                }
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                if (dia > 31) {
                    throw new fechaIncorrecta("Ingreso mal el dia, ese mes tiene 31 dias");
                }
            } else if (dia > 30) {
                throw new fechaIncorrecta("Ingreso mal el dia, ese mes tiene 30 dias");
            }
        } else {
            throw new fechaIncorrecta("Ingreso mal el mes");
        }
        comprobarFechaActual(anio, mes, dia);
    }

    public void comprobarFechaActual(int anio, int mes, int dia) throws fechaIncorrecta {
        LocalDate date = LocalDate.of(anio, mes, dia);
        if (date.isBefore(LocalDate.now())) {
            throw new fechaIncorrecta("La fecha que ingreso es anterior a la fecha actual");
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoReserva == null) ? 0 : codigoReserva.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Reserva other = (Reserva) obj;
        if (codigoReserva == null) {
            if (other.codigoReserva != null) {
                return false;
            }
        } else if (!codigoReserva.equals(other.codigoReserva)) {
            return false;
        }
        return true;
    }

    public String fijarCodigoReserva() {
        String letras = "ABCDefgh1234";
        String testigo = "";
        int longitudCadena = (int) Math.floor(Math.random() * 12);
        for (int i = 0; i < 12; i++) {
            int caracter = (int) Math.floor(Math.random() * 12); //Generamos la cadena
            testigo = testigo + letras.charAt(caracter);
        }
        //System.out.println(testigo);

        return testigo;

    }

    public void SetNombre(String name) {
        this.nombreCompleto = name;
    }

    public void SetOrigen(String origen) {
        this.origen = origen;
    }

    public void SetDomicilio(String domi) {
        this.domicilio = domi;

    }

    public ArrayList<Integer> GetHabitaciones() {
        return habitaciones;
    }

    public void cobrar() {
    	for(Integer numero: habitaciones) {
    		
    	}
    }
}
