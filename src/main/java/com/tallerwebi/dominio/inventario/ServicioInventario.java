package com.tallerwebi.dominio.inventario;

import com.tallerwebi.presentacion.DatosItem;

import java.util.List;

public interface ServicioInventario {

    List<DatosItem> get();
    List<DatosItem> getByTipoItem(TipoItem tipoItem);

}
