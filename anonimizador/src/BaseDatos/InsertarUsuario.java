package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertarUsuario {

    public static void main(String[] args) {
        try (Connection conn = ConexionBD.conectar()) {

            String sql = "INSERT INTO usuarios (nombre, apellido, departamento, usuario, password) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "Admin");     // nombre
            ps.setString(2, "Sistema");   // apellido
            ps.setString(3, "IT");        // departamento
            ps.setString(4, "admin");     // usuario
            ps.setString(5, "admin");     // contraseña

            ps.executeUpdate();

            System.out.println("Usuario admin/admin creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error insertando usuario: " + e.getMessage());
        }
    }
}
