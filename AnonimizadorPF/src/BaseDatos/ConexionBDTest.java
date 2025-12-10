package BaseDatos;

import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConexionBDTest {

    @Test
    void conectarBaseDatos() {
        try (Connection c = ConexionBD.conectar()) {
            assertNotNull(c, "La conexión no debe ser null");
            assertFalse(c.isClosed(), "La conexión debe estar abierta");
        } catch (Exception e) {
            fail("Error al conectar: " + e.getMessage());
        }
    }

    @Test
    void verificarTablasPrincipales() {
        try (Connection c = ConexionBD.conectar();
             Statement st = c.createStatement()) {

            ResultSet rs1 = st.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='usuarios'");
            assertTrue(rs1.next(), "Debe existir la tabla usuarios");

            ResultSet rs2 = st.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='historial'");
            assertTrue(rs2.next(), "Debe existir la tabla historial");

        } catch (Exception e) {
            fail("Error verificando tablas: " + e.getMessage());
        }
    }

    @Test
    void ejecutarConsultaSinError() {
        try (Connection c = ConexionBD.conectar();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT COUNT(*) FROM usuarios")) {

            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next(), "La consulta debe ejecutarse correctamente");

        } catch (Exception e) {
            fail("Error ejecutando consulta: " + e.getMessage());
        }
    }
}
