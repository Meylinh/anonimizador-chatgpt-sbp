package Modelo;

import Modelo.Usuario;
import Modelo.Texto;


public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Fares");
        Texto texto = new Texto("Mi nombre es Fares y trabajo en un Banistmo en Calle 50");

        System.out.println(usuario);
        System.out.println(texto);
        System.out.println("Texto anonimizado: " + texto.anonimizar());
    }
}
