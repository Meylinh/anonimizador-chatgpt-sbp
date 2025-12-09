package Controlador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import BaseDatos.ConexionBD;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Test 2 – UsuarioDAOTest
 *
 * Evalúa el proceso de login.
 * - Se crean usuarios de prueba en la base de datos.
 * - Se valida si el usuario correcto accede exitosamente.
 * - Se verifica que usuarios inexistentes no puedan entrar.
 */
public class UsuarioDAOTest {

    @BeforeAll
    static void prepararBaseDatos() {
        try (Connection conn = ConexionBD.conectar()) {
            Statement st = conn.createStatement();

            // Limpia la tabla
            st.executeUpdate("DELETE FROM usuarios");

            // Inserta usuarios de prueba
            st.executeUpdate("INSERT INTO usuarios (usuario, password) VALUES ('juan', '1234')");
            st.executeUpdate("INSERT INTO usuarios (usuario, password) VALUES ('maria', 'abcd')");

        } catch (Exception e) {
            fail("Error preparando la BD para pruebas: " + e.getMessage());
        }
    }

    @Test
    void testLoginCorrecto() {
        int resultado = UsuarioDAO.login("juan", "1234");
        assertTrue(resultado > 0, "El login con credenciales correctas debe devolver un ID válido");
    }

    @Test
    void testLoginIncorrecto() {
        int resultado = UsuarioDAO.login("juan", "malpass");
        assertEquals(-1, resultado, "Contraseña incorrecta debe devolver -1");
    }

    @Test
    void testUsuarioNoExiste() {
        int resultado = UsuarioDAO.login("robot", "123");
        assertEquals(-1, resultado, "Usuario que no existe debe devolver -1");
    }
}
