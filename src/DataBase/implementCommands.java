package DataBase;
import java.sql.*;

public class implementCommands implements CommandsSQL{
	Connection c = null;
	Statement statement = null;
	String sql = null;
	String output = null;
	
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
	public boolean DROP(String name) {
		try {
			statement = c.createStatement();
			sql = "DROP TABLE "+name;
			statement.executeUpdate(sql);
			statement.close();
			c.close(); 
		} catch (SQLException e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			return false;
		}
		
		return true;
	}
	public boolean CREATETABLE (String name){
		try {
			statement = c.createStatement();
			sql = "CREATE TABLE "+name;
			statement.executeUpdate(sql);
			statement.close();
			c.close(); 
		} catch (SQLException e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			return false;
		}
		return true;
	}
	@Override
	public String CREATEDB(String name) {
		return null;
	}

	@Override
	public boolean Connect(String username, String password) {
		try {
			if (password == null)
				password = "";
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/",
							username, password);
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	        return false;
		}
		System.out.println("connection successful");
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

}
