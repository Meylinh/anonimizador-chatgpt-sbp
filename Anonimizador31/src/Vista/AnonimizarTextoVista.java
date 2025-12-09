package Vista;

import javax.swing.*;
import Modelo.Texto;
import Controlador.HistorialDAO;

public class AnonimizarTextoVista extends JFrame {

    public AnonimizarTextoVista(int userId) {
        setTitle("Anonimizar Texto");
        setSize(500,400);
        setLayout(null);
        setLocationRelativeTo(null);

        JTextArea txt = new JTextArea();
        JScrollPane sc1 = new JScrollPane(txt);
        sc1.setBounds(20,20,440,130);
        add(sc1);

        JButton btn = new JButton("Anonimizar");
        btn.setBounds(180,160,120,30);
        add(btn);

        JTextArea res = new JTextArea();
        JScrollPane sc2 = new JScrollPane(res);
        sc2.setBounds(20,200,440,130);
        add(sc2);

        btn.addActionListener(e -> {
            Texto t = new Texto(txt.getText());
            String anon = t.anonimizar();
            res.setText(anon);
            HistorialDAO.guardar(txt.getText(), anon, userId);
        });
    }
}
