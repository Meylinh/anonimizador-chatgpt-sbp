package Vista;

import Modelo.PdfService;
import Modelo.Texto;
import Controlador.HistorialDAO;

import javax.swing.*;
import java.io.File;

public class AnonimizarPDFVista extends JFrame {

    public AnonimizarPDFVista(int usuarioId) {

        setTitle("Anonimizar PDF");
        setSize(500, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbl = new JLabel("Seleccione un archivo PDF:");
        lbl.setBounds(20, 20, 300, 20);
        add(lbl);

        JButton btnSeleccionar = new JButton("Buscar PDF");
        btnSeleccionar.setBounds(150, 60, 150, 30);
        add(btnSeleccionar);

        btnSeleccionar.addActionListener(e -> {

            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF", "pdf"));

            int op = fc.showOpenDialog(this);

            if (op == JFileChooser.APPROVE_OPTION) {

                File pdf = fc.getSelectedFile();

                try {
                    // 1. EXTRAER TEXTO
                    String contenido = PdfService.extraerTextoPDF(pdf);

                    // 2. ANONIMIZAR
                    Texto t = new Texto(contenido);
                    String anon = t.anonimizar();

                    // 3. GUARDAR PDF NUEVO
                    File salida = new File(pdf.getParent(), "ANONIMIZADO_" + pdf.getName());
                    PdfService.crearPDFAnonimizado(anon, salida);

                    // 4. GUARDAR EN HISTORIAL
                    HistorialDAO.guardar(contenido, anon, usuarioId);

                    JOptionPane.showMessageDialog(this,
                            "PDF anonimizado creado:\n" + salida.getAbsolutePath());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        });
    }
}
