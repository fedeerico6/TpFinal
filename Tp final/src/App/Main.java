package App;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import Clases.*;
import Clases.Habitacion.TipoHabitacion;
import Excepciones.fechaIncorrecta;
import Excepciones.paswordIncorrecto;
import Excepciones.usuarioIncorrecto;;

public class Main {

	public static Hotel hotel = new Hotel("420", "Amnsterdan", "xxx420");
	
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
		
		/*BaseDeDatos<String, Cliente> archivoClientes = new BaseDeDatos<String,Cliente>("clientes.dat");
		HashMap<String, Cliente> clientes = new HashMap<String,Cliente>();
		clientes.put("40794525", new Cliente("mdp", "Federico Elias", "40794525", "garay 5253"));
		clientes.put("40942975", new Cliente("pinamar", "Maia Luques", "409429752", "garay 5253"));
		archivoClientes.escribirArchivo(clientes);
		HashMap<String, Cliente> nuevo = archivoClientes.leerArchivo();
		Iterator<Entry<String, Cliente>> it = nuevo.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Cliente> mapa = (Entry<String, Cliente>)it.next();
			Cliente cliente = mapa.getValue();
			System.out.println(cliente.toString());
		}
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		clientes.put("17128073", new Cliente("mdp", "Ariel Elias", "18127073", "garay"));
		archivoClientes.escribirArchivo(clientes);
		HashMap<String, Cliente> nuevo3 = archivoClientes.leerArchivo();
		Iterator<Entry<String, Cliente>> it2 = nuevo3.entrySet().iterator();
		while(it2.hasNext()) {
			Entry<String, Cliente> mapa2 = (Entry<String, Cliente>)it2.next();
			Cliente cliente2 = mapa2.getValue();
			System.out.println(cliente2.toString());
		}*/
	}
	public static void menuIngreso() {
		int opc = 0;
		Scanner scan = new Scanner(System.in);
		while (opc != 3) {
			System.out.println("1_ Ingresar");
			System.out.println("2_ Ingresar en modo cliente");
			System.out.println("3_ Salir");
			System.out.println();
			System.out.println("Opcion a escojer: ");
			opc = scan.nextInt();
			
			switch (opc) {
			case 1:
				String pass;
				String usuario;
				System.out.println("Usuario: ");
				usuario = scan.next();
				System.out.println("Contrase�ia: ");
				pass = scan.next();
				
				try {
					Empleado empleado = hotel.comprobarUsuario(usuario);
					hotel.comprobarPass(pass);
				} catch (usuarioIncorrecto e) {
					
					System.out.println(e.getMessage());
					System.out.println("");
					menuIngreso();
					
				} catch (paswordIncorrecto e) {
					
					System.out.println(e.getMessage());
					System.out.println("");
					menuIngreso();
					
				} catch (RuntimeException e) {
					
				}
				
				break;
				
			case 2: 
				
				break;
				
			case 3:
				
				break;

			default:
				System.out.println("Ingreso un valor incorrecto");
				System.out.println("");
				break;
			}
			
		}
		scan.close();
	}

	public static void menuConserje() {
		int opc = 0;
		Scanner scan = new Scanner(System.in);
		while (opc != 4) {
			
			System.out.println("1_ Reservar");
			System.out.println("2_ Check in");
			System.out.println("3_ Check out");
			System.out.println("4_ Salir");
			opc = scan.nextInt();
			
			switch (opc) {
			case 1:
				
				TipoHabitacion tipo = menuTipoHabitacion();
				Reserva reserva = ingresoDeReserva();
				System.out.println("Ya se registro la reserva! ");
				System.out.println("");
				break;

			case 2:
				
				break;
				
			case 3:
				
				break;
				
			case 4:
				
				break;
			default:
				
				System.out.println("Opcion Incorrecta");
				System.out.println("");
				
				break;
			}
		}
		scan.close();
	}
	
	public static TipoHabitacion menuTipoHabitacion() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Tipo de habitacion que desea reservar");
		int opc = 0;
		TipoHabitacion tipo = null;
		
			System.out.println("1_ Simple");
			System.out.println("2_ Doble");
			System.out.println("3_ Triple");
			System.out.println("4_ Cuadruple");
			System.out.println("5_ Sextuple");
			System.out.println("6_ Volver atras.");
			opc = scan.nextInt();
			switch (opc) {
			case 1:
				tipo = TipoHabitacion.SIMPLE;
				break;
	
			case 2:
				tipo = TipoHabitacion.DOBLE;
				break;
				
			case 3:
				tipo = TipoHabitacion.TRIPLE;
				break;
				
			case 4:
				tipo = TipoHabitacion.CUADRUPLE;
				break;
				
			case 5:
				tipo = TipoHabitacion.SEXTUPLE;
				break;
				
			default:
				System.out.println("Opcion Incorrecta");
				System.out.println("");
				Main.menuConserje();
				break;
			}
	
		return tipo;	
	}

	public static Reserva ingresoDeReserva() {
		Scanner scan = new Scanner(System.in);
		int anio = 0, mes = 0, dia = 0;
		System.out.println("Ingrese a�io, mes , dia en el cual desea ingresar al Hotel");
		anio = scan.nextInt();
		mes = scan.nextInt();
		dia = scan.nextInt();
		System.out.println("Ingrese a�o, mes, dia en el cual egresa del Hotel");
		int anioFin = scan.nextInt();
		int mesFin = scan.nextInt();
		int diaFin = scan.nextInt();
		Reserva reserva = new Reserva();
		
		try {
			reserva.comprobarFormatoFecha(anio, mes, dia);
			LocalDate ingreso = LocalDate.of(anio, mes, dia);
			reserva.comprobarFormatoFecha(anioFin, mesFin, diaFin);
			LocalDate fin = LocalDate.of(anioFin, mesFin, diaFin);
			
			if(ingreso.isBefore(fin)) {
				reserva.setFechaInicio(ingreso);
				reserva.setFechaFin(fin);
			} else {
				throw new fechaIncorrecta("La fecha de egreso es anterior que la de ingreso");
			}
			
		} catch (fechaIncorrecta e) {
			System.out.println(e.getMessage());
			System.out.println("");
			Main.ingresoDeReserva();
		} catch (RuntimeException e) {
			e.getMessage();
			e.printStackTrace();
		}
		scan.close();
		return reserva;
	}
}
