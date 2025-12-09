package BaseDatos;

import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void main(String[] args) {

        try (Connection conn = ConexionBD.conectar()) {

            if (conn == null) {
                System.out.println("La conexión falló. No se creó la base.");
                return;
            }

            Statement st = conn.createStatement();

            st.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "usuario TEXT UNIQUE, " +
                       "password TEXT)");

            st.execute("CREATE TABLE IF NOT EXISTS historial (" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "original TEXT, " +
                       "anonimizado TEXT, " +
                       "fecha TEXT, " +
                       "usuario_id INTEGER)");

            st.execute("INSERT INTO usuarios (usuario, password) " +
                       "SELECT 'admin', '1234' WHERE NOT EXISTS " +
                       "(SELECT 1 FROM usuarios WHERE usuario='admin')");

            System.out.println("Base de datos creada con éxito.");
            System.out.println("Tablas creadas y usuario admin insertado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
