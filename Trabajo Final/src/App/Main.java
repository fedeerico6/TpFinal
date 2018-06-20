package App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Clases.Administrativo;
import Clases.BaseDeDatos;
import Clases.Cliente;
import Clases.Conserje;
import Clases.Empleado;
import Clases.Habitacion;
import Clases.Habitacion.Disponibilidad;
import Clases.Habitacion.TipoHabitacion;
import Clases.Hotel;
import Clases.Reserva;
import Excepciones.FaltaCheckIn;
import Excepciones.HabitacionesNoDisponibles;
import Excepciones.fechaIncorrecta;
import Excepciones.paswordIncorrecto;
import Excepciones.usuarioIncorrecto;



public class Main {

    public static Hotel hotel = new Hotel("420", "Amnsterdan", "xxx420");

    public static void main(String[] args) {

        
        
        //cargarMapas();
        recuperarDatosArchivo(); // NO COMENTAR ESTA LINEA PORQUE ES LA QUE RECUPERA LOS DATOS DEL ARCHIVO
        menuIngreso();
        hotel.gaurdarMapasEnArchivos();
        
        
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
                        if (empleado instanceof Conserje) {
                            System.out.println("Bienvenido " + empleado.getNombreCompleto() + " Ingrese una opcion para continuar");
                            menuConserje(empleado);
                        } else {
                            System.out.println("Bienvenido " + empleado.getNombreCompleto() + " Ingrese una opcion para continuar");
                            menuAdministrativo();
                        }
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
                	
                	ArrayList<TipoHabitacion> tipos = menuTipoHabitacion(); // te devuelve el tipo de habitacion que quiere el cliente
                    Reserva reserva = ingresoDeReserva(); // te devuelve la reserva con la fecha de inicio y fin
                    for(TipoHabitacion tipo: tipos) {
                    	Habitacion habitacion = hotel.reservar(reserva, tipo);
                    	if (habitacion != null) {
                            reserva.setHabitacion(habitacion.getNumeroHabitacion());
                            habitacion.setReserva(reserva); //guarda en el array de reservas que tiene la habitacion
                            
                        }
                    }
                    
                    reserva = Main.fijarReserva(reserva); //pide los campos faltantes para terminar la reserva
                    System.out.println(reserva.toString());
                    hotel.setReservaActiva(reserva); //guarda en el mapa la nueva reserva;
                    System.out.println("Ya se registro la reserva! ");
                    System.out.println("");
                    //te devuelve la habitacion que esta disponible para esa fecha

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

    public static void menuConserje(Empleado empleado) {
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
                	try {
                		ArrayList<TipoHabitacion> tipos = menuTipoHabitacion(); // te devuelve el tipo de habitacion que quiere el cliente
                        Reserva reserva = ingresoDeReserva(); // te devuelve la reserva con la fecha de inicio y fin
                        for(TipoHabitacion tipo: tipos) {
                        	Habitacion habitacion = hotel.reservar(reserva, tipo);
                        	if (habitacion != null) {
                                reserva.setHabitacion(habitacion.getNumeroHabitacion());
                                habitacion.setReserva(reserva); //guarda en el array de reservas que tiene la habitacion
                                
                            }
                        }
                        
                        reserva = Main.fijarReserva(reserva); //pide los campos faltantes para terminar la reserva
                        System.out.println(reserva.toString());
                        hotel.setReservaActiva(reserva); //guarda en el mapa la nueva reserva;
                        System.out.println("Ya se registro la reserva! ");
                        System.out.println("");
                        //te devuelve la habitacion que esta disponible para esa fecha
                	}catch (HabitacionesNoDisponibles e) {
						// TODO: handle exception
                		System.out.println(e.getMenssage());
					}
                    

                    break;

                case 2:
                    System.out.println("Por favor ingrese el codigo de reserva");
                    String code = scan.next();
                    Reserva reserva2 = hotel.getReserva(code);
                    empleado.CheckIn(reserva2);
                    break;

                case 3:
                	System.out.println("Por favor ingrese el codigo de reserva");
                    String code2 = scan.next();
                    Reserva reserva22 = hotel.getReserva(code2);
                    try {
                    	empleado.CheckOut(reserva22);
                    	hotel.setReservaAntiguas(reserva22);
                    	hotel.borrarReservaActiva(reserva22);
                    } catch (FaltaCheckIn e) {
						// TODO: handle exception
                    	System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("-4-");
                    break;
                default:

                    System.out.println("Opcion Incorrecta");
                    System.out.println("");

                    break;
            }
        }
    }

