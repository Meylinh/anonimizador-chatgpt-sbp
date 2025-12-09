package Modelo;

public class Texto {
    private String contenido;

    public Texto(String contenido) {
        this.contenido = contenido;
    }

    public String anonimizar() {
        return contenido
                .replaceAll("(?i)banistmo", "***")
                .replaceAll("(?i)sbp", "***")
                .replaceAll("(?i)banco", "***")
                .replaceAll("(?i)panamá", "***");
    }
}

