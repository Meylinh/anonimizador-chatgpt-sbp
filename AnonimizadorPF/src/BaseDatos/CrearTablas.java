package BaseDatos;

import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void main(String[] args) {

        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario TEXT UNIQUE,
                    password TEXT
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS historial (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    texto_original TEXT,
                    texto_anonimizado TEXT,
                    fecha TEXT,
                    usuario_id INTEGER
                )
            """);

            st.execute("""
                INSERT OR IGNORE INTO usuarios(usuario,password)
                VALUES ('admin','1234')
            """);

            System.out.println("✅ Base de datos lista");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
