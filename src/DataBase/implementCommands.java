package DataBase;
import Objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

public class implementCommands implements CommandsSQL{
	Connection c = null;
	Statement statement = null;
	String sql = null;
	String output = null;
	static String DB_URL = "jdbc:postgresql://localhost:5432/proyecto1";

    @Override
    public boolean Connect(String username, String password) {
        try {
            if (password == null)
                password = "";
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL,
                            username, password);

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return false;
        }
        System.out.println("connection successful");
        return true;
    }

	public boolean SELECT(String select, String fromTable) {
		try {
			statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT " + select + " FROM "+ fromTable);
			while (rs.next()) {
				  output = rs.getString(select);
				  System.out.println(output);
				}
			statement.close();
			c.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean INSERTINTO(String name, String insertar) {
		try {
			statement = c.createStatement();
			sql = "DROP TABLE "+name;
			statement.executeUpdate(sql);
			statement.close();
			c.close(); 
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean DATABASELOGIN(String name, String username, String password) {
		try {
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/"+name,
							username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("success to: "+name);


		return true;
	}

	@Override
	public ArrayList<User> getUsers() {
		try {
            User newUser;
            ArrayList<User> toReturn = new ArrayList<>();
			statement = c.createStatement();
			sql = "SELECT id, nombre, apellido, salario, direccion, fecha_contratacion, horario, \n" +
                    "       departamento, foto_dir, fecha_nacimiento, id_puesto, id_tecnologia, \n" +
                    "       \"id_Proyecto\"\n" +
                    "  FROM public.\"Empleado\";";
            //System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
			    //Hay 14 columnas
                newUser = new User();
                newUser.setIdEmpleado(Integer.parseInt(rs.getString(1)));
                newUser.setNombre(rs.getString(2));
                newUser.setApellido(rs.getString(3));
                newUser.setSalario(Float.parseFloat(rs.getString(4)));
                newUser.setDireccion(rs.getString(5));
                newUser.setFecha_contratacion(rs.getString(6));
                newUser.setHorrario(rs.getString(7));
                newUser.setDepartamento(rs.getString(8));
                newUser.setFoto_dir(rs.getString(9));
                newUser.setFecha_nacimiento(rs.getString(10));
                newUser.setIdPuesto(Integer.parseInt(rs.getString(11)));
                newUser.setId_tecnologia(Integer.parseInt(rs.getString(12)));
                newUser.setId_Proyecto(Integer.parseInt(rs.getString(13)));
                toReturn.add(newUser);
            }
            statement.close();
			return toReturn;
		} catch (SQLException e) {
			System.out.println("Error Consiguiendo datos");
			//e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean addEmpleado(User userToAdd) {
        String[] toAdd = userToAdd.stringArray();
        boolean isCreating = false;
        try {
            statement = c.createStatement();
            sql = "SELECT id FROM \"Empleado\" WHERE id = " + userToAdd.getIdEmpleado();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if(rs.getString(1) == null)
                isCreating = true;
            else
                isCreating = false;
            statement.close();
        }catch (SQLException e){
            isCreating = true;
        }
        if (isCreating){
            try{
                statement = c.createStatement();
                sql = "INSERT INTO public.\"Empleado\"(\n" +
                        "id, nombre, apellido, salario, direccion,  horario, departamento, foto_dir, fecha_nacimiento, fecha_contratacion, id_puesto, id_tecnologia, " +
                        "\"id_Proyecto\")" +
                        "    VALUES (";
                for (String s: toAdd) {
                    if (s == null) {
                        s = "null";
                        sql += s + ",";
                    }else
                        sql += "\'"+ s + "\',";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ");";
                statement.executeUpdate(sql);
                statement.close();
            }catch (SQLException e){
                System.out.println("ERROR: addEmpleado");
                e.printStackTrace();
                return false;
            }
        } else {
            // IS UPDATING
            try{
                statement = c.createStatement();
                sql = "UPDATE public.\"Empleado\"\n" +
                        "SET (id, nombre, apellido, salario, direccion,  horario, departamento, foto_dir, fecha_nacimiento, fecha_contratacion, id_puesto, id_tecnologia,\"id_Proyecto\")\n = (";
                for (String s: toAdd) {
                    if (s == null) {
                        s = "null";
                        sql += s + ",";
                    }else
                        sql += "\'"+ s + "\',";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ") WHERE id = "+ toAdd[0];
                statement.executeUpdate(sql);
                statement.close();
                return true;
            }catch (SQLException e){
                System.out.println("ERROR: addEmpleado");
                e.printStackTrace();
                return false;
            }
        }

        return true;
	}

	@Override
	public boolean addProyecto(Proyecto proyectoToAdd) {
        return false;

	}

    @Override
    public boolean addColumnasExtras(int id, String nombre, String valor) {
        try{
            // GET NEW ID COLUMN
            int idDeCol = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Columna\" ORDER BY id DESC LIMIT 1;";
            ResultSet rs = statement.executeQuery(sql);
            if(rs != null) {
                rs.next();
                idDeCol = rs.getInt(1)+1;
            } else {
                idDeCol = 1;
            }
            statement.close();
            // INSERT IN COLUMNS
            statement = c.createStatement();
            sql = "INSERT INTO \"Columna\" VALUES (" + idDeCol + ", \'" + nombre + "\', " + valor + ")";
            statement.executeUpdate(sql);
            statement.close();

            // GET NEW ID VALUE
            int idDeVal = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Valor\" ORDER BY id DESC LIMIT 1;";
            rs = statement.executeQuery(sql);
            if(rs != null) {
                rs.next();
                idDeVal = Integer.parseInt(rs.getString(1))+1;
            } else {
                idDeVal = 1;
            }
            statement.close();

            // INSERT IN VALUES
            statement = c.createStatement();
            sql = "INSERT INTO \"Valor\" VALUES (" + valor + ", " + idDeCol + ", " + id + ", "+ idDeVal + ")";
            statement.executeUpdate(sql);
            statement.close();
            return true;

        }catch (SQLException e){
            System.out.println("ERROR: addColumnasExtras");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void alterColumnExtras(int user, int proyecto) {
    /**    try{

            // ALTER IN COLUMNS
            statement = c.createStatement();
            sql = "INSERT INTO \"Columna\" VALUES (" + idDeCol + ", \'" + nombre + "\', " + valor + ")";
            statement.executeUpdate(sql);
            statement.close();

            // ALTER IN VALUES
            statement = c.createStatement();
            sql = "INSERT INTO \"Valor\" VALUES (" + valor + ", " + idDeCol + ", " + id + ", "+ idDeVal + ")";
            statement.executeUpdate(sql);
            statement.close();
            return true;

        }catch (SQLException e){
            System.out.println("ERROR: addColumnasExtras");
            e.printStackTrace();
            return false;
        }**/

    }
    @Override
    public ArrayList<Proyecto> getProyectos() {
        try{
            ArrayList<Proyecto> proyectosToReturn = new ArrayList<>();
            Proyecto newProyecto;
            statement = c.createStatement();
            sql = "SELECT * FROM public.\"Proyecto\";";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                newProyecto = new Proyecto(Integer.parseInt(rs.getString(1)), rs.getString(2),rs.getString(3));
                proyectosToReturn.add(newProyecto);
            }
            return proyectosToReturn;
        }catch (SQLException e){
            System.out.println("ERROR: Recibiendo Proyectos");
            System.out.println(e);
        }
        return null;
    }
    @Override
    public ArrayList<ColumnasExtras> getColumnasConValor(int empleado) {
        try{
            ArrayList<ColumnasExtras> columnasExtras = new ArrayList<>();
            ColumnasExtras columnasExtra;
            statement = c.createStatement();
            sql = "SELECT v.id, c.nombre, v.valor FROM \"Valor\" v INNER JOIN \"Columna\" c ON (v.id_columna = c.id)\n" +
                   "WHERE v.id_empleado = " + empleado;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                columnasExtra = new ColumnasExtras(Integer.parseInt(rs.getString(1)), rs.getString(2), Float.parseFloat(rs.getString(3)));
                columnasExtras.add(columnasExtra);
            }
            return columnasExtras;
        }catch (SQLException e){
            System.out.println("ERROR: Recibiendo Columnas Extras Con Relacion a Usuario");
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String[][] INSERTSQL(String sql) {
        try{
            String[][] toReturn;
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);


        } catch (SQLException e){

        }
        return null;
    }

    @Override
    public void runSQL(String sql) {
        try{
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);


        } catch (SQLException e){

        }
    }

    @Override
    public ArrayList<String> getColumnasExtras() {
        try{
            ArrayList<String> stringToReturn = new ArrayList<>();
            statement = c.createStatement();
            sql = "SELECT nombre FROM \"Columna\";";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                stringToReturn.add(rs.getString(1));
            }
            return stringToReturn;
        }catch (SQLException e){
            System.out.println("ERROR: Recibiendo nombre de columnas Extras");
        }
        return null;
    }

    @Override
    public ArrayList<Puestos> getPuestos() {
        try{
            ArrayList<Puestos> puestosToReturn = new ArrayList<>();
            Puestos newPuesto;
            statement = c.createStatement();
            sql = "SELECT id, nombre FROM public.\"Puesto\";";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                newPuesto = new Puestos(Integer.parseInt(rs.getString(1)), rs.getString(2));
                puestosToReturn.add(newPuesto);
            }
            return puestosToReturn;
        }catch (SQLException e){
            System.out.println("ERROR: Recibiendo Puestos");
        }
        return null;
    }

    @Override
    public ArrayList<Tecnologia> getTecnologia() {
        try{
            ArrayList<Tecnologia> puestosToReturn = new ArrayList<>();
            Tecnologia newTecnologia;
            statement = c.createStatement();
            sql = "SELECT id, nombre FROM public.\"Tecnologia\";";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                newTecnologia = new Tecnologia(Integer.parseInt(rs.getString(1)), rs.getString(2));
                puestosToReturn.add(newTecnologia);
            }
            return puestosToReturn;
        }catch (SQLException e){
            System.out.println("ERROR: Recibiendo Tecnologia");
        }
        return null;
    }

    @Override
    public int getNewID() {
        try{
            int toReturn = -1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Empleado\" ORDER BY id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            toReturn = Integer.parseInt(rs.getString(1));
            toReturn += 1;
            statement.close();
            return toReturn;
        }catch (SQLException e){
            System.out.println("ERROR: getNewID");
            System.out.println(e);
            return -1;
        }
    }

}
