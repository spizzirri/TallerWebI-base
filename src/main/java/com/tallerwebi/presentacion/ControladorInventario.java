package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.inventario.ServicioInventario;
import com.tallerwebi.dominio.inventario.TipoItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorInventario {

    private ServicioInventario servicioInventario;

    public ControladorInventario(ServicioInventario servicioInventario) {
        this.servicioInventario = servicioInventario;
    }

    @RequestMapping(path = "/inventario", method = RequestMethod.GET)
    public ModelAndView irAInventario() {
        String viewName = "inventario";
        ModelMap model = new ModelMap();
        model.put("message", "Bienvenido");
        model.put("items", this.servicioInventario.get());
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/buscar-item", method = RequestMethod.GET)
    public ModelAndView buscarItems(TipoItem tipoItem) {
        String viewName = "buscar-items";
        ModelMap model = new ModelMap();
        model.put("message", "Bienvenido");
        model.put("items", this.servicioInventario.getByTipoItem(tipoItem));
        return new ModelAndView(viewName, model);
    }
}
