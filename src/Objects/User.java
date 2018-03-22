package Objects;

public class User {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    public float salario;
    private String direccion;
    private String horrario;
    private String departamento;
    private String foto_dir;
    private String fecha_nacimiento;
    private String fecha_contratacion;
    private int idPuesto;
    private int id_tecnologia;
    private int id_Proyecto;

    public User(){

    }

    public User(int idEmpleado, String nombre, String apellido, float salario, String direccion, String horrario, String departamento, String foto_dir, String fecha_nacimiento, String fecha_contratacion, int idPuesto, int id_tecnologia, int id_Proyecto) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.direccion = direccion;
        this.horrario = horrario;
        this.departamento = departamento;
        this.foto_dir = foto_dir;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
        this.idPuesto = idPuesto;
        this.id_tecnologia = id_tecnologia;
        this.id_Proyecto = id_Proyecto;
    }
//GETTERS
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public float getSalario() {
        return salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorrario() {
        return horrario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getFoto_dir() {
        return foto_dir;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getFecha_contratacion() {
        return fecha_contratacion;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public int getId_tecnologia() {
        return id_tecnologia;
    }

    public int getId_Proyecto() {
        return id_Proyecto;
    }
//SETTERS
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorrario(String horrario) {
        this.horrario = horrario;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setFoto_dir(String foto_dir) {
        this.foto_dir = foto_dir;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setFecha_contratacion(String fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public void setId_tecnologia(int id_tecnologia) {
        this.id_tecnologia = id_tecnologia;
    }

    public void setId_Proyecto(int id_Proyecto) {
        this.id_Proyecto = id_Proyecto;
    }
    public String[] stringArray(){
        String[] toReturn = new String[13];
        toReturn[0] = "" +idEmpleado;
        toReturn[1] = nombre;
        toReturn[2] = apellido;
        toReturn[3] = ""+salario;
        toReturn[4] = direccion;
        toReturn[5] = horrario;
        toReturn[6] = departamento;
        toReturn[7] = foto_dir;
        toReturn[8] = fecha_nacimiento;
        toReturn[9] = fecha_contratacion;
        toReturn[10] = ""+idPuesto;
        toReturn[11] = ""+id_tecnologia;
        toReturn[12] = ""+id_Proyecto;
        return toReturn;
    }
    @Override
    public String toString() {
        return "User{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", salario=" + salario +
                ", direccion='" + direccion + '\'' +
                ", horrario='" + horrario + '\'' +
                ", departamento='" + departamento + '\'' +
                ", foto_dir='" + foto_dir + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", fecha_contratacion='" + fecha_contratacion + '\'' +
                ", idPuesto=" + idPuesto +
                ", id_tecnologia=" + id_tecnologia +
                ", id_Proyecto=" + id_Proyecto +
                '}';
    }
}
