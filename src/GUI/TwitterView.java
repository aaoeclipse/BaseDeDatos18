package GUI;

import Objects.User;
import twitter.Tweets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by luisa on 4/04/18.
 */
public class TwitterView {

    private JButton getTweet;
    private JPanel TableForm;
    private JTextField userName;
    private JTable tablaTweets;
    private JFrame thisWindow;
    String user;
    String[] mes = new String[2];
    private int i;
    private JLabel tweetsLabel;


    public TwitterView() {

        thisWindow = new JFrame("Table");
        thisWindow.setContentPane(TableForm);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1550,400);
        thisWindow.setVisible(true);

        DefaultTableModel model = (DefaultTableModel) tablaTweets.getModel();
        tablaTweets.setShowGrid(true);
        String[] nombreColumnas = {
                "Fecha",
                "Tweet"
        };
        for (int i = 0; i < nombreColumnas.length;i++)
            model.addColumn(nombreColumnas[i]);

        getTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)  {
                Tweets tw =  new Tweets();
                ArrayList<String[]> messages = new ArrayList<String[]>();
                user = userName.getText();
                //System.out.println(user);
                userName.setText("");
                tweetsLabel.setText(user+"'s Tweets ");
                try {
                    messages = tw.getTweets(user);
                }
                catch(IOException e){
                    //hola
                }
                for (int i = 0; i < messages.size();i++) {
                    model.addRow(messages.get(i));
                }
            }
        });



    }


}
