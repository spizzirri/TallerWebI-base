package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.JugadorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioJugadorImpl implements ServicioJugador {

    private final RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public void guardar(Jugador jugador) {
        this.repositorioJugador.guardar(jugador);
    }

    @Override
    public List<Jugador> obtenerJugadores(Posiciones posicion) {
        return this.repositorioJugador.obtenerJugadores(posicion);
    }
}
