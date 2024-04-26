package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.inventario.Item;
import com.tallerwebi.dominio.inventario.TipoItem;

public class DatosItem {
    private TipoItem tipoItem;
    private String descripcion;

    public DatosItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

    public DatosItem(Item item){
        this.tipoItem = item.getTipoItem();
        this.descripcion = "Item de tipo " + this.tipoItem;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }
}
