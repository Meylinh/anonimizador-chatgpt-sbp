package BaseDatos;

import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement()) {

            // Crear tabla usuarios
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT NOT NULL," +
                "password TEXT NOT NULL)"
            );

            // Crear tabla historial
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS historial (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "accion TEXT NOT NULL," +
                "fecha TEXT NOT NULL)"
            );

            // Insertar usuario admin si no existe
            stmt.executeUpdate(
                "INSERT INTO usuarios (usuario, password) " +
                "SELECT 'admin','1234' " +
                "WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE usuario='admin')"
            );

            System.out.println("Tablas creadas correctamente y usuario admin insertado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

