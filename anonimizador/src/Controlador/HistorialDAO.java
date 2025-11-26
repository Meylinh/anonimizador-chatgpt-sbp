package Controlador;

import BaseDatos.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    // Guardar en historial
    public static void guardar(String original, String anon, int usuarioId) {
        String sql = "INSERT INTO historial (prompt_original, prompt_anonimizado, fecha, usuario_id) VALUES (?, ?, datetime('now'), ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, original);
            ps.setString(2, anon);
            ps.setInt(3, usuarioId);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error guardando historial: " + e.getMessage());
        }
    }

    // Obtener historial por usuario
    public static List<String[]> obtenerHistorial(int usuarioId) {
        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT prompt_original, prompt_anonimizado, fecha FROM historial WHERE usuario_id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("prompt_original"),
                        rs.getString("prompt_anonimizado"),
                        rs.getString("fecha")
                });
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo historial: " + e.getMessage());
        }

        return lista;
    }
}
