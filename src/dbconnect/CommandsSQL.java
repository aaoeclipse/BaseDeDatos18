package dbconnect;

public interface CommandsSQL {
	
	//REQUERIMIENTO: nada
	//Se utiliza para conectarse a postgres con un usuario y con una contraseña
	public boolean Connect();
	
	//REQUERIMIENTO: Estar conectado ya a postgres y a una base de datos
	//Selecciona un subconjunto en una tabla. Si no encuentra la columna o la tabla regresa falso.
	public boolean SELECT(String select,String fromTable);
	
	//REQUERIMIENTO: estar conectado a postgres y a la base de datos 
	//
	public boolean DROP(String name);
	
	//REQUERIMIENTO: Estar conectado a postgres y a una base de datos
	//
	public boolean CREATETABLE (String name);
	
	//REQUERIMIENTO: Estar en una base de datos
	//
	public boolean INSERTINTO (String name, String insertar);
	
	//REQUERIMIENTO: nada
	//
	public boolean DATABASELOGIN (String name, String username, String password);
	
	//REQUERIMIENTO: 
	//
	public String CREATEDB(String name);
	
	public String LOADDBS();
}
