package Vista;

import javax.swing.*;
import java.sql.ResultSet;
import Controlador.HistorialDAO;

public class HistorialVista extends JFrame {

    public HistorialVista(int userId) {
        setTitle("Historial");
        setSize(600,400);
        setLayout(null);
        setLocationRelativeTo(null);

        String[] col = {"Original", "Anonimizado", "Fecha"};
        String[][] data = new String[50][3];

        int i = 0;

        try {
            ResultSet rs = HistorialDAO.obtener(userId);
            while (rs.next()) {
                data[i][0] = rs.getString("original");
                data[i][1] = rs.getString("anonimizado");
                data[i][2] = rs.getString("fecha");
                i++;
            }
        } catch (Exception e) {}

        JTable tabla = new JTable(data, col);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(20,20,550,300);
        add(sc);
    }
}
