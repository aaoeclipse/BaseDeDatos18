package Objects;

public class Proyecto {
    private int proyectoID;
    private String proyectoName;
    private String fecha_inicio;

    public Proyecto() {
    }

    public Proyecto(int proyectoID, String proyectoName, String fecha_inicio) {
        this.proyectoID = proyectoID;
        this.proyectoName = proyectoName;
        this.fecha_inicio = fecha_inicio;
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

    @Override
    public String toString() {
        return "Proyecto{" +
                "proyectoID=" + proyectoID +
                ", proyectoName='" + proyectoName + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                '}';
    }


}
