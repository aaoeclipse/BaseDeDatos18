package Objects;

public class ColumnasExtras {
    private int idColumna;
    private String nombre;
    private float value;

    public ColumnasExtras(int idColumna, String nombre, float value) {
        this.idColumna = idColumna;
        this.nombre = nombre;
        this.value = value;
    }

    public ColumnasExtras(int idColumna) {
        this.idColumna = idColumna;
    }

    public int getIdColumna() {
        return idColumna;
    }

    public void setIdColumna(int idColumna) {
        this.idColumna = idColumna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ColumnasExtras{" +
                "idColumna=" + idColumna +
                ", nombre='" + nombre + '\'' +
                ", value=" + value +
                '}';
    }

    public String[] toArray(){
        String[] toReturn = {""+getIdColumna(),""+getNombre(),""+getValue()};
        return toReturn;
    }


}
