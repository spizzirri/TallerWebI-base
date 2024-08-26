package com.tallerwebi.presentacion;

import com.tallerwebi.dto.ConversorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorDivisasTest {

    @Test
    public void DebeRetornarLaVistaConversorYSolamenteElModeloConversorCuandoSeEjecutaElMetodoConvertir(){

        // DADO
        ControladorDivisas controladorDivisas = new ControladorDivisas();

        // CUANDO
        ModelAndView modelAndView = controladorDivisas.convertir();

        // ENTONCES
        ConversorDTO conversor_experado = new ConversorDTO(0.0, 0.0);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("conversor"));
        assertThat(modelAndView.getModel().get("conversor"), equalTo(conversor_experado));
        assertThat(modelAndView.getModel().size(), is(1));
    }

    @Test
    public void DebeRetornarLaVistaResutadoYSolamenteElValorConvertidoCuandoSeEjecutaElMetodoMostrarResultado(){

    }

    @Test
    public void DebeRetornarLaVistaErrorYElTextoOcurrioUnErrorCuandoSeRecibeUnValorNegativoParaConvertir(){
        // Hay que crear otra vista.
    }
}
