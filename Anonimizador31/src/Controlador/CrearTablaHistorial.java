package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTablaHistorial {

    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS historial ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "original TEXT NOT NULL, "
                   + "anonimizado TEXT NOT NULL, "
                   + "user_id INTEGER NOT NULL, "
                   + "fecha TEXT NOT NULL, "
                   + "FOREIGN KEY (user_id) REFERENCES usuarios(id)"
                   + ");";

        try (Connection conn = BaseDatos.ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.execute();
            System.out.println("Tabla 'historial' creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        crearTabla(); // Llamamos al método para crear la tabla
    }
}
