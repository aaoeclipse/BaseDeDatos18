package GUI;

import Objects.User;
import twitter.Tweets;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JButton returnB;
    DefaultTableModel model;


    public TwitterView() {

        thisWindow = new JFrame("Table");
        thisWindow.setContentPane(TableForm);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1250,400);
        thisWindow.setVisible(true);

        DefaultTableModel model = (DefaultTableModel) tablaTweets.getModel();
        tablaTweets.setShowGrid(true);
        String[] nombreColumnas = {
                "Date",
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

                try {
                    messages = tw.getTweets(user);
                    tweetsLabel.setText(messages.size()+" Tweets ");
                }
                catch(Exception e){
                    //hola
                }
                for (int i = 0; i < messages.size();i++) {
                    model.addRow(messages.get(i));
                }
            }
        });
        tablaTweets.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                twitterInfo twI = new twitterInfo();
                twI.view(tablaTweets.getValueAt(tablaTweets.getSelectedRow(),1).toString(),tablaTweets.getValueAt(tablaTweets.getSelectedRow(),0).toString(),user);
                //System.out.println(tablaTweets.getValueAt(tablaTweets.getSelectedRow(),0).toString());
            }
        });
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thisWindow.dispose();

            }
        });



    }



}
