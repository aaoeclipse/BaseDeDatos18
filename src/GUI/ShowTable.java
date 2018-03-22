package GUI;

import DataBase.CommandsSQL;
import Objects.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class ShowTable {
    private JTable tablaUsuarios;
    private JTextField searchTextField;
    private JButton addUSerButton;
    private JButton logOutButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    public JPanel TableForm;
    private JLabel spacer;
    private JFrame thisWindow;
    private ArrayList<User> users;
    private CommandsSQL dbconnection;
    private boolean first;
    private boolean notReopen = false;
    public ShowTable(CommandsSQL dbconnection) {
        // connexion a base de datos
        this.dbconnection = dbconnection;

        // Window
        thisWindow = new JFrame("Table");
        thisWindow.setContentPane(TableForm);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1024,1024);
        thisWindow.setVisible(true);

        // init
        first = true;

        // Tabla
        DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
        tablaUsuarios.setShowGrid(true);
        // Columnas
        TableColumn[] columnasUsuarios = new TableColumn[13];
        String[] nombreColumnas = {
                "ID",
                "Nombre",
                "Apellido",
                "Salario",
                "Direccion",
                "Horario",
                "Departamento",
                "Foto",
                "Nacimiento",
                "Contratacion",
                "Puesto",
                "Tecnologia",
                "Proyecto"
        };
        for (int i = 0; i < nombreColumnas.length;i++)
            model.addColumn(nombreColumnas[i]);
        users = dbconnection.getUsers();
        for (int i = 0; i < users.size();i++) {
            model.addRow(users.get(i).stringArray());
        }
        tablaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaUsuarios.getSelectedRow() > -1) {
                    System.out.println(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString());
                    if (!notReopen){
                        notReopen = true;
                        AddUser newWindow = new AddUser(users.get(Integer.parseInt(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString())),dbconnection);
                        thisWindow.dispose();
                    }

                }
            }
        });
        /*

         */
        // Listners
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login newWindow = new Login();
                thisWindow.dispose();
            }
        });
        addUSerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUser newWindow = new AddUser(dbconnection);
                thisWindow.dispose();
            }
        });
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(first)
                    searchTextField.setText("");
            }
        });
    }

}
