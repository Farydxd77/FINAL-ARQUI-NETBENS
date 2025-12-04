package com.mycompany.mavenproject5.presentacion;

import com.mycompany.mavenproject5.datos.Alumno;
import com.mycompany.mavenproject5.negocio.AlumnoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlumnoForm extends JFrame {
    
    private AlumnoService alumnoService;
    
    // Componentes del formulario
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtMatricula;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    
    private JTable tablaAlumnos;
    private DefaultTableModel modeloTabla;
    
    public AlumnoForm() {
        alumnoService = new AlumnoService();
        initComponents();
        cargarAlumnos();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestión de Alumnos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel del formulario
        JPanel panelFormulario = crearPanelFormulario();
        add(panelFormulario, BorderLayout.NORTH);
        
        // Panel de la tabla
        JPanel panelTabla = crearPanelTabla();
        add(panelTabla, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Alumno"));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createTitledBorder("Datos del Alumno")
        ));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtMatricula = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Matrícula:"));
        panel.add(txtMatricula);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Alumnos"));
        
        String[] columnas = {"ID", "Nombre", "Apellido", "Matrícula", "Email", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaAlumnos = new JTable(modeloTabla);
        tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaAlumnos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarAlumnoSeleccionado();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        
        btnGuardar.addActionListener(e -> guardarAlumno());
        btnActualizar.addActionListener(e -> actualizarAlumno());
        btnEliminar.addActionListener(e -> eliminarAlumno());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        
        panel.add(btnGuardar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    private void guardarAlumno() {
        try {
            Alumno alumno = new Alumno(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtMatricula.getText().trim(),
                txtEmail.getText().trim(),
                txtTelefono.getText().trim()
            );
            
            alumnoService.crearAlumno(alumno);
            JOptionPane.showMessageDialog(this, "Alumno guardado exitosamente");
            limpiarFormulario();
            cargarAlumnos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarAlumno() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un alumno de la tabla");
                return;
            }
            
            Alumno alumno = new Alumno(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtMatricula.getText().trim(),
                txtEmail.getText().trim(),
                txtTelefono.getText().trim()
            );
            alumno.setId(Long.parseLong(txtId.getText()));
            
            alumnoService.actualizarAlumno(alumno);
            JOptionPane.showMessageDialog(this, "Alumno actualizado exitosamente");
            limpiarFormulario();
            cargarAlumnos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarAlumno() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un alumno de la tabla");
                return;
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar este alumno?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                Long id = Long.parseLong(txtId.getText());
                alumnoService.eliminarAlumno(id);
                JOptionPane.showMessageDialog(this, "Alumno eliminado exitosamente");
                limpiarFormulario();
                cargarAlumnos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarAlumnos() {
        modeloTabla.setRowCount(0);
        List<Alumno> alumnos = alumnoService.listarTodosLosAlumnos();
        
        for (Alumno alumno : alumnos) {
            Object[] fila = {
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getMatricula(),
                alumno.getEmail(),
                alumno.getTelefono()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void cargarAlumnoSeleccionado() {
        int filaSeleccionada = tablaAlumnos.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtId.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
            txtApellido.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
            txtMatricula.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
            
            Object email = modeloTabla.getValueAt(filaSeleccionada, 4);
            txtEmail.setText(email != null ? email.toString() : "");
            
            Object telefono = modeloTabla.getValueAt(filaSeleccionada, 5);
            txtTelefono.setText(telefono != null ? telefono.toString() : "");
        }
    }
    
    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtMatricula.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        tablaAlumnos.clearSelection();
    }
}