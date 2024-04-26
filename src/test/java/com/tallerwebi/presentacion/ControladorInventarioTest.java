package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.inventario.ServicioInventario;
import com.tallerwebi.dominio.inventario.TipoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorInventarioTest {
    private ControladorInventario controladorInventario;
    private ServicioInventario servicioInventario;

    @BeforeEach
    public void init(){
        this.servicioInventario = mock(ServicioInventario.class);
        this.controladorInventario = new ControladorInventario(this.servicioInventario);
    }

    @Test
    public void queAlSolicitarLaPantallaDeInventarioSeMuestreLaVistaInventario(){
        // preparacion

        // ejecucion
        ModelAndView mav = this.controladorInventario.irAInventario();

        String message = mav.getModel().get("message").toString();

        // verificacion
        assertThat(mav.getViewName(), equalToIgnoringCase("inventario")); // Vista correcta
        assertThat(message, equalToIgnoringCase("Bienvenido")); // Exista un mensaje de bienvenida
    }

    @Test
    public void queAlIngresarALaPantallaDeInventarioMeMuestreTodosLosItemsExistentes(){
        // preparacion
        List<DatosItem> itemsMock = new ArrayList<>();
        itemsMock.add(new DatosItem(TipoItem.ESPADA));
        itemsMock.add(new DatosItem(TipoItem.ESCUDO));
        itemsMock.add(new DatosItem(TipoItem.ESCUDO));

        when(this.servicioInventario.get()).thenReturn(itemsMock);

        // ejecucion
        ModelAndView mav = this.controladorInventario.irAInventario();

        // verificacion
        List<DatosItem> items = (List<DatosItem>) mav.getModel().get("items");
        assertThat(items.size(), equalTo(3)); // Existan 3 elementos
    }

    @Test
    public void queAlBuscarItemsDeTipoEspadaObtengaItemsDeEseTipo(){
        // preparacion
        List<DatosItem> itemsMock = new ArrayList<>();
        itemsMock.add(new DatosItem(TipoItem.ESPADA));
        when(this.servicioInventario.getByTipoItem(TipoItem.ESPADA)).thenReturn(itemsMock);

        // ejecucion
        ModelAndView mav = this.controladorInventario.buscarItems(TipoItem.ESPADA);

        // verificacion
        List<DatosItem> items = (List<DatosItem>) mav.getModel().get("items");
        assertThat(mav.getViewName(), equalToIgnoringCase("buscar-items")); // Vista correcta
        assertThat(items.size(), equalTo(1)); // Existan 1 elementos
        assertThat(items.get(0).getTipoItem(), equalTo(TipoItem.ESPADA)); // Existan 1 elementos
    }
}
