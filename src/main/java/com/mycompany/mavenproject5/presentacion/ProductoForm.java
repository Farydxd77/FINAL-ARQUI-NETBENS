package com.mycompany.mavenproject5.presentacion;

import com.mycompany.mavenproject5.datos.Producto;
import com.mycompany.mavenproject5.datos.TipoProducto;
import com.mycompany.mavenproject5.negocio.ProductoService;
import com.mycompany.mavenproject5.negocio.TipoProductoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class ProductoForm extends JFrame {
    
    private ProductoService productoService;
    private TipoProductoService tipoProductoService;
    private JTextField txtNombre, txtPrecio, txtDescripcionTipo;
    private JComboBox<TipoProducto> cmbTipoProducto;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    
    public ProductoForm() {
        productoService = new ProductoService();
        tipoProductoService = new TipoProductoService();
        
        setTitle("FARMACIA MEDICAM - Gestión de Productos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // --- Panel Tipo Producto ---
        JPanel panelTipo = new JPanel(new FlowLayout());
        panelTipo.setBorder(BorderFactory.createTitledBorder("Tipo de Producto"));
        txtDescripcionTipo = new JTextField(12);
        JButton btnGuardarTipo = new JButton("Guardar Tipo");
        panelTipo.add(new JLabel("Descripción:"));
        panelTipo.add(txtDescripcionTipo);
        panelTipo.add(btnGuardarTipo);
        
        // --- Panel Producto ---
        JPanel panelProducto = new JPanel(new FlowLayout());
        panelProducto.setBorder(BorderFactory.createTitledBorder("Producto"));
        txtNombre = new JTextField(10);
        txtPrecio = new JTextField(8);
        cmbTipoProducto = new JComboBox<>();
        JButton btnGuardarProducto = new JButton("Guardar");
        panelProducto.add(new JLabel("Nombre:"));
        panelProducto.add(txtNombre);
        panelProducto.add(new JLabel("Precio:"));
        panelProducto.add(txtPrecio);
        panelProducto.add(new JLabel("Tipo:"));
        panelProducto.add(cmbTipoProducto);
        panelProducto.add(btnGuardarProducto);
        
        panelSuperior.add(panelTipo);
        panelSuperior.add(panelProducto);
        add(panelSuperior, BorderLayout.NORTH);
        
        // --- Tabla ---
        String[] columnas = {"Código", "Nombre", "Precio", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        add(scroll, BorderLayout.CENTER);
        
        // --- Eventos ---
        btnGuardarTipo.addActionListener(e -> {
            try {
                tipoProductoService.crearTipoProducto(new TipoProducto(txtDescripcionTipo.getText().trim()));
                JOptionPane.showMessageDialog(this, "Tipo guardado!");
                txtDescripcionTipo.setText("");
                cargarTipos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        
        btnGuardarProducto.addActionListener(e -> {
            try {
                if (cmbTipoProducto.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione un tipo");
                    return;
                }
                Producto p = new Producto(
                    txtNombre.getText().trim(),
                    new BigDecimal(txtPrecio.getText().trim()),
                    (TipoProducto) cmbTipoProducto.getSelectedItem()
                );
                productoService.crearProducto(p);
                JOptionPane.showMessageDialog(this, "Producto guardado!");
                txtNombre.setText("");
                txtPrecio.setText("");
                cargarProductos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        
        cargarTipos();
        cargarProductos();
    }
    
    private void cargarTipos() {
        cmbTipoProducto.removeAllItems();
        for (TipoProducto t : tipoProductoService.getTipoProductos()) {
            cmbTipoProducto.addItem(t);
        }
    }
    
    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        for (Producto p : productoService.getProductos()) {
            modeloTabla.addRow(new Object[]{
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getTipoProducto().getDescripcion()
            });
        }
    }
}