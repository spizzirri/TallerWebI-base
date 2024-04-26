package com.tallerwebi.dominio.inventario;

public class Item {

    private Long id;
    private TipoItem tipoItem;

    public Item(Long id, TipoItem tipoItem) {
        this.id = id;
        this.tipoItem = tipoItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }
}
