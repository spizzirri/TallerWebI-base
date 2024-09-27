package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<Sucursal> sucursal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
