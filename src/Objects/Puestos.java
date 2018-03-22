package Objects;

public class Puestos {
    private int idPuesto;
    private String nombre;

    public Puestos(int idPuesto, String nombre) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
    }

    public Puestos(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Puestos{" +
                "idPuesto=" + idPuesto +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
