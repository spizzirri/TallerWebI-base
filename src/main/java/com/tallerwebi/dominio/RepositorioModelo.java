package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioModelo {
    void guardar(Modelo modelo);

    List<Modelo> obtener();

    void actualizar(Modelo modelo);
}
