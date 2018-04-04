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
    private JButton view1Button;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    public JPanel TableForm;
    private JLabel spacer;
    private JButton searchButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JFrame thisWindow;
    private ArrayList<User> users;
    private CommandsSQL dbconnection;
    private boolean first;
    private boolean notReopen = false; // cuando se hace click, se activa muchas veces, esto evita que se haga reopen

    public ShowTable(CommandsSQL dbconnection) {
        // connexion a base de datos
        this.dbconnection = dbconnection;

        // Window
        thisWindow = new JFrame("Table");
        thisWindow.setContentPane(TableForm);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1550, 800);
        thisWindow.setVisible(true);

        // init
        first = true;

        // Tabla
        DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
        tablaUsuarios.setShowGrid(true);
        // Columnas
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
        // El Esquema de la tabla (se usa el model para ponerle las columnas y las filas)
        for (int i = 0; i < nombreColumnas.length; i++)
            model.addColumn(nombreColumnas[i]);
        // Informacion de la Tabla
        users = dbconnection.getUsers(); // DB gets user
        for (int i = 0; i < users.size(); i++) {
            model.addRow(users.get(i).stringArray());
        }

        // CLICK LISTNER PARA LA TABLA
        tablaUsuarios.getSelectionModel().addListSelectionListener(event -> {
            if (tablaUsuarios.getSelectedRow() > -1) {
                if (!notReopen) {
                    notReopen = true;
                    AddUser newWindow = new AddUser(users.get(Integer.parseInt(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString())), dbconnection);
                    thisWindow.dispose();
                }
            }
        });

        // Listners
        // LOGOUT BUTTON
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login newWindow = new Login();
                thisWindow.dispose();
            }
        });
        // ADD USER BUTTON
        addUSerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUser newWindow = new AddUser(dbconnection);
                thisWindow.dispose();
            }
        });
        // SEARCH FIELD desaparece lo que lleva adentro para facilitar el uso
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (first)
                    searchTextField.setText("");
            }
        });
        // SEARCH BUTTON - manda a llenar la tabla con un select LIKE y busca usuarios con el String del search text field
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
                if (searchTextField.getText() == null || searchTextField.getText().equalsIgnoreCase("")) {
                    if (model.getRowCount() > 0) {
                        for (int i = model.getRowCount() - 1; i > -1; i--) {
                            model.removeRow(i);
                        }
                    }
                    users = dbconnection.getUsers(); // DB gets user
                    for (int i = 0; i < users.size(); i++) {
                        model.addRow(users.get(i).stringArray());
                    }
                } else {

                    if (model.getRowCount() > 0) {
                        for (int i = model.getRowCount() - 1; i > -1; i--) {
                            model.removeRow(i);
                        }
                    }
                    users = dbconnection.getUsers(searchTextField.getText()); // DB gets user
                    if (users.size() == 0)
                        users = dbconnection.getUsersLastName(searchTextField.getText());
                    for (int i = 0; i < users.size(); i++) {
                        model.addRow(users.get(i).stringArray());
                    }
                    // CLICK LISTNER PARA LA TABLA
                    tablaUsuarios.getSelectionModel().addListSelectionListener(event -> {
                        if (tablaUsuarios.getSelectedRow() > -1) {
                            if (!notReopen) {
                                notReopen = true;
                                AddUser newWindow = new AddUser(users.get(Integer.parseInt(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString())), dbconnection);
                                thisWindow.dispose();
                            }
                        }
                    });
                }
            }
        });
        // PRIMER VIEW
        view1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dbconnection.SELECT("SELECT COUNT(id) FROM public.\"Empleado\" WHERE public.\"Empleado\".\"id_Proyecto\" = 0");
                changeTable();
            }
        });
    }

    private void changeTable(){
        // TODO
    }

}
