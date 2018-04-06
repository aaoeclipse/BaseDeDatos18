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
    private JTextField textFieldPuestoResumen;
    private JTextField textFieldTecnologiaResumen;
    private JButton promedioDeSalarioButton;
    private JTextField textFieldPuestoSalarioResumen;
    private JButton listarEmpleadosButton;
    private JTextField textFieldTecnologiaDetallado;
    private JButton listarEmpleadosConMayorButton;
    private JTextField textFieldSalarioMayor;
    private JButton listarEmpleadosConHorarioButton;
    private JTextField textFieldHorario;
    private JButton botonNumeroEmpleado;
    private JButton btnSalarioMasAlto;
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
                    AddUser newWindow = new AddUser(users.get(tablaUsuarios.getSelectedRow()),dbconnection);
                    thisWindow.dispose();
                }
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
                    //if (users.size() == 0)
                    // users = dbconnection.getUsersLastName(searchTextField.getText());
                    for (int i = 0; i < users.size(); i++) {
                        model.addRow(users.get(i).stringArray());
                    }
                    // CLICK LISTNER PARA LA TABLA
                    tablaUsuarios.getSelectionModel().addListSelectionListener(event -> {
                        if (tablaUsuarios.getSelectedRow() > -1) {
                            if (!notReopen) {
                                notReopen = true;
                                AddUser newWindow = new AddUser(users.get(tablaUsuarios.getSelectedRow()),dbconnection);
                                thisWindow.dispose();
                            }
                        }
                    });
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
                dbconnection.SELECT("SELECT COUNT(id) FROM public.\"Empleado\" ");
            }
        });
        numeroDeEmpleadosEnButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT COUNT(*) FROM public.\"Empleado\" \n" +
                        "INNER JOIN public.\"Tecnologia\" ON (public.\"Tecnologia\".\"id\" = public.\"Empleado\".\"id_tecnologia\") \n" +
                        "WHERE public.\"Tecnologia\".\"nombre\" LIKE '%"+textFieldTecnologiaResumen.getText()+"%'");
            }
        });
        numeroDeEmpleadosEnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT COUNT(*) FROM public.\"Empleado\" \n" +
                        "INNER JOIN public.\"Puesto\" ON (public.\"Puesto\".\"id\" = public.\"Empleado\".\"id_puesto\") \n" +
                        "WHERE public.\"Puesto\".\"nombre\" LIKE '%"+textFieldPuestoResumen.getText()+"%' ");
            }
        });
        promedioDeSalarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT AVG(public.\"Empleado\".\"salario\") FROM public.\"Empleado\" \n" +
                        "INNER JOIN public.\"Puesto\" ON (public.\"Puesto\".\"id\" = public.\"Empleado\".\"id_puesto\") \n" +
                        "WHERE public.\"Puesto\".\"nombre\" LIKE '%"+textFieldPuestoSalarioResumen.getText()+"%'");
            }
        });
        btnSalarioMasAlto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT public.\"Empleado\".\"salario\" FROM public.\"Empleado\" ORDER BY public.\"Empleado\".\"salario\" DESC LIMIT 1");
            }
        });
        cumpleañerosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT * FROM public.\"Empleado\" " +
                        "WHERE date_part('month', public.\"Empleado\".\"fecha_nacimiento\") = date_part('month', current_timestamp)");
            }
        });
        listarEmpleadosConMayorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT * FROM public.\"Empleado\" WHERE public.\"Empleado\".\"salario\" > "+textFieldSalarioMayor.getText());
            }
        });
        listarEmpleadosConHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dbconnection.SELECT("");
                dbconnection.SELECT("SELECT * FROM public.\"Empleado\" " +
                        "WHERE public.\"Empleado\".\"horario\" LIKE '%"+textFieldHorario.getText()+"%'");
            }
        });
        listarUltimosEmpleadosContratadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT * FROM public.\"Empleado\"\n" +
                        "                ORDER BY public.\"Empleado\".\"fecha_contratacion\" DESC LIMIT 10");
            }
        });
        listarEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbconnection.SELECT("SELECT * FROM public.\"Empleado\" WHERE public.\"Empleado\".\"horario\" LIKE '%"+textFieldTecnologiaDetallado.getText()+"%'");
            }
        });
    }
}