package com.tallerwebi.presentacion;

import com.tallerwebi.dto.ConversorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorDivisas {

    @RequestMapping(value = "/convertir")
    public ModelAndView convertir(){

        ConversorDTO conversor = new ConversorDTO(0.0, 0.0);
        ModelMap modelo = new ModelMap();
        modelo.put("conversor", conversor);

        return new ModelAndView("conversor", modelo);
    }

    @RequestMapping(value = "/resultado", method = RequestMethod.POST)
    public ModelAndView mostrarResultado(@ModelAttribute("conversor") ConversorDTO conversorDTO ){

        Double montoEnMonedaFinal = conversorDTO.convertir();

        ModelMap modelo = new ModelMap();
        modelo.put("montoEnMonedaFinal", montoEnMonedaFinal);

        return new ModelAndView("resultado", modelo);
    }
}
