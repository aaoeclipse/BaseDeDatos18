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
    private JButton cumpleañerosButton;
    private JButton listarUltimosEmpleadosContratadosButton;
    public JPanel TableForm;
    private JLabel spacer;
    private JButton searchButton;
    private JTextArea queryResults;
    private JButton numeroDeEmpleadosEnButton1;
    private JButton numeroDeEmpleadosEnButton;
    private JTextField textField2;
    private JTextField textFieldTecnologiaResumen;
    private JButton promedioDeSalarioButton;
    private JTextField textField3;
    private JButton listarEmpleadosButton;
    private JTextField textField4;
    private JButton listarEmpleadosConMayorButton;
    private JTextField textField5;
    private JButton listarEmpleadosConHorarioButton;
    private JTextField textField6;
    private JButton botonNumeroEmpleado;
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
        thisWindow.setSize(1550,800);
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
        for (int i = 0; i < nombreColumnas.length;i++)
            model.addColumn(nombreColumnas[i]);
        users = dbconnection.getUsers();
        for (int i = 0; i < users.size();i++) {
            model.addRow(users.get(i).stringArray());
        }
        tablaUsuarios.getSelectionModel().addListSelectionListener(event -> {
            if (tablaUsuarios.getSelectedRow() > -1) {
                if (!notReopen){
                    notReopen = true;
                    AddUser newWindow = new AddUser(users.get(Integer.parseInt(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString())),dbconnection);
                    thisWindow.dispose();
                }
            }
        });
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
        botonNumeroEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
