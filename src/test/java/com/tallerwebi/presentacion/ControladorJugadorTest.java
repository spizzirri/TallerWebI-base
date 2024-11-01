package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Posiciones;
import com.tallerwebi.dominio.ServicioJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ControladorJugadorTest {

    private ControladorJugador controladorJugador;
    private ServicioJugador servicioJugador;

    @BeforeEach
    public void init(){
        this.servicioJugador = mock(ServicioJugador.class);
        this.controladorJugador = new ControladorJugador(this.servicioJugador);
    }

    @Test
    public void dadoQUeExisteUnControladorJugadorCuandoQuieroCrearUnJugadorMeMuestraLaVistaCrearJugadorConUnJugadorDtoVacio(){

        ModelAndView mav = this.controladorJugador.crearJugador();

        assertThat("crear-jugador", equalTo(mav.getViewName()));
        JugadorDto jugadorEnModelo = (JugadorDto) mav.getModel().get("jugadorDto");
        assertThat(jugadorEnModelo.getNombre(), equalTo(""));
        assertThat(jugadorEnModelo.getNumero(), equalTo(null));
        assertThat(jugadorEnModelo.getPosicion(), equalTo(Posiciones.DEFENSOR));
    }

    @Test
    public void dadoQueExisteUnControladorJugadorCuandoQuieroGuardarUnJugadorMeMuestraLaVistaCrearJugadorConUnMensajeDeExito(){
        JugadorDto jugadorDto = new JugadorDto();
        jugadorDto.setNombre("Matias");
        jugadorDto.setNumero(2);
        jugadorDto.setPosicion(Posiciones.DEFENSOR);

        ModelAndView mav = this.controladorJugador.guardarJugador(jugadorDto);

        JugadorDto jugadorEnModelo = (JugadorDto) mav.getModel().get("jugadorDto");
        verify(this.servicioJugador, times(1)).guardar(jugadorDto.obtenerEntidad());
        assertThat("crear-jugador", equalTo(mav.getViewName()));
        assertThat("Jugador creado!", equalTo(mav.getModel().get("mensaje")));
        assertThat(jugadorEnModelo.getNombre(), equalTo(""));
        assertThat(jugadorEnModelo.getNumero(), equalTo(null));
        assertThat(jugadorEnModelo.getPosicion(), equalTo(Posiciones.DEFENSOR));
    }

    @Test
    public void dadoQueExisteUnControladorJugadorCuandoQuieroVerJugadoresPorPosicionObtengoLaVistaVerJugadores(){
        ModelAndView mav =this.controladorJugador.verJugadores();

        List<JugadorDto> jugadoresDto = (List<JugadorDto>)mav.getModel().get("jugadoresDto");
        PosicionDto posicionDto = (PosicionDto) mav.getModel().get("posicionDto");
        assertThat(mav.getViewName(), equalTo("ver-jugadores"));
        assertThat(jugadoresDto.size(), equalTo(0));
        assertThat(posicionDto, notNullValue());
    }

    @Test
    public void dadoQueExisteUnControladorJugadorConJugadoresEnDistintasPosicionesCuandoQuieroVerJugadoresDefensoresMeMuestraLaVistaObtenerJugadoresConJugadoresDefensores(){

        PosicionDto posicionDto = new PosicionDto();
        posicionDto.setPosicion(Posiciones.DEFENSOR);

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Matias", 2, Posiciones.DEFENSOR));
        jugadores.add(new Jugador("Juan", 6, Posiciones.DEFENSOR));

        when(this.servicioJugador.obtenerJugadores(Posiciones.DEFENSOR)).thenReturn(jugadores);

        ModelAndView mav = this.controladorJugador.obtenerJugadores(posicionDto);

        List<JugadorDto> jugadoresDto = (List<JugadorDto>)mav.getModel().get("jugadoresDto");
        assertThat("ver-jugadores", equalTo(mav.getViewName()));
        assertThat(jugadoresDto.size(), equalTo(2));
        assertThat(jugadoresDto.get(0).getPosicion(), equalTo(Posiciones.DEFENSOR));
        assertThat(jugadoresDto.get(1).getPosicion(), equalTo(Posiciones.DEFENSOR));
    }
}
