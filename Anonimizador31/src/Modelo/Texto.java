package Modelo;

public class Texto {
    private String contenido;

    public Texto(String contenido) {
        this.contenido = contenido;
    }

    public String anonimizar() {
        String t = contenido;

   
        // --- Palabras viejas ---
        t = t.replaceAll("(?i)banistmo", "***");
        t = t.replaceAll("(?i)sbp", "***");
        t = t.replaceAll("(?i)banco", "***");
        t = t.replaceAll("(?i)panamá", "***");

       
        // Nombres completos (dos palabras con mayúscula)
        t = t.replaceAll("(?i)[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s+[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+", "***");

        // Empresas
        t = t.replaceAll("(?i)(S\\.A\\.|S\\.A|empresa|corporación|tecnologías|panama|solutions|global)", "***");

        // Cédula panameña (X-XXX-XXXX)
        t = t.replaceAll("\\b\\d-\\d{3}-\\d{4}\\b", "***");

        // Teléfono (+507 0000-0000)
        t = t.replaceAll("\\+507\\s?\\d{4}-?\\d{4}", "***");

        // Correos electrónicos
        t = t.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "***");

        // Dirección común
        t = t.replaceAll("(?i)(calle|avenida|edificio|torre|piso|oficina|ciudad|provincia)", "***");

        // Fechas (dd/mm/aaaa o dd-mm-aaaa)
        t = t.replaceAll("\\b\\d{2}[/-]\\d{2}[/-]\\d{4}\\b", "***");

        // Código de expediente (EXP-XXXX)
        t = t.replaceAll("\\bEXP-[A-Z0-9-]+\\b", "***");

        // Números largos (facturas, contratos)
        t = t.replaceAll("\\b\\d{5,}\\b", "***");

        return t;
    }
}
