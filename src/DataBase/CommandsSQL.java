package DataBase;

import Objects.User;

import java.util.ArrayList;
import java.util.Stack;

public interface CommandsSQL {
	
	//REQUERIMIENTO: nada
	//Se utiliza para conectarse a postgres con un usuario y con una contraseña
	public boolean Connect(String username, String password);
	
	//REQUERIMIENTO: Estar conectado ya a postgres y a una base de datos
	//Selecciona un subconjunto en una tabla. Si no encuentra la columna o la tabla regresa falso.
	public boolean SELECT(String select, String fromTable);
	
	//REQUERIMIENTO: Estar en una base de datos
	//
	public boolean INSERTINTO(String name, String insertar);
	
	//REQUERIMIENTO: nada
	//
	public boolean DATABASELOGIN(String name, String username, String password);

	public String LOADDBS();

	public ArrayList<User> getUsers();

	public void addEmpleado();

	public void addProyecto();

	public void assignEmpleadoProyecto(int user, int proyecto);

	// SHOW PROYECTOS
    //
}
