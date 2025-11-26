package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertarUsuario {

    public static void main(String[] args) {
        try (Connection conn = ConexionBD.conectar()) {

            String sql = "INSERT INTO usuarios (nombre, apellido, departamento, usuario, password) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "Meilyn");
            ps.setString(2, "Hill");
            ps.setString(3, "IT");
            ps.setString(4, "admin");   // usuario
            ps.setString(5, "1234");    // contraseña

            ps.executeUpdate();

            System.out.println("Usuario creado.");

        } catch (Exception e) {
            System.out.println("Error insertando usuario: " + e.getMessage());
        }
    }
}
