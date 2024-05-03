package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioItem {

    void guardar(Item item);

    List<Item> obtenerPorTipoItem(TipoItem tipoItem);

    void actualizar(Item item);
}
