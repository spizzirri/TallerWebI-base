package com.tallerwebi.dto;

import java.util.Objects;

public class ConversorDTO {

    public ConversorDTO() {
    }

    public ConversorDTO(Double monedaInicial, Double monedaFinal) {
        this.monedaInicial = monedaInicial;
        this.monedaFinal = monedaFinal;
    }

    private Double monedaInicial;
    private Double monedaFinal;

    public static final Double DOLAR = 1350.0;

    public Double getMonedaInicial() {
        return monedaInicial;
    }

    public void setMonedaInicial(Double monedaInicial) {
        this.monedaInicial = monedaInicial;
    }

    public Double getMonedaFinal() {
        return monedaFinal;
    }

    public void setMonedaFinal(Double monedaFinal) {
        this.monedaFinal = monedaFinal;
    }

    public Double convertir(){
        this.monedaFinal = monedaInicial * DOLAR;
        return this.monedaFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversorDTO that = (ConversorDTO) o;
        return Objects.equals(monedaInicial, that.monedaInicial) && Objects.equals(monedaFinal, that.monedaFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monedaInicial, monedaFinal);
    }
}
