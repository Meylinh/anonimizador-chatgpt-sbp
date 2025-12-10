package Vista;

import javax.swing.*;

public class MenuVista extends JFrame {

    public MenuVista(int userId) {
        setTitle("Menú Principal");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnTexto = new JButton("Anonimizar Texto");
        btnTexto.setBounds(100,30,200,30);
        add(btnTexto);

        JButton btnPDF = new JButton("Anonimizar PDF");
        btnPDF.setBounds(100,80,200,30);
        add(btnPDF);

        JButton btnHistorial = new JButton("Ver Historial");
        btnHistorial.setBounds(100,130,200,30);
        add(btnHistorial);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(100,180,200,30);
        add(btnSalir);

        // Acciones
        btnTexto.addActionListener(e -> {
            new AnonimizarTextoVista(userId).setVisible(true);
            dispose();
        });

        btnPDF.addActionListener(e -> {
            new AnonimizarPDFVista(userId).setVisible(true);
            dispose();
        });

        btnHistorial.addActionListener(e -> {
            new HistorialVista(userId).setVisible(true);
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
}
