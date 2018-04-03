import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Font;

public class AddNewProyect {

    private JFrame frame;
    private Evento evento = new Evento();
    private JTextArea input;
    private JButton btnTestInput;
    private JLabel lblErrors;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddNewProyect window = new AddNewProyect();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AddNewProyect() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 351, 223);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        input = new JTextArea();
        input.setBounds(76, 62, 200, 14);
        frame.getContentPane().add(input);

        btnTestInput = new JButton("Crear Proyecto");
        btnTestInput.setBounds(76, 87, 166, 23);
        btnTestInput.addActionListener(evento);
        frame.getContentPane().add(btnTestInput);

        JLabel lblInput = new JLabel("Nombre");
        lblInput.setBounds(10, 58, 46, 14);
        frame.getContentPane().add(lblInput);

        lblErrors = new JLabel("Proyecto Agregado Exitosamente");
        lblErrors.setBounds(10, 118, 232, 23);
        lblErrors.setVisible(false);
        frame.getContentPane().add(lblErrors);

        JLabel lblNewLabel = new JLabel("Crear Un Proyecto");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 303, 36);
        frame.getContentPane().add(lblNewLabel);
    }
    private class Evento implements ActionListener{

        public void actionPerformed(ActionEvent a) {
            if(a.getSource()==btnTestInput) {
                String nombreProyecto = input.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                String idQuerry = "SELECT id FROM public.\"Proyecto\" ORDER BY public.\"Proyecto\".\"id\" DESC LIMIT 1";
                int id = Integer.parseInt(idQuerry) + 1;
                String querry = "INSERT INTO public.\"Proyecto\"( id, nombre, fecha_inicio) VALUES ("+id+", '"+nombreProyecto+"', '"+dateFormat.format(date)+"');";
                lblErrors.setVisible(true);
            }
        }
    }
}