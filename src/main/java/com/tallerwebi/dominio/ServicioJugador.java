package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.JugadorDto;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ServicioJugador {
    void guardar(Jugador jugador);
    List<Jugador> obtenerJugadores(Posiciones posicion);
}
