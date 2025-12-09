package Controlador;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static int login(String user, String pass) {
        String sql = "SELECT id FROM usuarios WHERE usuario=? AND password=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
            return -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
