package com.tallerwebi.dominio.inventario;

import com.tallerwebi.presentacion.DatosItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioInventarioImpl implements ServicioInventario {

    private RepositorioInventario repositorioInventario;

    public ServicioInventarioImpl(RepositorioInventario repositorioInventario){
        this.repositorioInventario = repositorioInventario;
    }

    @Override
    public List<DatosItem> get() {
        return this.convertToDatosItem(this.repositorioInventario.get());
    }

    @Override
    public List<DatosItem> getByTipoItem(TipoItem tipoItem) {
        return this.convertToDatosItem(this.repositorioInventario.getByTipoItem(tipoItem));
    }

    private List<DatosItem> convertToDatosItem(List<Item> items) {
        List<DatosItem> datosItems = new ArrayList<>();

        for (Item item: items) {
            datosItems.add(new DatosItem(item));
        }

        return datosItems;
    }
}
