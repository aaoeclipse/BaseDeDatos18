import DataBase.CommandsSQL;
import DataBase.implementCommands;
import GUI.AddUser;
import GUI.Login;

import javax.swing.*;

public class main {
    public static void main (String[] args){
        CommandsSQL dbConnection = new implementCommands();
        Login login = new Login();

    }
}
