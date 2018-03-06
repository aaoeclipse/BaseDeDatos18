package GUI;

import DataBase.CommandsSQL;
import DataBase.implementCommands;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JButton button1;
    public JPanel loginView;
    private JPasswordField passField;
    private JTextField userField;
    CommandsSQL database;

    public Login(){
        database = new implementCommands();
        button1.addActionListener(new changeText());
    }

    private class changeText implements ActionListener{
        public changeText() {

        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            char[] breakdown = passField.getPassword();
            String pass = "";
            for(int i = 0; i < breakdown.length;i++)
                pass += breakdown[i];
            if(database.Connect(userField.getText(),pass))
                 userField.setText("Success");
             else
                 userField.setText("failed");
        }
    }

}
