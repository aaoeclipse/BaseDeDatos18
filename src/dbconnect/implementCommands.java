package dbconnect;

import java.sql.*;

public class implementCommands implements CommandsSQL {

    Connection c = null;
    Statement statement = null;
    String sql = null;
    String output = null;

    @Override
    public boolean Connect() {
        try {
            // TODO url test
            String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
            c = DriverManager
                    .getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        System.out.println("connection successful");
        return true;
    }
    
    @Override
    public boolean SELECT(String select, String fromTable) {
        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + select + " FROM " + fromTable);
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
    public boolean DROP(String name) {
        try {
            statement = c.createStatement();
            sql = "DROP TABLE " + name;
            statement.executeUpdate(sql);
            statement.close();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }
    @Override
    public boolean CREATETABLE(String name) {
        try {
            statement = c.createStatement();
            sql = "CREATE TABLE " + name;
            statement.executeUpdate(sql);
            statement.close();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public String CREATEDB(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean INSERTINTO(String name, String insertar) {
        try {
            statement = c.createStatement();
            sql = "DROP TABLE " + name;
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
                    .getConnection("jdbc:postgresql://localhost:5432/" + name,
                            username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        System.out.println("success to: " + name);
        return true;
    }

    @Override
    public String LOADDBS() {
        // TODO Auto-generated method stub
        return null;
    }

}
