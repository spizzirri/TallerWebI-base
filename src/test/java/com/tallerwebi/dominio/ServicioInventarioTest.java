package com.tallerwebi.dominio;

import com.tallerwebi.dominio.inventario.*;
import com.tallerwebi.presentacion.DatosItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class ServicioInventarioTest {

    private ServicioInventario servicioInventario;
    private RepositorioInventario repositorioInventario;

    @BeforeEach
    public void init(){
        this.repositorioInventario = mock(RepositorioInventario.class);
        this.servicioInventario = new ServicioInventarioImpl(this.repositorioInventario);
    }

    @Test
    public void queSePuedanObtenerTodosLosItems(){
        // preparacion
        List<Item> itemsMock = new ArrayList<>();
        itemsMock.add(new Item(1L, TipoItem.ESPADA));
        itemsMock.add(new Item(2L,TipoItem.ESCUDO));
        itemsMock.add(new Item(3L,TipoItem.ESCUDO));
        when(this.repositorioInventario.get()).thenReturn(itemsMock);

        // ejecucion
        List<DatosItem> items = this.servicioInventario.get();

        // verificacion
        assertThat(items.size(), equalTo(3)); // Existan 3 elementos
    }

    @Test
    public void queAlBuscarItemsPorTipoItemESPADADevuelvaLosItemsCorrespondientes(){
        // preparacion
        List<Item> itemsMock = new ArrayList<>();
        itemsMock.add(new Item(1L,TipoItem.ESPADA));
        when(this.repositorioInventario.getByTipoItem(TipoItem.ESPADA)).thenReturn(itemsMock);

        // ejecucion
        List<DatosItem> items = this.servicioInventario.getByTipoItem(TipoItem.ESPADA);

        // verificacion
        assertThat(items.size(), equalTo(1)); // Existan 1 elementos
    }
}
