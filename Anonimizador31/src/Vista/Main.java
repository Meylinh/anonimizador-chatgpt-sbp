package Vista;

import BaseDatos.CrearTablas;

public class Main {

    public static void main(String[] args) {

        CrearTablas.inicializarBD();
       
        LoginVista login = new LoginVista();
        login.setVisible(true);
    }
}
