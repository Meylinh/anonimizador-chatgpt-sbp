package Vista;

import javax.swing.*;
import Modelo.Texto;
import Controlador.HistorialDAO;

public class AnonimizarVista extends JFrame {

    public AnonimizarVista(int usuarioId) {

        setTitle("Anonimizar Texto");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbl1 = new JLabel("Texto original:");
        lbl1.setBounds(20, 10, 300, 20);
        add(lbl1);

        JTextArea txtOriginal = new JTextArea();
        txtOriginal.setBounds(20, 35, 450, 120);
        add(txtOriginal);

        JButton btn = new JButton("Anonimizar");
        btn.setBounds(180, 165, 120, 30);
        add(btn);

        JLabel lbl2 = new JLabel("Texto anonimizado:");
        lbl2.setBounds(20, 200, 200, 20);
        add(lbl2);

        JTextArea txtAnon = new JTextArea();
        txtAnon.setBounds(20, 225, 450, 120);
        add(txtAnon);

        // Acción del botón
        btn.addActionListener(e -> {
            Texto t = new Texto(txtOriginal.getText());
            String resultado = t.anonimizar();
            txtAnon.setText(resultado);

            // Guardar en historial
            HistorialDAO.guardar(txtOriginal.getText(), resultado, usuarioId);
        });
    }
}
