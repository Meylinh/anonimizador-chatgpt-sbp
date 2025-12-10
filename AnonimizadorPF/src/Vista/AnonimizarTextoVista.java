package Vista;

import javax.swing.*;
import Controlador.HistorialDAO;
import Modelo.Texto;

public class AnonimizarTextoVista extends JFrame {

    public AnonimizarTextoVista(int userId) {
        setTitle("Anonimizar Texto");
        setSize(600,450);
        setLayout(null);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MenuVista(userId).setVisible(true);
                dispose();
            }
        });


        JTextArea txtOriginal = new JTextArea();
        JScrollPane sc1 = new JScrollPane(txtOriginal);
        sc1.setBounds(20,20,550,140);
        add(sc1);

        JButton btnAnon = new JButton("Anonimizar");
        btnAnon.setBounds(230,170,130,30);
        add(btnAnon);

        JTextArea txtAnon = new JTextArea();
        JScrollPane sc2 = new JScrollPane(txtAnon);
        sc2.setBounds(20,210,550,160);
        add(sc2);

        btnAnon.addActionListener(e -> {
            Texto t = new Texto(txtOriginal.getText());
            String anon = t.anonimizar();
            txtAnon.setText(anon);
            HistorialDAO.guardar(txtOriginal.getText(), anon, userId);
        });
    }
}
