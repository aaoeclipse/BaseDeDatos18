package DataBase;

import Objects.*;

import java.util.ArrayList;
import java.util.Stack;

public interface CommandsSQL {
	
	//REQUERIMIENTO: nada
	//Se utiliza para conectarse a postgres con un usuario y con una contraseña
	public boolean Connect(String username, String password);
	
	//REQUERIMIENTO: Estar conectado ya a postgres y a una base de datos
	//Selecciona un subconjunto en una tabla. Si no encuentra la columna o la tabla regresa falso.
	public boolean SELECT(String select);
	
	//REQUERIMIENTO: Estar en una base de datos
	//
	public boolean INSERTINTO(String name, String insertar);
	
	//REQUERIMIENTO: nada
	//
	public boolean DATABASELOGIN(String name, String username, String password);

	public ArrayList<User> getUsers();

	public boolean addEmpleado(User userToAdd);

	public boolean addProyecto(Proyecto proyectoToAdd);

	public boolean addColumnasExtras(int id, String nombre, String valor);

	public void alterColumnExtras(int user, int proyecto);

	public ArrayList<Proyecto> getProyectos();
	public ArrayList<String> getColumnasExtras();
	public ArrayList<Puestos> getPuestos();
	public ArrayList<Tecnologia> getTecnologia();
	public int getNewID();
    public ArrayList<ColumnasExtras> getColumnasConValor(int empleado);


}
