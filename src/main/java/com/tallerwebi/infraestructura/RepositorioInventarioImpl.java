package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.inventario.Item;
import com.tallerwebi.dominio.inventario.RepositorioInventario;
import com.tallerwebi.dominio.inventario.TipoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioInventarioImpl implements RepositorioInventario {

    private List<Item> items;

    public RepositorioInventarioImpl(){
        this.items = new ArrayList<>();
        items.add(new Item(1L,TipoItem.ESPADA));
        items.add(new Item(2L,TipoItem.ESCUDO));
        items.add(new Item(3L,TipoItem.ESCUDO));
    }

    @Override
    public List<Item> get() {
        return this.items;
    }

    @Override
    public List<Item> getByTipoItem(TipoItem tipoItem) {
        List<Item> itemsFiltrados = new ArrayList<>();

        for (Item item: this.items) {
            if(item.getTipoItem().equals(tipoItem)){
                itemsFiltrados.add(item);
            }
        }
        return itemsFiltrados;
    }
}
