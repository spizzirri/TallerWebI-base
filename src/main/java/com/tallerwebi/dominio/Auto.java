package com.tallerwebi.dominio;

import com.tallerwebi.dominio.Modelo;
import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "car")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(length = 50, nullable = false)
    private String modelo;*/

    @OneToOne
//    @JoinColumn(name = "")
    private Modelo modelo;

    @Transient
    private String otroCampo; // No se guarda en la bd

//    public void setModelo(String modelo) {
//        this.modelo = modelo;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return Objects.equals(id, auto.id) && Objects.equals(modelo, auto.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo);
    }
}
