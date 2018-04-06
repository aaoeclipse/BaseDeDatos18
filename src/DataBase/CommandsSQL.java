package DataBase;
import Objects.*;
import java.util.ArrayList;
public interface CommandsSQL {
    //REQUERIMIENTO: nada
    //Se utiliza para conectarse a postgres con un usuario y con una contraseña
    public boolean Connect(String username, String password);
    //REQUERIMIENTO: Estar conectado ya a postgres y a una base de datos
    //Selecciona un subconjunto en una tabla. Si no encuentra la columna o la tabla regresa falso.
    public String SELECT(String select);
    //REQUERIMIENTO: nada
    //
    public boolean DATABASELOGIN(String name, String username, String password);
    //REQUERIMIENTO: nada
    // Regresa los usuarios en una lista de objeto usuario
    public ArrayList<User> getUsers();
    //REQUERIMIENTO: Estar conectado ya a postgres y a una base de datos
    //Selecciona un subconjunto en una tabla. Si no encuentra la columna o la tabla regresa falso.
    public ArrayList<User> getUsers(String name);
    public ArrayList<User> getUsersLastName(String name);
    public boolean addEmpleado(User userToAdd);
    public boolean addColumnasExtras(int idUsuario, String nombre, String valor);
    public void alterColumnExtras(int idColumna, int idValor, String name, String value);
    public ArrayList<Proyecto> getProyectos();
    public ArrayList<NombreConTipo> getColumnasExtras();
    public ArrayList<Puestos> getPuestos();
    public ArrayList<Tecnologia> getTecnologia();
    public int getNewID();
    public ArrayList<ColumnasExtras> getColumnasConValor(int empleado);
    public boolean addColumnasExtrasSinCambiarElTipo(int idEmpleado, int idDeCol, String aNull);
    public boolean addValorExtra(int idEmpleado, int idColumna, String valor);
}