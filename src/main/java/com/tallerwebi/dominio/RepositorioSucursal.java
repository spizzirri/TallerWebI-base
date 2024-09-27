package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioSucursal {

    List<Sucursal> obtener();
    Sucursal obtener(Long id);
    void guardar(Sucursal sucursal);
    List<Sucursal> buscarSucursalesPorCiudad(String sucursal, String nombre_ciudad);
}
