package Vista;

import javax.swing.*;
import Modelo.PDFService;
import Modelo.Texto;
import Controlador.HistorialDAO;

public class AnonimizarPDFVista extends JFrame {

    public AnonimizarPDFVista(int userId) {
        setTitle("Anonimizar PDF");
        setSize(600,500);
        setLayout(null);
        setLocationRelativeTo(null);

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
        JScrollPane sc2 = new JScrollPane(res);
        sc2.setBounds(20,260,550,150);
        add(sc2);

        final String[] ruta = {""};

        sel.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ruta[0] = fc.getSelectedFile().getAbsolutePath();
                String texto = PDFService.leerPDF(ruta[0]);
                txt.setText(texto);
            }
        });

        anon.addActionListener(e -> {
            Texto t = new Texto(txt.getText());
            String anonTxt = t.anonimizar();
            res.setText(anonTxt);
            HistorialDAO.guardar(txt.getText(), anonTxt, userId);
        });
    }
}
