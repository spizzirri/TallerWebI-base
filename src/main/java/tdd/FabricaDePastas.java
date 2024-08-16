package tdd;

import java.util.ArrayList;
import java.util.List;

public class FabricaDePastas {

    private List<Pasta> pastasDisponibles;

    public FabricaDePastas(){
        this.pastasDisponibles = new ArrayList<>();
    }

    public Boolean agregar(Pasta pasta) {
        return this.pastasDisponibles.add(pasta);
    }

    public List<Pasta> obtener() {
        /*List<Pasta> pastas = new ArrayList<>();
        pastas.add(new Pasta());
        pastas.add(new Pasta());*/

        return this.pastasDisponibles;
    }

    public Pasta obtener(Long id) {
        Pasta pastaBuscada = null;
        for (Pasta pasta: this.pastasDisponibles) {
            if(pasta.getId().equals(id)){
                pastaBuscada = pasta;
            }
        }
        return pastaBuscada;
    }

    public Boolean modificar(Long id, String nuevoNombre) {
        Pasta pasta = this.obtener(id);
        pasta.setNombre(nuevoNombre);
        return true;
    }
}
