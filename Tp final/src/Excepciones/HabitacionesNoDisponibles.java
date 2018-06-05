package Excepciones;

public class HabitacionesNoDisponibles extends RuntimeException {

		public HabitacionesNoDisponibles(String mensaje) {
			super(mensaje);
		}
		
		public String getMenssage() {
			return super.getMessage();
		}
}
