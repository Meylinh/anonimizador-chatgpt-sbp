package Controlador;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    public static class Historial {
        private int id;
        private String original;
        private String anonimizado;
        private int userId;
        private String fecha;

        public Historial(int id, String original, String anonimizado, int userId, String fecha) {
            this.id = id;
            this.original = original;
            this.anonimizado = anonimizado;
            this.userId = userId;
            this.fecha = fecha;
        }

        public int getId() { return id; }
        public String getOriginal() { return original; }
        public String getAnonimizado() { return anonimizado; }
        public int getUserId() { return userId; }
        public String getFecha() { return fecha; }

        @Override
        public String toString() {
            return "Historial [id=" + id + ", original=" + original + ", anonimizado=" + anonimizado
                    + ", userId=" + userId + ", fecha=" + fecha + "]";
        }
    }

    public static boolean insertar(String original, String anon, int userId, String fecha) {
        String sql = "INSERT INTO historial (original, anonimizado, user_id, fecha) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, original);
            ps.setString(2, anon);
            ps.setInt(3, userId);
            ps.setString(4, fecha);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar historial: " + e.getMessage());
            return false;
        }
    }

    public static List<Historial> listar() {
        List<Historial> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Historial h = new Historial(
                        rs.getInt("id"),
                        rs.getString("original"),
                        rs.getString("anonimizado"),
                        rs.getInt("user_id"),
                        rs.getString("fecha")
                );
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar historial: " + e.getMessage());
        }
        return lista;
    }
}

