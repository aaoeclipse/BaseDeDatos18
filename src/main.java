import DataBase.CommandsSQL;
import DataBase.implementCommands;
import GUI.AddUser;
import GUI.Login;

import javax.swing.*;

public class main {
    public static void main (String[] args){
        CommandsSQL dbConnection = new implementCommands();
        dbConnection.Connect("postgres","spartan2012");
        AddUser newuser = new AddUser(dbConnection);

    }
}
