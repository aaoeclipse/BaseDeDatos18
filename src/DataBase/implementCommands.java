package DataBase;
import Objects.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;
public class implementCommands implements CommandsSQL {
    Connection c = null;
    Statement statement = null;
    String sql = null;
    ArrayList<String> output = null;
    static String DB_URL = "jdbc:postgresql://localhost:5432/proyecto1";
    boolean debug = false;
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("connection successful");
        return true;
    }
    @Override
    public ArrayList<String> SELECT(String select) {
        try {
            output = new ArrayList<>();
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(select);
            while (rs.next()) {
                    output.add(rs.getString(1));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return output;
    }
    @Override
    public boolean DATABASELOGIN(String name, String username, String password) {
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + name,
                            username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("success to: " + name);
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
                    "  FROM public.\"Empleado\" ORDER BY id ASC;";
            //System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
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
    public ArrayList<User> getUsers(String name) {
        try {
            User newUser;
            ArrayList<User> toReturn = new ArrayList<>();
            statement = c.createStatement();
            sql = "SELECT id, nombre, apellido, salario, direccion, fecha_contratacion, horario, \n" +
                    "       departamento, foto_dir, fecha_nacimiento, id_puesto, id_tecnologia, \n" +
                    "       \"id_Proyecto\"\n" +
                    "  FROM public.\"Empleado\" WHERE LOWER(nombre) LIKE \'%" + name.replaceAll("[^a-zA-Z]", "") + "%\';";
            if (debug) {
                System.out.println(sql);
            }
            //System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
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
    public ArrayList<User> getUsersLastName(String name) {
        try {
            User newUser;
            ArrayList<User> toReturn = new ArrayList<>();
            statement = c.createStatement();
            sql = "SELECT id, nombre, apellido, salario, direccion, fecha_contratacion, horario, \n" +
                    "       departamento, foto_dir, fecha_nacimiento, id_puesto, id_tecnologia, \n" +
                    "       \"id_Proyecto\"\n" +
                    "  FROM public.\"Empleado\" WHERE LOWER(apellido) LIKE \'%" + name.replaceAll("[^a-zA-Z]", "") + "%\';";
            if (debug) {
                System.out.println(sql);
            }
            //System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
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
            if (rs.getString(1) == null)
                isCreating = true;
            else
                isCreating = false;
            statement.close();
        } catch (SQLException e) {
            isCreating = true;
        }
        if (isCreating) {
            try {
                statement = c.createStatement();
                sql = "INSERT INTO public.\"Empleado\"(\n" +
                        "id, nombre, apellido, salario, direccion,  horario, departamento, foto_dir, fecha_nacimiento, fecha_contratacion, id_puesto, id_tecnologia, " +
                        "\"id_Proyecto\")" +
                        "    VALUES (";
                for (String s : toAdd) {
                    if (s == null) {
                        s = "null";
                        sql += s + ",";
                    } else
                        sql += "\'" + s + "\',";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ");";
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR: addEmpleado");
                e.printStackTrace();
                return false;
            }
        } else {
            // IS UPDATING
            try {
                statement = c.createStatement();
                sql = "UPDATE public.\"Empleado\"\n" +
                        "SET (id, nombre, apellido, salario, direccion,  horario, departamento, foto_dir, fecha_nacimiento, fecha_contratacion, id_puesto, id_tecnologia,\"id_Proyecto\")\n = (";
                for (String s : toAdd) {
                    if (s == null) {
                        s = "null";
                        sql += s + ",";
                    } else
                        sql += "\'" + s + "\',";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ") WHERE id = " + toAdd[0];
                statement.executeUpdate(sql);
                statement.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR: addEmpleado");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean addColumnasExtras(int id, String nombre, String valor) {
        try {
            // GET NEW ID COLUMN
            int idDeCol = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Columna\" ORDER BY id DESC LIMIT 1;";
            ResultSet rs = statement.executeQuery(sql);
            if (rs != null) {
                rs.next();
                idDeCol = rs.getInt(1) + 1;
            } else {
                idDeCol = 1;
            }
            statement.close();
            // INSERT IN COLUMNS
            statement = c.createStatement();
            sql = "INSERT INTO \"Columna\" VALUES (" + idDeCol + ", \'" + nombre + "\', \'" + checkWhatType(valor) + "\')";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCESS");
            statement.close();
            // GET NEW ID VALUE
            int idDeVal = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Valor\" ORDER BY id DESC LIMIT 1;";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            rs = statement.executeQuery(sql);
            if (debug)
                System.out.println("SUCESS");
            if (rs != null) {
                rs.next();
                idDeVal = Integer.parseInt(rs.getString(1)) + 1;
            } else {
                idDeVal = 1;
            }
            statement.close();
            // INSERT IN VALUES
            statement = c.createStatement();
            sql = "INSERT INTO \"Valor\" VALUES (\'" + valor + "\', " + idDeCol + ", " + id + ", " + idDeVal + ")";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCESS");
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR: addColumnasExtras");
            e.printStackTrace();
            return false;
        }
    }
    private String checkWhatType(String valor) {
        boolean isString = false;
        String toReturn;
        for (int i =0; i < valor.length();i++){
            if (Character.isLetter(valor.charAt(i)))
                isString = true;
        }
        if (isString)
            toReturn = "String";
        else
            toReturn = "float";
        return toReturn;
    }
    @Override
    public void alterColumnExtras(int idColumna, int idValor, String nombreDeColumna, String value){
        // Se necesita 2 SQL UPDATES
        try {
            // Alterar columna
            statement = c.createStatement();
            sql = "UPDATE public.\"Columna\"\n" +
                    "   SET nombre=\'" + nombreDeColumna + "\', tipo=\'"+checkWhatType(value)+"\'\n" +
                    " WHERE id = " + idColumna +";";
            if (debug)
                System.out.println(sql);
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCCESS");
            // Alterar Valor
            statement = c.createStatement();
            sql = "UPDATE public.\"Valor\"\n" +
                    "   SET valor=\'"+value+"\'\n" +
                    " WHERE id = "+idValor+";";
            if (debug)
                System.out.println(sql);
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCCESS");
        } catch (SQLException e){
            System.err.println("ERROR: (alterando Columna)\n"+e);
        }
    }
    @Override
    public ArrayList<Proyecto> getProyectos() {
        try {
            ArrayList<Proyecto> proyectosToReturn = new ArrayList<>();
            Proyecto newProyecto;
            statement = c.createStatement();
            sql = "SELECT * FROM public.\"Proyecto\";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                newProyecto = new Proyecto(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3));
                proyectosToReturn.add(newProyecto);
            }
            return proyectosToReturn;
        } catch (SQLException e) {
            System.out.println("ERROR: Recibiendo Proyectos");
            System.out.println(e);
        }
        return null;
    }
    @Override
    public ArrayList<ColumnasExtras> getColumnasConValor(int empleado) {
        try {
            ArrayList<ColumnasExtras> columnasExtras = new ArrayList<>();
            ColumnasExtras columnasExtra;
            statement = c.createStatement();
            sql = "SELECT c.id, c.nombre, v.valor, v.id FROM \"Valor\" v INNER JOIN \"Columna\" c ON (v.id_columna = c.id)\n" +
                    "WHERE v.id_empleado = " + empleado;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                columnasExtra = new ColumnasExtras(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
                columnasExtras.add(columnasExtra);
                if (debug){
                    System.out.println(columnasExtra.toString());
                }
            }
            return columnasExtras;
        } catch (SQLException e) {
            System.out.println("ERROR: Recibiendo Columnas Extras Con Relacion a Usuario");
            System.out.println(e);
        }
        return null;
    }
    @Override
    public boolean addColumnasExtrasSinCambiarElTipo(int idEmpleado, int idDeCol, String valor) {
        try {
            // GET NEW ID VALUE
            int idDeVal = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Valor\" ORDER BY id DESC LIMIT 1;";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            ResultSet rs = statement.executeQuery(sql);
            if (debug)
                System.out.println("SUCESS");
            if (!rs.next()) {
                if (debug){
                    System.out.println("no hay resultado");
                }
                idDeVal = 1;
            } else {
                if (debug){
                    System.out.println("si hay resultado");
                }
                //rs.next();
                idDeVal = Integer.parseInt(rs.getString(1)) + 1;
                if (debug){
                    System.out.println("idDelValorNuevo = " + idDeVal);
                }
            }
            statement.close();
            // INSERT IN VALUES
            statement = c.createStatement();
            sql = "INSERT INTO \"Valor\" VALUES (\'" + valor + "\', " + idDeCol + ", " + idEmpleado + ", " + idDeVal + ")";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCESS");
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("ERROR: addColumnasExtras SIN CAMBIAR TIPO \n");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean addValorExtra(int idEmpleado, int idColumna, String valor) {
        try {
            // GET NEW ID VALUE
            int idDeVal = 1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Valor\" ORDER BY id DESC LIMIT 1;";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            ResultSet rs = statement.executeQuery(sql);
            if (debug)
                System.out.println("SUCESS");
            if (!rs.next()) {
                if (debug){
                    System.out.println("no hay resultado");
                }
                idDeVal = 1;
            } else {
                if (debug){
                    System.out.println("si hay resultado");
                }
                //rs.next();
                idDeVal = Integer.parseInt(rs.getString(1)) + 1;
                if (debug){
                    System.out.println("idDelValorNuevo = " + idDeVal);
                }
            }
            statement.close();
            // INSERT IN VALUES
            statement = c.createStatement();
            sql = "INSERT INTO \"Valor\" VALUES (\'" + valor + "\', " + idColumna + ", " + idEmpleado + ", " + idDeVal + ")";
            if (debug)
                System.out.print("SQL: " + sql + "\n...");
            statement.executeUpdate(sql);
            if (debug)
                System.out.println("SUCESS");
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("ERROR: addValor Extra");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public ArrayList<NombreConTipo> getColumnasExtras() {
        try {
            ArrayList<NombreConTipo> stringToReturn = new ArrayList<>();
            statement = c.createStatement();
            sql = "SELECT * FROM \"Columna\";";
            ResultSet rs = statement.executeQuery(sql);
            NombreConTipo nuevoTipo;
            while (rs.next()) {
                nuevoTipo = new NombreConTipo(rs.getInt(1), rs.getString(2), rs.getString(3));
                stringToReturn.add(nuevoTipo);
            }
            return stringToReturn;
        } catch (SQLException e) {
            System.err.println("ERROR: Recibiendo nombre de columnas Extras");
        }
        return null;
    }
    @Override
    public ArrayList<Puestos> getPuestos() {
        try {
            ArrayList<Puestos> puestosToReturn = new ArrayList<>();
            Puestos newPuesto;
            statement = c.createStatement();
            sql = "SELECT id, nombre FROM public.\"Puesto\";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                newPuesto = new Puestos(Integer.parseInt(rs.getString(1)), rs.getString(2));
                puestosToReturn.add(newPuesto);
            }
            return puestosToReturn;
        } catch (SQLException e) {
            System.out.println("ERROR: Recibiendo Puestos");
        }
        return null;
    }
    @Override
    public ArrayList<Tecnologia> getTecnologia() {
        try {
            ArrayList<Tecnologia> puestosToReturn = new ArrayList<>();
            Tecnologia newTecnologia;
            statement = c.createStatement();
            sql = "SELECT id, nombre FROM public.\"Tecnologia\";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                newTecnologia = new Tecnologia(Integer.parseInt(rs.getString(1)), rs.getString(2));
                puestosToReturn.add(newTecnologia);
            }
            return puestosToReturn;
        } catch (SQLException e) {
            System.out.println("ERROR: Recibiendo Tecnologia");
        }
        return null;
    }
    @Override
    public int getNewID() {
        try {
            int toReturn = -1;
            statement = c.createStatement();
            sql = "SELECT id FROM \"Empleado\" ORDER BY id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            toReturn = Integer.parseInt(rs.getString(1));
            toReturn += 1;
            statement.close();
            return toReturn;
        } catch (SQLException e) {
            System.out.println("ERROR: getNewID");
            System.out.println(e);
            return -1;
        }
    }
    private boolean checkIfValorExist(int id){
        return false; // TODO
    }
}