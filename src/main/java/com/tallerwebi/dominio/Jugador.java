package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer numero;
    private Posiciones posicion;

    public Jugador(){}

    public Jugador(String nombre, Integer numero, Posiciones posicion) {
        this.numero = numero;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setPosicion(Posiciones posicion) {
        this.posicion = posicion;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id) && Objects.equals(nombre, jugador.nombre) && Objects.equals(numero, jugador.numero) && posicion == jugador.posicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numero, posicion);
    }
}
