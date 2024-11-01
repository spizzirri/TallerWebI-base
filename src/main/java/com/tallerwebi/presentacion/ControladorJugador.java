package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorJugador {
    private ServicioJugador servicioJugador;

    @Autowired
    public ControladorJugador(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
    }

    @RequestMapping(path = "/crear-jugador", method = RequestMethod.GET)
    public ModelAndView crearJugador() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("jugadorDto", new JugadorDto());
        return new ModelAndView("crear-jugador", modelMap);
    }

    @RequestMapping(path = "/crear-jugador", method = RequestMethod.POST)
    public ModelAndView guardarJugador(@ModelAttribute("jugadorDto") JugadorDto jugadorDto) {

        this.servicioJugador.guardar(jugadorDto.obtenerEntidad());
        ModelMap modelMap = new ModelMap();
        modelMap.put("mensaje", "Jugador creado!");
        modelMap.put("jugadorDto", new JugadorDto());
        return new ModelAndView("crear-jugador", modelMap);
    }

    @RequestMapping(path = "/ver-jugadores", method = RequestMethod.GET)
    public ModelAndView verJugadores() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("jugadoresDto", new ArrayList<>());
        modelMap.put("posicionDto", new PosicionDto());
        return new ModelAndView("ver-jugadores", modelMap);
    }

    @RequestMapping(path = "/ver-jugadores", method = RequestMethod.POST)
    public ModelAndView obtenerJugadores(@ModelAttribute("posicionDto") PosicionDto posicionDto) {

        List<JugadorDto> jugadoresDto = JugadorDto.obtenerEntidades(this.servicioJugador.obtenerJugadores(posicionDto.getPosicion()));
        ModelMap modelMap = new ModelMap();
        modelMap.put("jugadoresDto", jugadoresDto);
        return new ModelAndView("ver-jugadores", modelMap);
    }


}
