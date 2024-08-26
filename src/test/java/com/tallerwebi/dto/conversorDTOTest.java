package com.tallerwebi.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class conversorDTOTest {

    @Test
    public void DebeDevoler1300CuandoConvierte1Peso(){
        //DADO
        ConversorDTO conversorDTO = new ConversorDTO(1.0, 0.0);

        //CUANDO
        Double monedaFinal = conversorDTO.convertir();

        //ENTONCES
        Double conversion_esperada = ConversorDTO.DOLAR * conversorDTO.getMonedaInicial();

        assertThat(monedaFinal, is(conversion_esperada));
        assertThat(conversorDTO.getMonedaFinal(), is(conversion_esperada));

    }
}
