package com.mycompany.mavenproject5;

import com.mycompany.mavenproject5.presentacion.ProductoForm;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            ProductoForm form = new ProductoForm();
            form.setVisible(true);
        });
    }
}