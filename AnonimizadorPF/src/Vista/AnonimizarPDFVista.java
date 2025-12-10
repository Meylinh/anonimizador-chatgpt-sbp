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
        add(new JScrollPane(txt)).setBounds(20,60,550,150);

        JButton anon = new JButton("Anonimizar");
        anon.setBounds(200,220,150,30);
        add(anon);

        JButton hist = new JButton("Historial");
        hist.setBounds(370,220,150,30);
        add(hist);

        JTextArea res = new JTextArea();
        add(new JScrollPane(res)).setBounds(20,260,550,180);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MenuVista(userId).setVisible(true);
                dispose();
            }
        });


        final String[] ruta = {""};

        sel.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ruta[0] = fc.getSelectedFile().getAbsolutePath();
                txt.setText(PDFService.leerPDF(ruta[0]));
            }
        });

        anon.addActionListener(e -> {
            Texto t = new Texto(txt.getText());
            String anonTxt = t.anonimizar();
            res.setText(anonTxt);
            HistorialDAO.guardar(txt.getText(), anonTxt, userId);
        });

        hist.addActionListener(e -> {
            new HistorialVista(userId).setVisible(true);
        });
    }
}