    public static ArrayList<TipoHabitacion> menuTipoHabitacion() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Tipo de habitacion que desea reservar");
        int opc = 0;
        TipoHabitacion tipo = null;
        Scanner sc = new Scanner(System.in);
        String bandera = "si";
        ArrayList<TipoHabitacion> tipos = new ArrayList<TipoHabitacion>();
        while(bandera.equals("si")){
        	
        	System.out.println("1_ Simple");
            System.out.println("2_ Doble");
            System.out.println("3_ Triple");
            System.out.println("4_ Cuadruple");
            System.out.println("5_ Sextuple");
            opc = scan.nextInt();
            switch (opc) {
            	case 1:
                    tipo = TipoHabitacion.SIMPLE;
                    tipos.add(tipo);
                    break;

                case 2:
                    tipo = TipoHabitacion.DOBLE;
                    tipos.add(tipo);
                    break;

                case 3:
                    tipo = TipoHabitacion.TRIPLE;
                    tipos.add(tipo);
                    break;

                case 4:
                    tipo = TipoHabitacion.CUADRUPLE;
                    tipos.add(tipo);
                    break;

                case 5:
                    tipo = TipoHabitacion.SEXTUPLE;
                    tipos.add(tipo);
                    break;
       
                default:
                    System.out.println("Opcion Incorrecta");
                    System.out.println("");
                    menuTipoHabitacion();
                   // Main.menuConserje(new Empleado());
                    break;
            }
            System.out.println("");
            System.out.println("Desea reservas otra habitacion? si/no");
            bandera = sc.nextLine();
        }
        		
        
        
        return tipos;
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

            if (ingreso.isBefore(fin)) {
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

        return reserva;
    }

    public static Reserva fijarReserva(Reserva reserva) {
        Scanner scan3 = new Scanner(System.in);

        System.out.println("Ingrese DNI.");
        String dni = scan3.nextLine();
        reserva.setDni(dni);

        hotel.fijarCodigoReserva(reserva);
        System.out.println("Su reserva se ha realizado con exito, su codigo de reserva es : " + reserva.getCodigoReserva());

        return reserva;
    }

    public static void cargarMapaHabitaciones() {
        int i = 0;
        for (i = 0; i < 10; i++) {
            Habitacion habitacion = new Habitacion(TipoHabitacion.SIMPLE, 200, i+1);
            hotel.setHabitaciones(habitacion);
        }
        for (int a = 0; a < 10; a++) {
            i++;
            Habitacion habitacion = new Habitacion(TipoHabitacion.DOBLE, 200, i+1);
            hotel.setHabitaciones(habitacion);
        }
        for (int a = 0; a < 10; a++) {
            i++;
            Habitacion habitacion = new Habitacion(TipoHabitacion.TRIPLE, 200, i+1);
            hotel.setHabitaciones(habitacion);
        }
        for (int a = 0; a < 10; a++) {
            i++;
            Habitacion habitacion = new Habitacion(TipoHabitacion.CUADRUPLE, 200, i+1);
            hotel.setHabitaciones(habitacion);
        }
        for (int a = 0; a < 10; a++) {
            i++;
            Habitacion habitacion = new Habitacion(TipoHabitacion.SEXTUPLE, 200, i+1);
            hotel.setHabitaciones(habitacion);
        }
        BaseDeDatos<Integer, Habitacion> archivoHabitaciones = new BaseDeDatos<>("habitaciones.dat");
        archivoHabitaciones.escribirArchivoV2(hotel.getHabitaciones());
    }

    public static void cargarMapaReservasActivas() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        Reserva reserva = new Reserva("40794525", LocalDate.of(2018, 8, 8), LocalDate.of(2018, 8, 12));
        Reserva reserva1 = new Reserva("4033333", LocalDate.of(2018, 9, 8), LocalDate.of(2018, 9, 12));
        hotel.setReservaActiva(reserva);
        hotel.setReservaActiva(reserva1);
        
