package GUI;

import DataBase.CommandsSQL;
import DataBase.implementCommands;
import Objects.User;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUser {
    private JLabel image;
    private JTextField Nombre;
    private JTextField Direccion;
    private JTextField Apellido;
    private JTextField Salario;
    private JTable table1;
    private JButton addButton;
    public JFrame thisWindow;
    public JPanel addUserView;
    private JTextField ID;
    private JTextField FechaContratacion;
    private JTextField Horario;
    private JTextField Departamento;
    private JTextField foto;
    private JTextField Nacimiento;
    private JButton cancelButton;
    private JButton verProyectos;
    private TableColumn columns;
    private TableColumn values;
    private User user;
    private CommandsSQL database;
    private String[] Columns;
    private int idColumns;

    CommandsSQL dbconnection = new implementCommands();

    public AddUser(CommandsSQL dbconnection) {
        createComponents();
        this.dbconnection = dbconnection;
    }
    public AddUser(User user, CommandsSQL dbconnection){
        createComponents();
        this.dbconnection = dbconnection;
        // user selected, inserting user info
        this.user = user;
        ID.setText(""+user.getIdEmpleado());
        Nombre.setText(user.getNombre());
        Apellido.setText(user.getApellido());
        Direccion.setText(user.getDireccion());
        Salario.setText(""+user.getSalario());
        FechaContratacion.setText(user.getFecha_contratacion());
        Nacimiento.setText(user.getFecha_nacimiento());
        foto.setText(user.getFoto_dir());
        Horario.setText(user.getHorrario());
        Departamento.setText(user.getDepartamento());
    }

    public void createComponents(){
        // WINDOW
        thisWindow = new JFrame("addUser");
        thisWindow.setContentPane(addUserView);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setVisible(true);
        // Columnas
        columns=new TableColumn(0);
        columns.setHeaderValue("Columns");
        values=new TableColumn(0);
        values.setHeaderValue("Columns");
        table1.addColumn(columns);
        table1.addColumn(values);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        cancelButton.addActionListener(new returnToTable());
    }


    private class returnToTable implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            ShowTable newWindow = new ShowTable(dbconnection);
            thisWindow.dispose();

        }
    }

    private void closeWindow(){

    }



    private void fillColumns(){
        // SELECT * FROM Columns;

    }
    private void fillValues(){
        // SELECT value FROM valueCol vc WHERE vc.id_user = userId;
    }
}
