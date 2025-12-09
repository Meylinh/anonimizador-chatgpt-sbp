package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL = "jdbc:sqlite:anonimador.db";

    public static Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC"); // Muy importante
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