        BaseDeDatos<String, Reserva> archivoReservaActiva = new BaseDeDatos<String, Reserva>("reservasActivas.dat");
        archivoReservaActiva.escribirArchivoV2(hotel.getReservaActivas());
    }

    public static void cargarMapaReservasAntiguas() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        Reserva reserva = new Reserva("40794525", LocalDate.of(2018, 1, 8), LocalDate.of(2018, 1, 12));
        Reserva reserva1 = new Reserva("4033333", LocalDate.of(2018, 4, 8), LocalDate.of(2018, 4, 12));
        hotel.setReservaActiva(reserva);
        hotel.setReservaActiva(reserva1);
        BaseDeDatos<String, Reserva> archivoReservaAntigua = new BaseDeDatos<String, Reserva>("reservasAntiguas.dat");
        archivoReservaAntigua.escribirArchivoV2(hotel.getReservasAntiguas());
    }

    public static void cargarMapaCliente() {
        Cliente cliente = new Cliente("mdp", "Edgar", "111111", "viila gesel");
        hotel.setCliente(cliente);
        BaseDeDatos<String, Cliente> archvioClientes = new BaseDeDatos<String, Cliente>("clientes.dat");
        archvioClientes.escribirArchivoV2(hotel.getClientes());
    }

    public static void cargarMapaEmpleado() {
        Empleado empleado = new Conserje("Federico Elias", "40794525", "garay", "admin", "admin");
        Empleado empleado2 = new Administrativo("Edgar Rosales", "20178978", "nose", "edgar", "edgar");
        hotel.setEmpleado(empleado);
        hotel.setEmpleado(empleado2);
        BaseDeDatos<String, Empleado> archivoEmpleado = new BaseDeDatos<String, Empleado>("empleados.dat");
        archivoEmpleado.escribirArchivoV2(hotel.getEmpleados());
    }

    public static void cargarMapas() {
        cargarMapaHabitaciones();
        cargarMapaReservasActivas();
        cargarMapaReservasAntiguas();
        cargarMapaCliente();
        cargarMapaEmpleado();
    }

    public static void recuperarDatosArchivo() {
       /* hotel.recuerarClienteBaseDeDatos();
        hotel.recuperarEmpleadoBaseDeDatos();
        hotel.recuperarHabitacionesBaseDeDatos();
        hotel.recuperarReservasActivasBaseDeDatos();
        hotel.recuperarReservasAntiguasBaseDeDatos();*/
    	
    	
    	hotel.recuerarClienteBaseDeDatosV2();
        hotel.recuperarEmpleadoBaseDeDatosV2();
        hotel.recuperarHabitacionesBaseDeDatosV2();
        hotel.recuperarReservasActivasBaseDeDatosV2();
        hotel.recuperarReservasAntiguasBaseDeDatosV2();
    
    }
   
	public static void menuAdministrativo() {
	        int opc = 0;
	        Scanner scan = new Scanner(System.in);
	        while (opc != 12) {
	            System.out.println("1_ Crear nuevo administrador");
	            System.out.println("2_ Crear nuevo conserje");
	            System.out.println("3_ Crear nuevo cliente");
	            System.out.println("4_ Realizar backup de la informacion");
	            System.out.println("5_ Listar hotel");
	            System.out.println("6_ Listar habitaciones");
	            System.out.println("7_ Listar reservas activas");
	            System.out.println("8_ Listar reservas antiguas");
	            System.out.println("9_ Listar clientes");
	            System.out.println("10_ Listar empleados");
	            System.out.println("11_ Modificar disponibilidad habitacion. (limpieza,reaparacion,etc)");
	            System.out.println("12_ Volver atras");
	            opc = scan.nextInt();
	
	            switch (opc) {
	                case 1:
	                    
	                    String nombreCompleto,
	                     dni,
	                     domicilio,
	                     usuario,
	                     password;
	                    
	                    scan.nextLine();
	                    System.out.println("Ingrese el nombre completo del nuevo administrador");
	                    nombreCompleto = scan.nextLine();
	                    System.out.println("Ingrese el DNI del nuevo administrador");
	                    dni = scan.nextLine();
	                    System.out.println("Ingrese el domicilio del nuevo administrador");
	                    domicilio = scan.nextLine();
	                    System.out.println("Ingrese el usuario del nuevo administrador");
	                    usuario = scan.nextLine();
	                    System.out.println("Ingrese la contraseña del nuevo administrador");
	                    password = scan.nextLine();
	
	                    Empleado empleado = new Administrativo(nombreCompleto, dni, domicilio, usuario, password);
	                    hotel.setEmpleado(empleado);
	                    
	                    break;
	
	                case 2:
	                    
	                    String nombreCompleto2,
	                    dni2,
	                    domicilio2,
	                    usuario2,
	                    password2;
	                    
	                    scan.nextLine();
	                    System.out.println("Ingrese el nombre completo del nuevo conserje");
	                    nombreCompleto2 = scan.nextLine();
	                    System.out.println("Ingrese el DNI del nuevo conserje");
	                    dni2 = scan.nextLine();
	                    System.out.println("Ingrese el domicilio del nuevo conserje");
	                    domicilio2 = scan.nextLine();
	                    System.out.println("Ingrese el usuario del nuevo conserje");
	                    usuario2 = scan.nextLine();
	                    System.out.println("Ingrese la contraseña del nuevo conserje");
	                    password2 = scan.nextLine();
	
	                    Empleado empleado2 = new Conserje(nombreCompleto2, dni2, domicilio2, usuario2, password2);
	                    hotel.setEmpleado(empleado2);
	
	                    break;
	
	                case 3:
	                	String origen = new String();
	                	String nombreCompleto3 = new String();
	                	String dni3 = new String();
	                	String domicilio3 = new String();;
	                	scan.nextLine();
	                	System.out.println("Ingrese el nombre completo del nuevo cliente");
	                	scan = scan.reset();
	                	nombreCompleto3 = scan.nextLine();
	                	System.out.println("Ingrese el dni: ");
	                	scan = scan.reset();
	                	dni3 = scan.nextLine();
	                	System.out.println("Ingrese el domicilio: ");
	                	scan = scan.reset();
	                	domicilio3 = scan.nextLine();
	                	System.out.println("Ingrese el origen");
	                	scan = scan.reset();
	                	origen = scan.nextLine();
	                	
	                	Cliente cliente = new Cliente(origen, nombreCompleto3, dni3, domicilio3);
	                	hotel.setCliente(cliente);
	                	
	                    break;
	
	                case 4:
	                	hotel.gaurdarMapasEnArchivos();
	                    break;
	                    
	                case 5:
	                	System.out.println("Clientes: ");
	                	System.out.println("");
	                	hotel.listarClientes();
	                	System.out.println("--------");
	                	System.out.println("");
	                	
	                	System.out.println("Empleados: ");
	                	System.out.println("");
	                	hotel.listarEmpleados();
	                	System.out.println("--------");
	                	System.out.println("");
	                	
	                	System.out.println("Habitaciones: ");
	                	System.out.println("");
	                	hotel.listarHabitaciones();
	                	System.out.println("--------");
	                	System.out.println("");
	                	
	                	System.out.println("Reservas activas: ");
	                	System.out.println("");
	                	hotel.listarReservasActivas();
	                	System.out.println("--------");
	                	System.out.println("");
	                	
	                	System.out.println("Reservas antiguas: ");
	                	System.out.println("");
	                	hotel.listarReservasAntiguas();
	                	System.out.println("--------");
	                	System.out.println("");
	                	
	                	break;
	                	
	                case 6:
	                	System.out.println("Habitaciones: ");
	                	System.out.println("");
	                	hotel.listarHabitaciones();
	                	System.out.println("--------");
	                	System.out.println("");
	                	break;
	                	
	                case 7:
	                	System.out.println("Reservas activas: ");
	                	System.out.println("");
	                	hotel.listarReservasActivas();
	                	System.out.println("--------");
	                	System.out.println("");
	                	break;
	                	
	                case 8:
	                	System.out.println("Reservas antiguas: ");
	                	System.out.println("");
	                	hotel.listarReservasAntiguas();
	                	System.out.println("--------");
	                	System.out.println("");
	                	break;
	                	
	                case 9:
	                	System.out.println("Clientes: ");
	                	System.out.println("");
	                	hotel.listarClientes();
	                	System.out.println("--------");
	                	System.out.println("");
	                	break;
	                	
	                case 10:
	                	System.out.println("Empleados: ");
	                	System.out.println("");
	                	hotel.listarEmpleados();
	                	System.out.println("--------");
	                	System.out.println("");
	                	break;
	                	
	                case 11:
	                	Scanner sc = new Scanner(System.in);
	                	System.out.println("Ingrese el numero de la habitacion que desea modificar");
	                	int rta = sc.nextInt();
	                	Habitacion room = hotel.GetHabitacion(rta);
	                	if(room != null) {
	                		if(!room.getOcupado()) {
	                			Disponibilidad dis = menuDisponibilidadHabitacion();
		                		room.setDisponible(dis);
	                		}else {
	                			System.out.println();
	                		}
	                		
	                	}
	                	break;
	                default:
	
	                    System.out.println("Opcion Incorrecta");
	                    System.out.println("");
	
	                    break;
	            }
	        }
	    }
	
	public static Disponibilidad menuDisponibilidadHabitacion() {
		Scanner scan = new Scanner(System.in);
		Disponibilidad dis = Disponibilidad.DISPONIBLE;
		System.out.println("1_ Cambiar a disponible");
		System.out.println("2_ Cambiar a limpieza");
		System.out.println("3_ Cambiar a desinfeccion");
		System.out.println("4_ Cambiar a reparacion");
		int opc = scan.nextInt();
		switch (opc) {
		case 1:
			dis = Disponibilidad.DISPONIBLE;
			break;
		case 2:
			dis = Disponibilidad.LIMPIEZA;
			break;
		case 3:
			dis = Disponibilidad.DESINFECCION;
			break;
		case 4:
			dis = Disponibilidad.REPARACION;
			break;
		default:
			break;
		}
		return dis;
	}
	
	}
	
	
