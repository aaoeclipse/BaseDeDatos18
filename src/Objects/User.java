package Objects;

public class User {
    private int idUser;
    private String nombre;
    private String apellido;
    public float salario;
    private String direccion;
    private String horrario;
    private String departamento;
    private String foto_dir;
    private String fecha_nacimiento;
    private String fecha_contratacion;

    public User(int idUser, String nombre, String apellido, float salario, String direccion, String horrario, String departamento, String foto_dir, String fecha_nacimiento, String fecha_contratacion) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.direccion = direccion;
        this.horrario = horrario;
        this.departamento = departamento;
        this.foto_dir = foto_dir;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
    }

    // GETTERS
    public int getIdUser() {
        return idUser;
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

    // SETTERS
    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}
