package Modelo;

public class Texto {

    private String contenido;

    public Texto(String contenido) {
        this.contenido = contenido;
    }

    public String anonimizar() {

        String texto = contenido;

        // Correos
        texto = texto.replaceAll(
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                "[CENSURADO]"
        );

        // Teléfonos Panamá (+507 6789-1234, 6789-1234, 67891234)
        texto = texto.replaceAll(
                "(\\+?507\\s*)?\\d{4}[- ]?\\d{4}",
                "[CENSURADO]"
        );

        // Cédula panameña
        texto = texto.replaceAll(
                "\\b\\d{1}-\\d{3,4}-\\d{3,4}\\b",
                "[CENSURADO]"
        );

        // Fechas (dd/mm/yyyy, dd-mm-yyyy, texto)
        texto = texto.replaceAll(
                "\\b\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}\\b",
                "[CENSURADO]"
        );
        texto = texto.replaceAll(
                "\\b\\d{1,2} de [A-Za-zñÑ]+ de \\d{4}\\b",
                "[CENSURADO]"
        );

        // Dirección
        texto = texto.replaceAll(
                "(?i)\\b(calle|avenida|av\\.?|barrio|sector|residencial|casa)\\b[^\\n]*",
                "[CENSURADO]"
        );

        // Empresas
        texto = texto.replaceAll(
                "(?i)\\b(S\\.A\\.|S\\.A|empresa|compañía|corporación|corp\\.)\\b[^\\n]*",
                "[CENSURADO]"
        );

        // Código de expediente / proyecto
        texto = texto.replaceAll(
                "\\b[A-Z]{2,}-[A-Z]{2}-\\d{4}\\b",
                "[CENSURADO]"
        );

        // Facturas
        texto = texto.replaceAll(
                "\\bF-\\d{5}\\b",
                "[CENSURADO]"
        );

        // Nombres completos
        texto = texto.replaceAll(
                "\\b([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s){1,3}[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\b",
                "[CENSURADO]"
        );

        // Proyectos
        texto = texto.replaceAll(
                "(?i)\\bproyecto\\b[^\\n]*",
                "[CENSURADO]"
        );

        return texto;
    }
}

