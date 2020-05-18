package DataBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PostgreSQLJDBC {
	
	public static void main(String args[]) {
		String password;
		String userName;
		CommandsSQL impl = new implementCommands();

		//CONNECT
		userName = "postgres";
		password = "spartan2012";
		impl.Connect(userName, password);
		//Finish Connecting
		impl.DATABASELOGIN("ropa", userName, password);

	}
}