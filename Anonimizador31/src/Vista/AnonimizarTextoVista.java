package Vista;

import javax.swing.*;
import Modelo.Texto;
import Controlador.HistorialDAO;
import java.time.LocalDateTime;

public class AnonimizarTextoVista extends JFrame {

    public AnonimizarTextoVista(int userId) {
        setTitle("Anonimizar Texto");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Área de texto original
        JTextArea txt = new JTextArea();
        JScrollPane sc1 = new JScrollPane(txt);
        sc1.setBounds(20, 20, 440, 130);
        add(sc1);

        // Botón Anonimizar
        JButton btn = new JButton("Anonimizar");
        btn.setBounds(180, 160, 120, 30);
        add(btn);

        // Área de resultado
        JTextArea res = new JTextArea();
        res.setEditable(false);
        JScrollPane sc2 = new JScrollPane(res);
        sc2.setBounds(20, 200, 440, 130);
        add(sc2);

        // Acción del botón
        btn.addActionListener(e -> {
            String textoOriginal = txt.getText();

            if (textoOriginal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El texto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Anonimizar
            Texto t = new Texto(textoOriginal);
            String anon = t.anonimizar();
            res.setText(anon);

            // Guardar en historial
            String fecha = LocalDateTime.now().toString();
            boolean exito = HistorialDAO.insertar(textoOriginal, anon, userId, fecha);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Texto anonimizado y guardado en historial correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el historial.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para probar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnonimizarTextoVista(1).setVisible(true);
        });
    }
}
