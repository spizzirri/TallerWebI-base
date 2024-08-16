package tdd;

public class Pasta {
    private Long id;
    private String nombre;

    public Pasta() {
    }

    public Pasta(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
