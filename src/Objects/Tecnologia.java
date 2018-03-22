package Objects;

public class Tecnologia {
    private int idTecnologia;
    private String nombre;

    public Tecnologia(int idTecnologia, String nombre) {
        this.idTecnologia = idTecnologia;
        this.nombre = nombre;
    }

    public Tecnologia() {
    }

    public int getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(int idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tecnologia{" +
                "idTecnologia=" + idTecnologia +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
