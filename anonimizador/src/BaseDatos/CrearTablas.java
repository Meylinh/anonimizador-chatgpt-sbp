package BaseDatos;

import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void main(String[] args) {
        try (Connection conn = ConexionBD.conectar()) {

            // SQL para crear tabla usuarios
            String usuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT,
                    apellido TEXT,
                    departamento TEXT,
                    usuario TEXT UNIQUE,
                    password TEXT
                );
                """;

            // SQL para crear tabla historial
            String historial = """
                CREATE TABLE IF NOT EXISTS historial (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    prompt_original TEXT,
                    prompt_anonimizado TEXT,
                    fecha TEXT,
                    usuario_id INTEGER,
                    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                );
                """;

            Statement st = conn.createStatement();
            st.execute(usuarios);
            st.execute(historial);

            System.out.println("✔ Tablas creadas correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error creando tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
