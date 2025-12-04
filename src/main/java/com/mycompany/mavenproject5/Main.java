package com.mycompany.mavenproject5;

import com.mycompany.mavenproject5.presentacion.AlumnoForm;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        // Establecer Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Iniciar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            AlumnoForm form = new AlumnoForm();
            form.setVisible(true);
        });
        
        System.out.println("Onichan chamo");
    }
}