package Controlador;

import BaseDatos.ConexionBD;
import java.sql.*;

public class UsuarioDAO {

    public static int login(String user, String pass) {

        String sql = "SELECT id FROM usuarios WHERE usuario=? AND password=?";

        try (Connection c = ConexionBD.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

