package Vista;

import javax.swing.*;

public class MenuVista extends JFrame {

    int usuarioId;

    public MenuVista(int usuarioId) {
        this.usuarioId = usuarioId;

        setTitle("Menú Principal - Anonimador SBP");
        setSize(350, 300);
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

        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.setBounds(70, 190, 200, 40);
        add(btnSalir);

        // Abrir ventana de anonimización
        btnAnon.addActionListener(e -> {
            new AnonimizarVista(usuarioId).setVisible(true);
        });

        // Abrir ventana historial
        btnHistorial.addActionListener(e -> {
            new HistorialVista(usuarioId).setVisible(true);
        });

        // Cerrar sesión
        btnSalir.addActionListener(e -> {
            new LoginVista().setVisible(true);
            dispose();
        });
    }
}
