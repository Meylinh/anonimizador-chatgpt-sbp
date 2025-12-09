package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuVista extends JFrame {

    int usuarioId;

    public MenuVista(int usuarioId) {
        this.usuarioId = usuarioId;

        setTitle("Menú Principal - Anonimizador SBP");
        setSize(360, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // centrar

        JLabel lbl = new JLabel("Seleccione una opción:");
        lbl.setBounds(90, 20, 200, 30);
        add(lbl);

        JButton btnAnon = new JButton("Anonimizar texto");
        btnAnon.setBounds(70, 70, 200, 40);
        add(btnAnon);

        JButton btnHistorial = new JButton("Ver historial");
        btnHistorial.setBounds(70, 130, 200, 40);
        add(btnHistorial);

        JButton btnAnonPDF = new JButton("Anonimizar PDF");
        btnAnonPDF.setBounds(70, 190, 200, 40);
        add(btnAnonPDF);

        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.setBounds(70, 250, 200, 40);
        add(btnSalir);

        // Abrir ventana de anonimización de texto
        btnAnon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnonimizarVista(usuarioId).setVisible(true);
            }
        });

        // Abrir ventana historial
        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistorialVista(usuarioId).setVisible(true);
            }
        });

        // Abrir ventana anonimizar PDF (usando clase anónima para compatibilidad)
        btnAnonPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Usa 'this' del outer a través de MenuVista.this si lo necesitas,
                // pero aquí usuarioId es campo de la instancia y accesible.
                new AnonimizarPDFVista(usuarioId).setVisible(true);
            }
        });

        // Cerrar sesión
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginVista().setVisible(true);
                dispose();
            }
        });
    }
}

