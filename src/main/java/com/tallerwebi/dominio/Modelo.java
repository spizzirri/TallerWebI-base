package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
