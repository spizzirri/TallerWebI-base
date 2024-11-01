package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioJugadorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class ServicioJugadorTest {

    private RepositorioJugador repositorioJugador;
    private ServicioJugador servicioJugador;

    @BeforeEach
    public void init(){
        this.repositorioJugador = mock(RepositorioJugador.class);
        this.servicioJugador = new ServicioJugadorImpl(this.repositorioJugador);
    }

    @Test
    public void dadoQueExisteUnServicioJugadorPuedoGuardarUnJugador(){
        Jugador jugador = new Jugador();
        jugador.setNombre("Matias");
        jugador.setNumero(2);
        jugador.setPosicion(Posiciones.DEFENSOR);

        this.servicioJugador.guardar(jugador);

        verify(this.repositorioJugador, times(1)).guardar(jugador);
    }

    @Test
    public void dadoQueExisteUnServicioJugadorCuandoObtengoJugadoresDefensoresObtengoUnaColeccionCon2JugadoresDefensores(){

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Matias", 2, Posiciones.DEFENSOR));
        jugadores.add(new Jugador("Juan", 6, Posiciones.DEFENSOR));

        when(this.repositorioJugador.obtenerJugadores(Posiciones.DEFENSOR)).thenReturn(jugadores);

        List<Jugador> jugadoresObtenidos = this.servicioJugador.obtenerJugadores(Posiciones.DEFENSOR);

        assertThat(jugadoresObtenidos.size(), equalTo(2));

    }
}
