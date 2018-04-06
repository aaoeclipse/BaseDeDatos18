package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by luisa on 5/04/18.
 */
public class twitterInfo {
    private JTextArea info;
    private JLabel fe;
    private JLabel us;
    private JLabel user;
    private JLabel date;
    private JScrollPane scr;
    private JPanel tweetForm;
    private JButton returnB;
    private JFrame thisWindow;

    public twitterInfo(){
        thisWindow = new JFrame("Table");
        thisWindow.setContentPane(tweetForm);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1050,400);
        thisWindow.setVisible(true);

    }
    public void view(String tweet, String date,String user){
        fe.setText(date);
        if(tweet.contains("RT @")){
            String valor = tweet.split(" ")[1];
            valor = valor.substring(1,valor.indexOf(":"));
            System.out.println(valor);
            us.setText(valor);

            valor = tweet.substring(tweet.indexOf(":"));
            info.append(valor);

        }else{
            us.setText(user);
            info.append(tweet);
        }
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thisWindow.dispose();

            }
        });
    }
}
