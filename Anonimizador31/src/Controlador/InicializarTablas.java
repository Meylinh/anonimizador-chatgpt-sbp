package Controlador;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicializarTablas {

    public static void crearTablas() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "usuario TEXT NOT NULL UNIQUE, "
                + "password TEXT NOT NULL"
                + ");";

        String sqlHistorial = "CREATE TABLE IF NOT EXISTS historial ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "original TEXT NOT NULL, "
                + "anonimizado TEXT NOT NULL, "
                + "user_id INTEGER NOT NULL, "
                + "fecha TEXT NOT NULL, "
                + "FOREIGN KEY (user_id) REFERENCES usuarios(id)"
                + ");";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps1 = conn.prepareStatement(sqlUsuarios);
             PreparedStatement ps2 = conn.prepareStatement(sqlHistorial)) {

            ps1.execute();
            ps2.execute();
            System.out.println("Tablas creadas correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }

    // Agregar usuario de prueba
    public static void agregarUsuarioPrueba() {
        String sql = "INSERT OR IGNORE INTO usuarios (usuario, password) VALUES ('admin', '1234');";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Usuario de prueba agregado.");
        } catch (SQLException e) {
            System.out.println("Error al agregar usuario de prueba: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        crearTablas();
        agregarUsuarioPrueba();
    }
}
