package Clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import App.Main;
import Clases.Habitacion.Disponibilidad;
import Clases.Habitacion.TipoHabitacion;
import Excepciones.CodigoDeReservaEnUso;
import Excepciones.HabitacionesNoDisponibles;
import Excepciones.paswordIncorrecto;
import Excepciones.usuarioIncorrecto;

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
	
        public void setReservaActiva(Reserva reserva) {
		reservasActivas.put(reserva.getCodigoReserva(), reserva);
	}
	
	public void setEmpleado(Empleado empleado) {
		empleados.put(empleado.getUsuario(), empleado);
	}
	
        public void setCliente(Cliente cliente){
            clientes.put(cliente.getDni(), cliente);
        }
        
        public void setReservaAntiguas(Reserva reserva) {
		reservasAntiguas.put(reserva.getCodigoReserva(), reserva);
	}
        
        public HashMap<Integer, Habitacion> getHabitaciones(){
		return habitaciones;
	}
	
        public HashMap<String, Reserva> getReservaActivas(){
            return reservasActivas;
        }
        
        public HashMap<String, Reserva> getReservasAntiguas(){
            return reservasAntiguas;
        }
        
        public HashMap<String, Cliente> getClientes(){
            return clientes;
        }
        
        public HashMap<String, Empleado> getEmpleados(){
            return empleados;
        }
	
        public Habitacion comprobarDisponibilidadDeReserva(Reserva comprobar, TipoHabitacion tipo) throws HabitacionesNoDisponibles {
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
		if(!estado) {
			throw new HabitacionesNoDisponibles("No hay ninguna habitacion disponible en esa fecha");
		}
		return null;
	}
	
	public Empleado comprobarUsuario(String usuario) throws usuarioIncorrecto {
		/*
		 * COMPRUEBA QUE EL USUARIO INGRESADO POR EL USUARIO
		 * SEA EL CORRECTO, EN CASO DE NO SER EL CORRECTO VUELVE A PEDIR
		 * QUE SE INGRESE NUEVAMENTE
		 */
		boolean estado = false;
		Empleado empleado = null;
		Iterator<Entry<String,Empleado>> it = empleados.entrySet().iterator();
		while(it.hasNext() && estado==false) {
			Entry<String,Empleado> mapa = (Entry<String, Empleado>)it.next();
			if(mapa.getValue().getUsuario().equals(usuario)) {
				estado = true;
				empleado = mapa.getValue();
			}
		}
		if(estado==false) {
			throw new usuarioIncorrecto("Usuario incorrecto");
		}
		return empleado;
	}
	
	public void comprobarPass(String pass) throws paswordIncorrecto {
		/*
		 *COMPRUEBA QUE LA CONTRASE�IA INGRESADA SEA LA CORRECTA
		 *EN CASO DE NO SERLA VUELVE A PEDIR QUE SE INGRESE
		 *NUEVAMENTE LA CONTRASE�A 
		 */
		boolean estado = false;
		Iterator<Entry<String,Empleado>> it = empleados.entrySet().iterator();
		while(it.hasNext() && estado==false) {
			Entry<String,Empleado> mapa = (Entry<String, Empleado>)it.next();
			if(mapa.getValue().getPass().equals(pass)) {
				estado = true;
				
			}
		}
		if(estado==false) {
			throw new paswordIncorrecto("Contrase�a incorrecto");
		}
	}

	public Habitacion reservar(Reserva reserva, TipoHabitacion tipo) {
		Habitacion habitacion = null;
		try {

			habitacion = comprobarDisponibilidadDeReserva(reserva, tipo);
			//habitacion.setReserva(reserva);
			
			
		} catch (HabitacionesNoDisponibles e) {
			System.out.println(e.getMenssage());
			System.out.println("");
			//Main.menuConserje();
                       
		} 
               // System.out.println("me segui ejecutando");
		return habitacion;
	}
	
	public void recuperarHabitacionesBaseDeDatos() {
		BaseDeDatos<Integer, Habitacion> archivoHab = new BaseDeDatos<Integer, Habitacion>("habitaciones.dat");
		habitaciones = archivoHab.leerArchivo();
	}
	
	public void recuperarReservasActivasBaseDeDatos() {
		BaseDeDatos<String,Reserva> archivoReser = new BaseDeDatos<String,Reserva>("reservasActivas.dat");
                reservasActivas = archivoReser.leerArchivo();
        }
	
	public void recuperarReservasAntiguasBaseDeDatos() {
		BaseDeDatos<String,Reserva> archivoReserAntiguas = new BaseDeDatos<String,Reserva>("reservAsantiguas.dat");
                reservasAntiguas = archivoReserAntiguas.leerArchivo();
	}
	
        public void recuerarClienteBaseDeDatos(){
                BaseDeDatos<String, Cliente> archivoCliente = new BaseDeDatos<String, Cliente>("clientes.dat");
                clientes = archivoCliente.leerArchivo();
                }
        
        public void recuperarEmpleadoBaseDeDatos(){
            BaseDeDatos<String, Empleado> archEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
            empleados = archEmpleado.leerArchivo();
        }
        
	public void fijarCodigoReserva(Reserva reserva) {
		String codigo = reserva.fijarCodigoReserva();
		try {
			comprobarCodigoReserva(codigo);
			reserva.setCodigoreserva(codigo);
			//reservasActivas.put(codigo, reserva);
			
			
		} catch (CodigoDeReservaEnUso e){
			fijarCodigoReserva(reserva);
		}
		
	}
	
	public void comprobarCodigoReserva(String codigo) throws CodigoDeReservaEnUso {
		if(reservasActivas.containsKey(codigo)) {
			throw new CodigoDeReservaEnUso("Codigo de reserva en uso");
		}
		if(reservasAntiguas.containsKey(codigo)) {
			throw new CodigoDeReservaEnUso("Codigo de reserva en uso");
		}
	}
        
        public void listarHabitaciones(){
            Iterator<Entry<Integer,Habitacion>> it = habitaciones.entrySet().iterator();
            while(it.hasNext()){
                Entry<Integer, Habitacion> mapa = (Entry<Integer, Habitacion>)it.next();
                Habitacion habitacion = mapa.getValue();
                System.out.println(habitacion.toString());
                System.out.println("");     
            }
        }
        
        public void listarReservasActivas(){
            Iterator<Entry<String,Reserva>> it = reservasActivas.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,Reserva> mapa = (Entry<String,Reserva>)it.next();
                Reserva reserva = mapa.getValue();
                System.out.println(reserva.toString());
                System.out.println("");     
            }
        }

        public void listarClientes(){
           Iterator<Entry<String,Cliente>> it = clientes.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,Cliente> mapa = (Entry<String,Cliente>)it.next();
                Cliente cliente = mapa.getValue();
                System.out.println(cliente.toString());
                System.out.println("");     
            } 
        }
        
        public void listarEmpleados(){
            Iterator<Entry<String,Empleado>> it = empleados.entrySet().iterator();
            while(it.hasNext()){
                Entry<String,Empleado> mapa = (Entry<String,Empleado>)it.next();
                Empleado empleado = mapa.getValue();
                System.out.println(empleado.toString());
                System.out.println("");     
            }
        }
               
          public Habitacion GetHabitacion(Integer k){
            
            Habitacion room = habitaciones.get(k);
            return room;
            
            
        }   
        
         public Reserva getReserva(String code){
             return reservasActivas.get(code);
         }

         public void gaurdarMapasEnArchivos() {
        	 guardarMapaCliente();
        	 guardarMapaEmpleados();
        	 guardarMapaHabitaciones();
        	 guardarMapaReservasActivas();
        	 guardarMapaReservasAntiguas();
         }
         
         public void guardarMapaHabitaciones() {
        	 BaseDeDatos<Integer, Habitacion> archHabitaciones = new BaseDeDatos<Integer, Habitacion>("habitaciones.dat");
        	 archHabitaciones.escribirArchivo(habitaciones);
         }
         
         public void guardarMapaReservasActivas() {
        	 BaseDeDatos<String, Reserva> archRese = new BaseDeDatos<String,Reserva>("reservasActivas.dat");
        	 archRese.escribirArchivo(reservasActivas);
         }
         
         public void guardarMapaReservasAntiguas() {
        	 BaseDeDatos<String, Reserva> archRese = new BaseDeDatos<String,Reserva>("reservasAntiguas.dat");
        	 archRese.escribirArchivo(reservasAntiguas);
         }
         
         public void guardarMapaCliente() {
        	 BaseDeDatos<String, Cliente> archCLientes = new BaseDeDatos<String,Cliente>("clientes.dat");
        	 archCLientes.escribirArchivo(clientes);
         }
         
         public void guardarMapaEmpleados() {
        	 BaseDeDatos<String, Empleado> archEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
        	 archEmpleado.escribirArchivo(empleados);
         }
}
