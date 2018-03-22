package DataBase;
import Objects.User;

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
	public String LOADDBS() {
		return null;
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
	public void addEmpleado() {

	}

	@Override
	public void addProyecto() {

	}

	@Override
	public void assignEmpleadoProyecto(int user, int proyecto) {

	}

}
