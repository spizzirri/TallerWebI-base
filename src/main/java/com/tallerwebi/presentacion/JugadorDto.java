package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Posiciones;

import java.util.ArrayList;
import java.util.List;

public class JugadorDto {
    private String nombre;
    private Integer numero;
    private Posiciones posicion;

    public JugadorDto(){
        this.numero = null;
        this.nombre = "";
        this.posicion = Posiciones.DEFENSOR;
    }

    public JugadorDto(Jugador jugador) {
        this.nombre = jugador.getNombre();
        this.numero = jugador.getNumero();
        this.posicion = jugador.getPosicion();
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setPosicion(Posiciones posicion) {
        this.posicion= posicion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public Posiciones getPosicion() {
        return this.posicion;
    }

    public Jugador obtenerEntidad() {
        return new Jugador(this.nombre, this.numero, this.posicion);
    }

    public static List<JugadorDto> obtenerEntidades(List<Jugador> jugadores) {
        List<JugadorDto> jugadoresDto = new ArrayList<>();
        for(Jugador jugador : jugadores){
            jugadoresDto.add(new JugadorDto(jugador));
        }
        return jugadoresDto;
    }
}
