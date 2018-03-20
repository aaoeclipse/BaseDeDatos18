package GUI;

import Objects.User;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class AddUser {
    private JLabel image;
    private JTextField Nombre;
    private JTextField Direccion;
    private JTextField Apellido;
    private JTextField Salario;
    private JTable table1;
    private JButton addButton;
    public JPanel addUserView;
    private JTextField ID;
    private JTextField FechaContratacion;
    private JTextField Horario;
    private JTextField Departamento;
    private JTextField foto;
    private JTextField Nacimiento;
    private TableColumn columns;
    private TableColumn values;
    private User user;

    private String[] Columns;
    private int idColumns;

    public AddUser() {
        // no user serlected THEREFOR create new user
        columns=new TableColumn(0);
        columns.setHeaderValue("Columns");
        values=new TableColumn(0);
        values.setHeaderValue("Columns");
        table1.addColumn(columns);
        table1.addColumn(values);
    }
    public AddUser(User user){
        // user selected, inserting user info
        this.user = user;
        ID.setText(""+user.getIdUser());
        Nombre.setText(user.getNombre());
        Apellido.setText(user.getApellido());
        Direccion.setText(user.getDireccion());
        Salario.setText(""+user.getSalario());
        FechaContratacion.setText(user.getFecha_contratacion());
        Nacimiento.setText(user.getFecha_nacimiento());
        foto.setText(user.getFoto_dir());
        Horario.setText(user.getHorrario());
        Departamento.setText(user.getDepartamento());
        // Llamar columnas extras
        columns=new TableColumn();
        columns.setHeaderValue("Columnas");
        values=new TableColumn();
        values.setHeaderValue("Valor");
        table1.addColumn(columns);
        table1.addColumn(values);
    }

    private void fillColumns(){
        // SELECT * FROM Columns;

    }
    private void fillValues(){
        // SELECT value FROM valueCol vc WHERE vc.id_user = userId;
    }
}
