package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.objetivo.Objetivo;
import com.tallerwebi.dominio.rutina.Rutina;
import com.tallerwebi.dominio.rutina.ServicioRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorRutina {


    private final ServicioRutina servicioRutina;

    @Autowired
    public ControladorRutina(ServicioRutina servicioRutina) {
        this.servicioRutina = servicioRutina;
    }

    @RequestMapping(path = "/rutinas", method = RequestMethod.GET)
    public ModelAndView VerRutinasQueLeInteresanAlUsuario(@RequestParam("objetivo") String objetivo) {
        ModelAndView modelAndView = new ModelAndView("rutinas");

        Objetivo objetivoEnum;
        try {
            objetivoEnum = Objetivo.valueOf(objetivo);
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Objetivo no válido");
            return modelAndView;
        }

        List<DatosRutina> rutinas = this.servicioRutina.getRutinasPorObjetivo(objetivoEnum);
        modelAndView.addObject("rutinas", rutinas);

        return modelAndView;
    }

    @RequestMapping(path = "/mi-rutina", method = RequestMethod.GET)
    public ModelAndView irAMiRutina(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return new ModelAndView("redirect:/login");
        }

        if (usuario.getObjetivo() == null) {
            return new ModelAndView("redirect:/vistaObjetivos");
        }

        try {
            DatosRutina rutina = servicioRutina.getRutinaActualDelUsuario(usuario);
            modelAndView.addObject("rutina", rutina);
            modelAndView.setViewName("rutina");
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/rutinas?objetivo=" + usuario.getObjetivo().toString());
        }

        return modelAndView;
    }

    @RequestMapping(path = "/asignar-rutina", method = RequestMethod.GET)
    public ModelAndView asignarRutina(@RequestParam("id") Long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            // Obtener el usuario de la sesión
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            // Obtener la rutina por su ID
            Rutina rutina = servicioRutina.getRutinaById(id);
            DatosRutina datosRutina = servicioRutina.getDatosRutinaById(id);

            // Verificar si el usuario y la rutina existen
            if (usuario != null && rutina != null) {
                // Asignar la rutina al usuario
                servicioRutina.asignarRutinaAUsuario(rutina, usuario);

                // Agregar la rutina al modelo
                modelAndView.addObject("rutina", datosRutina);
                modelAndView.setViewName("rutina");
            } else {
                // Si el usuario o la rutina no existen, redirigir a la página de rutinas
                modelAndView.setViewName("redirect:/vistaObjetivos");
            }
        } catch (Exception e) {
            // Si ocurre una excepción, redirigir a la página de rutinas
            modelAndView.setViewName("redirect:/vistaObjetivos");
        }

        return modelAndView;
    }




}



