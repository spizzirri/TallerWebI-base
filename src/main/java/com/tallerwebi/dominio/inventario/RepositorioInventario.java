package com.tallerwebi.dominio.inventario;

import java.util.List;
public interface RepositorioInventario {
    List<Item> get();
    List<Item> getByTipoItem(TipoItem tipoItem);
}
