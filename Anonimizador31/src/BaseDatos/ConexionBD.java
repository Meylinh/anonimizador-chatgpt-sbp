package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL = "jdbc:sqlite:anonimizador.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
