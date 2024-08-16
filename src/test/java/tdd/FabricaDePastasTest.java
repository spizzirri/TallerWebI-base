package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class FabricaDePastasTest {
    public static final int CANTIDAD_DOS = 2;

    /*
        Fabrica de pastas que tiene disponibles distintas pastas.
     */

    private FabricaDePastas fabricaDePastas;

    @BeforeEach
    public void inicializacion(){
        this.fabricaDePastas = new FabricaDePastas();
    }

    @Test
    public void test() {
        // Preparación: Datos y/o elementos necesarios para ejecutar el test.

        // Ejecución: ejecutar el método correspondiente al test.

        // Verificación: Hamcrest

        // Escribimos un test y falla. Agregamos el codigo productivo NECESARIO para que el test pase (o corra). Refactor.
    }

    @Test
    public void dadoQueExisteUnaFabricaDePastasCuandoAgregoUnaPastaEntoncesMeDevuelveVerdadero(){

        // Preparacion
        Pasta pasta = new Pasta(1L);

        // Ejecucion
        Boolean pastaAgregada = this.fabricaDePastas.agregar(pasta);

        // Validacion -> Hamcrest
        // assertTrue(pastaAgregada), assertFalse(pastaAgregada), assertNotNull(pastaAgregada)

        // Que evaluamos, como lo comparamos
        assertThat(pastaAgregada, equalTo(true));
    }

    @Test
    public void dadoQueExisteUnaFabricaDePastasConDosPastasCuandoObtengoLasPastasDisponiblesEntoncesMeDevuelveUnaColeccionConEsasDosPastas(){

        // Preparacion
        Pasta pasta = new Pasta(1L);
        Pasta segundaPasta = new Pasta(2L);
        this.dadoQueExistenDosPastasEnLaFabricaDePastas(pasta, segundaPasta);

        // Ejecucion
        List<Pasta> pastasDisponibles = this.fabricaDePastas.obtener();

        // Validacion -> Hamcrest
        this.entoncesEnLaColeccionExistenEsasDosPastas(pastasDisponibles, pasta, segundaPasta);
    }

    @Test
    public void dadoQueExisteUnaFabricaDePastasConDosPastasCuandoObtengoUnaPastaExistentePorSuIdEntoncesObtengoLaPastaDeEseId(){
        // Preparacion
        Long id = 1L;
        Pasta pasta = new Pasta(id);
        this.fabricaDePastas.agregar(pasta);

        // Ejecucion
        Pasta pastaObtenida = this.fabricaDePastas.obtener(id);

        // Verificacion
        assertThat(pastaObtenida.getId(), equalTo(id));
    }

    @Test
    public void dadoQueExisteUnaFabricaDePastasConUnaPastaCuandoModificoSuNombreMeDevuelveVerdadero(){
        // preparacion
        Long id = 1L;
        String nuevoNombre = "Moñitos";
        Pasta pasta = new Pasta();
        pasta.setId(id);
        pasta.setNombre("Ravioles");
        this.fabricaDePastas.agregar(pasta);

        // ejecucion
        Boolean pastaModificada = this.fabricaDePastas.modificar(id, nuevoNombre);

        // Verificacion
        Pasta pastaObtenida = this.fabricaDePastas.obtener(id);
        assertThat(pastaObtenida.getNombre(), equalToIgnoringCase(nuevoNombre));
    }

    @Test
    public void dadoQueExisteUnaFabricaDePastasConDosPastasCuandoObtengoUnaPastaInexistentePorSuIdEntoncesObtengoNull(){

    }

    private void dadoQueExistenDosPastasEnLaFabricaDePastas(Pasta pasta, Pasta segundaPasta) {
        this.fabricaDePastas.agregar(pasta);
        this.fabricaDePastas.agregar(segundaPasta);
    }

    private void entoncesEnLaColeccionExistenEsasDosPastas(List<Pasta> pastasDisponibles, Pasta pasta, Pasta segundaPasta) {
        assertThat(pastasDisponibles.size(), equalTo(CANTIDAD_DOS));
        assertThat(pastasDisponibles.get(0).getId(), equalTo(pasta.getId()));
        assertThat(pastasDisponibles.get(1).getId(), equalTo(segundaPasta.getId()));
    }
}
