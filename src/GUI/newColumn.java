package GUI;

import DataBase.CommandsSQL;
import Objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newColumn {
    private JTextField nombre;
    private JTextField valor;
    private JButton agregarButton;
    private JButton cancelarButton;
    private JPanel thisColum;
    private CommandsSQL dbconnection;
    private JFrame thisWindow;
    private User user;
    public newColumn(CommandsSQL dbconnection, User user){
        this.user = user;
        this.dbconnection = dbconnection;

        thisWindow = new JFrame();
        thisWindow.setContentPane(thisColum);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(500,200);
        thisWindow.setVisible(true);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (dbconnection.addColumnasExtras(user.getIdEmpleado(), nombre.getText(), valor.getText())){
                    AddUser newWindo = new AddUser(user, dbconnection);
                    thisWindow.dispose();
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUser newWindo = new AddUser(user, dbconnection);
                thisWindow.dispose();
            }
        });
    }
}
