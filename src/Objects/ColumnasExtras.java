package Objects;

public class ColumnasExtras {
    private int idColumna;
    private String nombre;
    private String value;
    private int idValor;
    private boolean existe;
    private String type;

    public ColumnasExtras(int idColumna , String nombre, String value, int idValor) {
        this.idColumna = idColumna;
        this.nombre = nombre;
        this.value = value;
        this.idValor = idValor;
        this.existe = false;
        this.type = "String";
    }

    public ColumnasExtras(int idColumna, String nombre, String value, int idValor, boolean existe, String type) {
        this.idColumna = idColumna;
        this.nombre = nombre;
        this.value = value;
        this.idValor = idValor;
        this.existe = existe;
        this.type = type;
    }

    public ColumnasExtras(int idColumna , String nombre, String value, int idValor, boolean siExiste) {
        this.idColumna = idColumna;
        this.nombre = nombre;
        this.value = value;
        this.idValor = idValor;
        this.existe = siExiste;
        this.type = "String";
    }

    public int getIdValor() {
        return idValor;
    }

    public void setIdValor(int idValor) {
        this.idValor = idValor;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ColumnasExtras{" +
                "idColumna=" + idColumna +
                ", nombre='" + nombre + '\'' +
                ", value='" + value + '\'' +
                ", idValor=" + idValor +
                ", existe=" + existe +
                ", type='" + type + '\'' +
                '}';
    }

    public String[] toArray(){
        String[] toReturn = {""+getIdColumna(),""+getNombre(),""+getValue()};
        return toReturn;
    }


}
