package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Controlador.HistorialDAO;
import java.util.List;

public class HistorialVista extends JFrame {

    public HistorialVista(int usuarioId) {

        setTitle("Historial de Anonimizaciones");
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] columnas = {"Original", "Anonimizado", "Fecha"};

        List<String[]> datos = HistorialDAO.obtenerHistorial(usuarioId);

        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (String[] fila : datos) {
            model.addRow(fila);
        }

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }
}
