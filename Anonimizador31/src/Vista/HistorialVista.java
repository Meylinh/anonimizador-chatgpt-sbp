package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import Controlador.HistorialDAO;

public class HistorialVista extends JFrame {

    public HistorialVista(int userId) {
        setTitle("Historial");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Columnas de la tabla
        String[] col = {"Original", "Anonimizado", "Fecha"};

        // Modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel(col, 0);

        // Obtener historial del usuario
        List<HistorialDAO.Historial> registros = HistorialDAO.listar();

        for (HistorialDAO.Historial h : registros) {
            if (h.getUserId() == userId) { // solo los del usuario actual
                Object[] fila = {h.getOriginal(), h.getAnonimizado(), h.getFecha()};
                modelo.addRow(fila);
            }
        }

        // Crear tabla y scroll
        JTable tabla = new JTable(modelo);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(20, 20, 550, 300);
        add(sc);
    }

    // Método para probar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HistorialVista(1).setVisible(true);
        });
    }
}
