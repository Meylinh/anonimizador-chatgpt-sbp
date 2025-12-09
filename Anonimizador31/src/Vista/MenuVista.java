package Vista;

import javax.swing.*;

public class MenuVista extends JFrame {

    public MenuVista(int userId) {
        setTitle("Menú Principal");
        setSize(300,300);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton t = new JButton("Anonimizar Texto");
        t.setBounds(50,40,180,30);
        add(t);

        JButton p = new JButton("Anonimizar PDF");
        p.setBounds(50,90,180,30);
        add(p);

        JButton h = new JButton("Ver Historial");
        h.setBounds(50,140,180,30);
        add(h);

        t.addActionListener(e -> new AnonimizarTextoVista(userId).setVisible(true));
        p.addActionListener(e -> new AnonimizarPDFVista(userId).setVisible(true));
        h.addActionListener(e -> new HistorialVista(userId).setVisible(true));
    }
}
