package Objects;

public class Proyecto {
    private int proyectoID;
    private String proyectoName;
    private String fecha_inicio;
    private String descripcion;

    public Proyecto() {
    }

    public Proyecto(int proyectoID, String proyectoName, String fecha_inicio, String descripcion) {
        this.proyectoID = proyectoID;
        this.proyectoName = proyectoName;
        this.fecha_inicio = fecha_inicio;
        this.descripcion = descripcion;
    }

    //GETTERS

    public int getProyectoID() {
        return proyectoID;
    }

    public String getProyectoName() {
        return proyectoName;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //SETTERS

    public void setProyectoID(int proyectoID) {
        this.proyectoID = proyectoID;
    }

    public void setProyectoName(String proyectoName) {
        this.proyectoName = proyectoName;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "proyectoID=" + proyectoID +
                ", proyectoName='" + proyectoName + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


}
