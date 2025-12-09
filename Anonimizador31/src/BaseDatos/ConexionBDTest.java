package BaseDatos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class ConexionBDTest {

    @Test
    public void testConexionNoEsNula() {
        Connection conn = ConexionBD.conectar();
        assertNotNull(conn, "La conexión no debe ser nula");
    }

    @Test
    public void testConexionCierraCorrectamente() throws Exception {
        Connection conn = ConexionBD.conectar();
        conn.close();
        assertTrue(conn.isClosed(), "La conexión debe cerrarse correctamente");
    }
}
