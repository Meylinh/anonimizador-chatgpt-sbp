package Controlador;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistorialDAO {

    public static void guardar(String original, String anon, int userId) {
        String sql = "INSERT INTO historial(original,anonimizado,fecha,usuario_id) VALUES(?,?,datetime('now'),?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, original);
            ps.setString(2, anon);
            ps.setInt(3, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet obtener(int userId) {
        try {
            Connection conn = ConexionBD.conectar();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM historial WHERE usuario_id=? ORDER BY id DESC"
            );
            ps.setInt(1, userId);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
}
