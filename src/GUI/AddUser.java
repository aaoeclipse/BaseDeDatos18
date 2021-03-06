package GUI;

import DataBase.CommandsSQL;
import DataBase.implementCommands;
import Objects.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AddUser {
    private JLabel imageLabel;
    private JTextField Nombre;
    private JTextField Direccion;
    private JTextField Apellido;
    private JTextField Salario;
    private JTable tableColumnas;
    private JButton addButton;
    private JFrame thisWindow;
    public JPanel addUserView;
    private JTextField ID;
    private JTextField FechaContratacion;
    private JTextField Horario;
    private JTextField Departamento;
    private JTextField foto;
    private JTextField Nacimiento;
    private JButton cancelButton;
    private JComboBox comboPuesto;
    private JComboBox comboTecnologia;
    private JComboBox comboProyecto;
    private JScrollPane scrollColumnas;
    private JButton nuevaColumnaButton;
    private JButton tweetsButton;
    private JButton seleccionarImagenButton;
    private TableColumn columns;
    private TableColumn values;
    private User user;
    private CommandsSQL database;
    // OBJECTS
    private ArrayList<ColumnasExtras> columnasExtras;
    private ArrayList<Proyecto> proyectos;
    private ArrayList<Puestos> puestos;
    private ArrayList<Tecnologia> tecnologias;
    ArrayList<NombreConTipo> nombreCol;
    private boolean isUserCreated;
    JFileChooser fc = null;

    CommandsSQL dbconnection = new implementCommands();

    public AddUser(CommandsSQL dbconnection) {
        isUserCreated = false;
        this.dbconnection = dbconnection;
        createComponents();
        ID.setText("" + dbconnection.getNewID());
        user = new User();
        user.setIdEmpleado(Integer.parseInt(ID.getText()));
        //COMBOS
        puestos = dbconnection.getPuestos();
        for (Puestos p : puestos) {
            comboPuesto.addItem(new ComboItem(p.getNombre(), "" + p.getIdPuesto()));
        }
        tecnologias = dbconnection.getTecnologia();
        for (Tecnologia t : tecnologias) {
            comboTecnologia.addItem(new ComboItem(t.getNombre(), "" + t.getIdTecnologia()));
        }
        proyectos = dbconnection.getProyectos();
        for (Proyecto t : proyectos) {
            comboProyecto.addItem(new ComboItem(t.getProyectoName(), "" + t.getProyectoID()));
        }
        nuevaColumnaButton.disable();
        // Add listner en nueva columna
        nuevaColumnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevaColumnaButton.disable();
            }
        });
    }

    public AddUser(User user, CommandsSQL dbconnection) {
        isUserCreated = true;
        this.dbconnection = dbconnection;
        createComponents();
        isUserCreated = true;
        // user selected, inserting user info
        this.user = user;
        //COMBOS
        puestos = dbconnection.getPuestos();
        ComboItem item = new ComboItem();
        int i = 0;
        int index = 0;
        for (Puestos p : puestos) {
            comboPuesto.addItem(new ComboItem(p.getNombre(), "" + p.getIdPuesto()));
            if (p.getIdPuesto() == user.getIdPuesto())
                index = i;
            i++;
        }
        comboPuesto.setSelectedIndex(index);
        i = 0;
        index = 0;
        tecnologias = dbconnection.getTecnologia();
        for (Tecnologia t : tecnologias) {
            comboTecnologia.addItem(new ComboItem(t.getNombre(), "" + t.getIdTecnologia()));
            if (t.getIdTecnologia() == user.getId_tecnologia())
                index = i;
            i++;
        }
        comboTecnologia.setSelectedIndex(index);
        i = 0;
        index = 0;
        proyectos = dbconnection.getProyectos();
        for (Proyecto t : proyectos) {
            comboProyecto.addItem(new ComboItem(t.getProyectoName(), "" + t.getProyectoID()));
            if (t.getProyectoID() == user.getId_Proyecto())
                index = i;
            i++;
        }
        comboProyecto.setSelectedIndex(index);
        // SETTING users text
        ID.setText("" + user.getIdEmpleado());
        Nombre.setText(user.getNombre());
        Apellido.setText(user.getApellido());
        Direccion.setText(user.getDireccion());
        Salario.setText("" + user.getSalario());
        FechaContratacion.setText(user.getFecha_contratacion());
        Nacimiento.setText(user.getFecha_nacimiento());
        foto.setText(user.getFoto_dir());
        Horario.setText(user.getHorrario());
        Departamento.setText(user.getDepartamento());
        creatTable();

        // Add listner en nueva columna
        nuevaColumnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newColumn newWindow = new newColumn(dbconnection, user);
                thisWindow.dispose();
            }
        });
        tweetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TwitterView twitterView = new TwitterView();
                // thisWindow.dispose();
            }
        });
    }

    public void createComponents() {
        // WINDOW
        thisWindow = new JFrame("addUser");
        thisWindow.setContentPane(addUserView);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setVisible(true);
        // ID
        ID.setEditable(false);
        // Listeners
        cancelButton.addActionListener(new returnToTable());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User newUser = new User(Integer.parseInt(ID.getText()),
                        Nombre.getText(),
                        Apellido.getText(),
                        getSalario(),
                        Direccion.getText(),
                        Horario.getText(),
                        Departamento.getText(),
                        foto.getText(),
                        Nacimiento.getText(),
                        FechaContratacion.getText(),
                        getPuesto(),
                        getTecnologia(),
                        getProyecto());

                //if(dbconnection.alterColumnExtras)
                if (dbconnection.addEmpleado(newUser)) {
                    DefaultTableModel model = (DefaultTableModel) tableColumnas.getModel();
                    String value = "null";
                    if (isUserCreated)
                        if (model.getRowCount() > 0)
                            for (int i = 0; i < model.getRowCount(); i++) {
                                value = "null";
                                if (!model.getValueAt(i, 1).toString().equalsIgnoreCase("-"))
                                    value = model.getValueAt(i, 1).toString();
                                dbconnection.alterColumnExtras(columnasExtras.get(i).getIdColumna(), columnasExtras.get(i).getIdValor(), model.getValueAt(i, 0).toString(), value);
                            }
                    ShowTable newWindow = new ShowTable(dbconnection);
                    thisWindow.dispose();
                }
            }
        });
        ImageIcon icon = null;
        try {
            icon = createImageIcon(user.getFoto_dir(),
                    "imagen");
        } catch (Exception e){
            icon = createImageIcon("../Resources/profile.jpeg",
                    "default");
            if (icon == null){
                System.err.println("No image");
            }
        }
        imageLabel.setIcon(icon);

    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            return null;
        }
    }

    private void creatTable() {
        // Tabla
        DefaultTableModel model = (DefaultTableModel) tableColumnas.getModel();
        tableColumnas.setShowGrid(true);
        // Columnas
        String[] nombreColumnas = {
                "Columnas",
                "Valor"
        };
        for (String nombreColumna : nombreColumnas) model.addColumn(nombreColumna);
        // ROWS
        String[] newArray;
        nombreCol = dbconnection.getColumnasExtras();
        columnasExtras = dbconnection.getColumnasConValor(user.getIdEmpleado());
        for (int i = 0; i < nombreCol.size(); i++) {
            newArray = new String[2];
            newArray[0] = nombreCol.get(i).getNombre();
            newArray[1] = "-";
            for (int x = 0; x < columnasExtras.size(); x++) {
                if (nombreCol.get(i).getNombre().equalsIgnoreCase(columnasExtras.get(x).getNombre()))
                    newArray[1] = "" + columnasExtras.get(x).getValue();

            }
            // AGREGAR VALOR SI NO EXISTE
            if (isUserCreated) {
                if (newArray[1].equalsIgnoreCase("-")) {
                    dbconnection.addColumnasExtrasSinCambiarElTipo(user.getIdEmpleado(), nombreCol.get(i).getId(), "null");
                    newArray[1] = "null";
                }
            }
            model.addRow(newArray);

        }
        columnasExtras = dbconnection.getColumnasConValor(user.getIdEmpleado());

    }

    private class returnToTable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ShowTable newWindow = new ShowTable(dbconnection);
            thisWindow.dispose();
        }
    }

    private int getId() {
        if (ID.getText() != null && !ID.getText().equalsIgnoreCase("")) {
            return Integer.parseInt(ID.getText());
        } else {
            return 0;
        }
    }

    private int getPuesto() {
        for (Puestos p : puestos) {
            if (p.getNombre().equalsIgnoreCase(Objects.requireNonNull(comboPuesto.getSelectedItem()).toString())) {
                return p.getIdPuesto();
            }
        }
        return 0;
    }

    private int getTecnologia() {
        for (Tecnologia t : tecnologias) {
            if (t.getNombre().equalsIgnoreCase(comboTecnologia.getSelectedItem().toString())) {
                return t.getIdTecnologia();
            }
        }
        return 0;
    }

    private int getProyecto() {
        for (Proyecto p : proyectos) {
            if (p.getProyectoName().equalsIgnoreCase(comboProyecto.getSelectedItem().toString())) {
                return p.getProyectoID();
            }
        }
        return 0;
    }

    private float getSalario() {
        if (Salario.getText() != null)
            return Float.parseFloat(Salario.getText());
        else
            return 0.0f;
    }
}
