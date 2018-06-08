package Interfaces;

import Clases.Reserva;

/**
 *
 * @author usuario
 */
public interface Administracion {
    public void CheckOut(Reserva reserva);
    public void CheckIn(Reserva reserva);
}