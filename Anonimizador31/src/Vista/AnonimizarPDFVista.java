package Vista;

import javax.swing.*;
import Modelo.PDFService;
import Controlador.HistorialDAO;
import java.time.LocalDateTime;

public class AnonimizarPDFVista extends JFrame {

    public AnonimizarPDFVista(int userId) {
        setTitle("Anonimizar PDF");
        setSize(600,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton sel = new JButton("Seleccionar PDF");
        sel.setBounds(20,20,150,30);
        add(sel);

        JTextArea txt = new JTextArea();
        JScrollPane sc1 = new JScrollPane(txt);
        sc1.setBounds(20,60,550,150);
        add(sc1);

        JButton anon = new JButton("Anonimizar PDF");
        anon.setBounds(200,220,170,30);
        add(anon);

        JTextArea res = new JTextArea();
        res.setEditable(false);
        JScrollPane sc2 = new JScrollPane(res);
        sc2.setBounds(20,260,550,150);
        add(sc2);

        final String[] ruta = {""};

        sel.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ruta[0] = fc.getSelectedFile().getAbsolutePath();
                txt.setText(PDFService.leerPDF(ruta[0]));
            }
        });

        anon.addActionListener(e -> {
            String original = txt.getText();
            if(original.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay contenido para anonimizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String anonTxt = PDFService.anonimizarContenido(original);
            res.setText(anonTxt);

            String fecha = LocalDateTime.now().toString();
            HistorialDAO.insertar(original, anonTxt, userId, fecha);
            JOptionPane.showMessageDialog(this, "PDF anonimizado y guardado en historial.");
        });
    }
}
