package Controlador;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Login, devuelve id del usuario o -1 si no coincide
    public static int login(String user, String pass) {
        String sql = "SELECT id FROM usuarios WHERE usuario = ? AND password = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
            return -1;
        }
    }

    // Registrar usuario nuevo
    public static boolean registrar(String user, String pass) {
        String sql = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}

