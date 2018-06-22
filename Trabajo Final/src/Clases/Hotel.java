/**
	 * Clase hotel, es la clase encarga de manejar todo lo que suceda ya sea desde reservas hasta nuevos empleados.
	 * Cualquier cosa que se realice esta controlada por esta clase 
	 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import App.Main;
import Clases.Habitacion.Disponibilidad;
import Clases.Habitacion.TipoHabitacion;
import Excepciones.CodigoDeReservaEnUso;
import Excepciones.CodigoDeReservaInexsistente;
import Excepciones.HabitacionesNoDisponibles;
import Excepciones.paswordIncorrecto;
import Excepciones.usuarioIncorrecto;

public class Hotel {
	/**
	 * ATRIBUTOS
	 */
	private String nombre;
	private String localidad;
	private String domicilio;
	private HashMap<Integer,Habitacion> habitaciones;
	private HashMap<String,Reserva> reservasActivas;
	private HashMap<String,Reserva> reservasAntiguas;
	private HashMap<String,Cliente> clientes;
	private HashMap<String,Empleado> empleados;
	
	
	/**
	 * METODOS 
	 */
	public Hotel(String nombre, String localidad, String domicilio) {
		
		/**
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
		
		/**
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
		/**
		 * ESTE METODO RECIBE COMO PARAMETRO UNA RESERVA LA CUAL SE QUIERE COMPROBAR SI ESTA DISPONIBLE
		 * TAMBIEN RECIBE UN TIPO DE HABITACION POR EJEMPLO SI LA RESERVA ES PARA UNA HABITACION DOBLE RECIBE COMO PARAMETRO DOBLE
		 * PARA PODER HACER UN FILTRADO PREVIO Y NO TENES QUE COMPROBAR TODAS LAS RESERVAS
		 */
		boolean estado = false;
		HashSet<Habitacion> habitaciones = mismoTipoHabitacion(tipo);
		for(Habitacion habitacion: habitaciones) {
			//System.out.println("Entro al for");
			if(!estado) {
				estado  = habitacion.habitacionDisponible(comprobar);
				boolean aux = habitacion.comprobarQueLaHabitacionNoEsteEnMantenimiento();
				if(estado && aux) {
					return habitacion;
				}else {
					estado = false;
				}
			}
		}
		if(!estado) {
			throw new HabitacionesNoDisponibles("No hay ninguna habitacion disponible en esa fecha");
		}
		return null;
	}
	
	public Empleado comprobarUsuario(String usuario) throws usuarioIncorrecto {
		/**
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

	public Habitacion reservar(Reserva reserva, TipoHabitacion tipo) throws HabitacionesNoDisponibles {
		/**
		 * ES EL METODO EN EL CUAL SE INSTANCIA UNA HABITACION PARA SABER SI LA RESERVA ESTA DISPONIBLE
		 * SI DEVUELVE LA HABITACION EN NULO ES PORQUE NO HAY HABITACIONES PARA ESA RESERVA
		 */
		
		Habitacion habitacion = null;
			
		habitacion = comprobarDisponibilidadDeReserva(reserva, tipo);
			
		return habitacion;
	}
	
	public void recuperarHabitacionesBaseDeDatos() {
		BaseDeDatos<Integer, Habitacion> archivoHab = new BaseDeDatos<Integer, Habitacion>("habitaciones.dat");
		habitaciones = archivoHab.leerArchivo();
	}
	
	public void recuperarHabitacionesBaseDeDatosV2() {
		/**
		 * UTILIZAR TODO LO V2 CON LO QUE TENGA QUE VER A LA BASE DE DATOS
		 */
		BaseDeDatos<Integer, Habitacion> archivoHab = new BaseDeDatos<Integer, Habitacion>("habitaciones.dat");
		ArrayList<Habitacion> lista = archivoHab.leerArhivoV2();
		for(Habitacion hab: lista) {
			habitaciones.put(hab.getNumeroHabitacion(), hab);
		}
	}
	
	public void recuperarReservasActivasBaseDeDatos() {
		BaseDeDatos<String,Reserva> archivoReser = new BaseDeDatos<String,Reserva>("reservasActivas.dat");
                reservasActivas = archivoReser.leerArchivo();
    }
	
	public void recuperarReservasActivasBaseDeDatosV2() {
		BaseDeDatos<String,Reserva> archivoReser = new BaseDeDatos<String,Reserva>("reservasActivas.dat");
        ArrayList<Reserva> lista = archivoReser.leerArhivoV2();
        for(Reserva r: lista) {
        	reservasActivas.put(r.getCodigoReserva(), r);
        }
    }
	
	public void recuperarReservasAntiguasBaseDeDatos() {
		BaseDeDatos<String,Reserva> archivoReserAntiguas = new BaseDeDatos<String,Reserva>("reservAsantiguas.dat");
        reservasAntiguas = archivoReserAntiguas.leerArchivo();
	}
	
	public void recuperarReservasAntiguasBaseDeDatosV2() {
		BaseDeDatos<String,Reserva> archivoReserAntiguas = new BaseDeDatos<String,Reserva>("reservAsantiguas.dat");
        ArrayList<Reserva> lista = archivoReserAntiguas.leerArhivoV2();
        for(Reserva r: lista) {
        	reservasAntiguas.put(r.getCodigoReserva(), r);
        }
	}
	
    public void recuerarClienteBaseDeDatos(){
        BaseDeDatos<String, Cliente> archivoCliente = new BaseDeDatos<String, Cliente>("clientes.dat");
        clientes = archivoCliente.leerArchivo();
    }
    
    public void recuerarClienteBaseDeDatosV2(){
        BaseDeDatos<String, Cliente> archivoCliente = new BaseDeDatos<String, Cliente>("clientes.dat");
        ArrayList<Cliente> lista = archivoCliente.leerArhivoV2();
        for(Cliente c: lista) {
        	clientes.put(c.getDni(), c);
        }
    }
        
    public void recuperarEmpleadoBaseDeDatos(){
       BaseDeDatos<String, Empleado> archEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
       empleados = archEmpleado.leerArchivo();
    }
    
    public void recuperarEmpleadoBaseDeDatosV2(){
        BaseDeDatos<String, Empleado> archEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
        ArrayList<Empleado> lista = archEmpleado.leerArhivoV2();
        for(Empleado e: lista) {
        	empleados.put(e.getDni(), e);
        }
     }
        
	public void fijarCodigoReserva(Reserva reserva) {
		/**
		 * ES EL METODO EN EL CUAL SE FIJA UN NUEVO CODIGO DE RESERVA
		 * PARA LA RESERVA QUE SE ENVIA POR PARAMETRO
		 */
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
		/**
		 * ES EL CODIGO QUE COMPRUEBA QUE EL CODIGO DE LA RESERVA YA NO ESTE EN USO
		 * ASI SE LE PUEDE ASIGNAR UN NUEVO CODIGO A LA NUEVA RESERVA
		 */
		
		if(reservasActivas.containsKey(codigo)) {
			throw new CodigoDeReservaEnUso("Codigo de reserva en uso");
		}
		if(reservasAntiguas.containsKey(codigo)) {
			throw new CodigoDeReservaEnUso("Codigo de reserva en uso");
		}
	}
        
    public void listarHabitaciones(){
    	/**
    	 * TE MUESTRA POR PANTALLAS EL LISTADO DE TODAS LAS HABITACIONES 
    	 */
       Iterator<Entry<Integer,Habitacion>> it = habitaciones.entrySet().iterator();
       while(it.hasNext()){
          Entry<Integer, Habitacion> mapa = (Entry<Integer, Habitacion>)it.next();
          Habitacion habitacion = mapa.getValue();
          System.out.println(habitacion.toString());
          System.out.println("");     
       }
    }
    
    public void listarReservasActivas(){
    	/**
    	 * TE MUESTRA POR PANTALLA EL LISTADO DE TODAS LAS RESERVAS ACTIVAS
    	 */
       Iterator<Entry<String,Reserva>> it = reservasActivas.entrySet().iterator();
       while(it.hasNext()){
           Entry<String,Reserva> mapa = (Entry<String,Reserva>)it.next();
           Reserva reserva = mapa.getValue();
           System.out.println("Reserva activa  " + reserva.toString());
           System.out.println("");     
        }
    }
        
    public void listarReservasAntiguas(){
    	/**
    	 * TE MUESTRA POR PANTALLA EL LISTADO DE TODAS LAS RESERVAS ANTIGUAS
    	 */
    	Iterator<Entry<String,Reserva>> it = reservasAntiguas.entrySet().iterator();
    	while(it.hasNext()){
    		Entry<String,Reserva> mapa = (Entry<String,Reserva>)it.next();
    		Reserva reserva = mapa.getValue();
    		System.out.println(reserva.toString());
    		System.out.println("");     
    	}
    }

    public void listarClientes(){
    	/**
    	 * TE MUESTRA POR PANTALLA TODOS LOS CLIENTES GUARDADOS EN EL SISTEMA
    	 */
    	Iterator<Entry<String,Cliente>> it = clientes.entrySet().iterator();
    	while(it.hasNext()){
    		Entry<String,Cliente> mapa = (Entry<String,Cliente>)it.next();
    		Cliente cliente = mapa.getValue();
    		System.out.println(cliente.toString());
    		System.out.println("");     
    	} 
    }

    public void listarEmpleados(){
    	/**
    	 * TE MUESTRA POR PANTALLA LA LISTA DE EMPLEADOS
    	 */
    	Iterator<Entry<String,Empleado>> it = empleados.entrySet().iterator();
    	while(it.hasNext()){
    		Entry<String,Empleado> mapa = (Entry<String,Empleado>)it.next();
    		Empleado empleado = mapa.getValue();
    		System.out.println(empleado.toString());
    		System.out.println("");     
    	}
    }

    public Habitacion GetHabitacion(Integer k){
    	/**
    	 * TE DUVUELVE UNA HABITACION SEGUN EL NUMERO QUE LE MANDES POR PARAMETRO
    	 */

    	Habitacion room = habitaciones.get(k);
    	return room;
    }   

    public Reserva getReserva(String code){
    	/**
    	 * TE DEVUELE LA RESERVA QUE LE CORRESPONDA AL CODIGO QUE LE MANDASTE POR PARAMETRO
    	 */
    	return reservasActivas.get(code);
    }

    public void gaurdarMapasEnArchivos() {
    	/**
    	 * GUARDA TODO LO QUE CONTIENEN LOS MAPAS EN LOS ARCVÇHIVOS
    	 */
    	guardarMapaCliente();
    	guardarMapaEmpleados();
    	guardarMapaHabitaciones();
    	guardarMapaReservasActivas();
    	guardarMapaReservasAntiguas();
    }

    public void guardarMapaHabitaciones() {
    	BaseDeDatos<Integer, Habitacion> archHabitaciones = new BaseDeDatos<Integer, Habitacion>("habitaciones.dat");
    	archHabitaciones.escribirArchivoV2(habitaciones);
    }

    public void guardarMapaReservasActivas() {
    	BaseDeDatos<String, Reserva> archRese = new BaseDeDatos<String,Reserva>("reservasActivas.dat");
    	archRese.escribirArchivoV2(reservasActivas);
    }

    public void guardarMapaReservasAntiguas() {
    	BaseDeDatos<String, Reserva> archRese = new BaseDeDatos<String,Reserva>("reservasAntiguas.dat");
    	archRese.escribirArchivoV2(reservasAntiguas);
    }

    public void guardarMapaCliente() {
    	BaseDeDatos<String, Cliente> archCLientes = new BaseDeDatos<String,Cliente>("clientes.dat");
    	archCLientes.escribirArchivoV2(clientes);
    }

    public void guardarMapaEmpleados() {
    	BaseDeDatos<String, Empleado> archEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
    	archEmpleado.escribirArchivoV2(empleados);
    }

    public void borrarReservaActiva(Reserva r) {
    	reservasActivas.remove(r.getCodigoReserva());
    }
    
    public void eliminarReserva(String codigo) throws CodigoDeReservaInexsistente {
    	if(reservasActivas.remove(codigo)==null) {
    		throw new CodigoDeReservaInexsistente("El codigo de reserva que ingreso no exsiste");
    	}
    }
    
    public void comprobarValidezCodigoDeReservaActiva(String codigo) {
    	if(reservasActivas.get(codigo)==null) {
    		throw new CodigoDeReservaInexsistente("El codigo ingresado no exsiste");
    	}
    }
}
