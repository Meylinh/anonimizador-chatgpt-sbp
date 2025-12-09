package tools;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrearUsuarioAdmin {

    public static void main(String[] args) {
        String sql = "INSERT INTO usuarios (nombre, apellido, departamento, usuario, password) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Admin");
            ps.setString(2, "Sistema");
            ps.setString(3, "IT");
            ps.setString(4, "admin");   // usuario
            ps.setString(5, "admin");   // contraseña

            ps.executeUpdate();

            System.out.println("✔ Usuario admin/admin creado correctamente.");

        } catch (Exception e) {
            System.out.println("ERROR creando admin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
