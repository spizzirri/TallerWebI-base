package com.tallerwebi.dominio.excepcion;

public class UsuarioSinRutinasException extends Exception{
    public UsuarioSinRutinasException() {
        super("El usuario no tiene rutinas asignadas.");
    }
}
