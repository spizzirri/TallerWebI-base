package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.reto.Reto;
import com.tallerwebi.dominio.reto.ServicioReto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorReto {

    @Autowired
    private ServicioReto servicioReto;

    @Autowired
    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorReto(ServicioReto servicioReto, ServicioLogin servicioLogin) {this.servicioReto = servicioReto;
        this.servicioLogin = servicioLogin;
    }


    @RequestMapping(path = "/empezar-reto", method = RequestMethod.POST)
    public ModelAndView empezarReto(@RequestParam Long retoId, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("retoId", retoId); // Añadir retoId al modelo
        try {
            servicioReto.empezarRetoActualizado(retoId);

            // Añadir itemMasSeleccionado al modelo
            DatosItemRendimiento itemMasSeleccionado = servicioLogin.obtenerItemMasSeleccionado();
            modelAndView.addObject("itemMasSeleccionado", itemMasSeleccionado);

            // Añadir retoDisponible al modelo
            Reto retoDisponible = servicioReto.obtenerRetoPorId(retoId);
            modelAndView.addObject("retoDisponible", retoDisponible);

            // Añadir el usuario al modelo
            modelAndView.addObject("usuario", usuario);

        } catch (Exception e) {
            // Manejar el error mostrando un mensaje de error en la misma página
            modelAndView.addObject("error", "An error occurred while starting the challenge: " + e.getMessage());
        }
        return modelAndView;
    }



    @RequestMapping(path = "/terminar-reto", method = RequestMethod.POST)
    public ModelAndView terminarReto(@RequestParam Long retoId, @RequestParam String email, @RequestParam String password, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("retoId", retoId);
        try {
            Usuario usuarioBuscado = servicioLogin.consultarUsuario(email, password);
            if (usuarioBuscado != null) {
                servicioLogin.sumarRachaReto(usuarioBuscado);
                modelAndView.addObject("usuario", usuarioBuscado);
            }

            // Terminar el reto
            servicioReto.terminarReto(retoId);

            // Añadir información adicional al modelo si es necesario
            Reto retoDisponible = servicioReto.obtenerRetoDisponible();
            modelAndView.addObject("retoDisponible", retoDisponible);

            // Añadir itemMasSeleccionado al modelo
            DatosItemRendimiento itemMasSeleccionado = servicioLogin.obtenerItemMasSeleccionado();
            modelAndView.addObject("itemMasSeleccionado", itemMasSeleccionado);

        } catch (Exception e) {
            // Manejar el error mostrando un mensaje de error en la misma página
            modelAndView.addObject("error", "An error occurred while finishing the challenge: " + e.getMessage());
        }
        return modelAndView;
    }



}
