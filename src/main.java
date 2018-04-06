import DataBase.CommandsSQL;
import DataBase.implementCommands;
import GUI.AddUser;
import GUI.Login;
import GUI.TwitterView;
import twitter.Tweets;

import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main (String[] args) throws IOException {
        CommandsSQL dbConnection = new implementCommands();
        Login login = new Login();


    }
}
