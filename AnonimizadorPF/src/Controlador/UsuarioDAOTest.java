package Controlador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    void loginCorrecto() {
        int id = UsuarioDAO.login("admin", "1234");
        assertTrue(id > 0, "El usuario admin debería iniciar sesión");
    }

    @Test
    void loginIncorrectoPassword() {
        int id = UsuarioDAO.login("admin", "xxxx");
        assertEquals(-1, id, "Contraseña incorrecta no debe permitir login");
    }

    @Test
    void loginUsuarioInexistente() {
        int id = UsuarioDAO.login("noexiste", "1234");
        assertEquals(-1, id, "Usuario inexistente no debe iniciar sesión");
    }
}
