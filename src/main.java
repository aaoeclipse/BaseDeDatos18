import GUI.Login;

import javax.swing.*;

public class main {
    public static void main (String[] args){
        JFrame window = new JFrame("Login");
        window.setContentPane(new Login().loginView);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
