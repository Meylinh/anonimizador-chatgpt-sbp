package Controlador;

import BaseDatos.ConexionBD;
import java.sql.*;
import java.time.LocalDateTime;

public class HistorialDAO {

    public static void guardar(String original, String anon, int userId) {

        String sql = """
            INSERT INTO historial (texto_original, texto_anonimizado, fecha, usuario_id)
            VALUES (?,?,?,?)
        """;

        try (Connection c = ConexionBD.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, original);
            ps.setString(2, anon);
            ps.setString(3, LocalDateTime.now().toString());
            ps.setInt(4, userId);

            ps.executeUpdate(); // ← ESTO guarda

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet listar(int userId) throws SQLException {
        String sql = """
            SELECT texto_original, texto_anonimizado, fecha
            FROM historial
            WHERE usuario_id=?
            ORDER BY id DESC
        """;

        Connection c = ConexionBD.conectar();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps.executeQuery();
    }
}
