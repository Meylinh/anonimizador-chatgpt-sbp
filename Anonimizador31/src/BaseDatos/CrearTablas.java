package BaseDatos;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void inicializarBD() {
        try {
            File bd = new File("anonimador.db");

            // Si ya existe, no hacemos nada
            if (bd.exists()) {
                System.out.println("✔ Base de datos ya existente. No se vuelve a crear.");
                return;
            }

            System.out.println("⏳ Creando nueva base de datos...");

            try (Connection conn = ConexionBD.conectar();
                 Statement stmt = conn.createStatement()) {

                stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usuario TEXT UNIQUE, " +
                        "password TEXT)");

                stmt.execute("CREATE TABLE IF NOT EXISTS historial (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "accion TEXT, " +
                        "fecha TEXT)");

                // Insertar admin solo la primera vez
                stmt.execute("INSERT INTO usuarios (usuario, password) VALUES ('admin', '1234')");

                System.out.println("✔ Base de datos creada correctamente.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error creando la base de datos: " + e.getMessage());
        }
    }
}

