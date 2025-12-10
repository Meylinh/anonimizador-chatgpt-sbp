package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Controlador.HistorialDAO;
import java.sql.ResultSet;

public class HistorialVista extends JFrame {

    public HistorialVista(int userId) {

        setTitle("Historial de Anonimizaciones");
        setSize(700,400);
        setLocationRelativeTo(null);

        String[] cols = {"Original", "Anonimizado", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try {
            ResultSet rs = HistorialDAO.listar(userId);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("texto_original"),
                        rs.getString("texto_anonimizado"),
                        rs.getString("fecha")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table));
    }
}
