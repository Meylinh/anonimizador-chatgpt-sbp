package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFService {

    // ==========================
    // LECTURA DEL PDF
    // ==========================
    public static String leerPDF(String ruta) {
        try (PDDocument doc = Loader.loadPDF(new File(ruta))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo PDF: " + e.getMessage(), e);
        }
    }

    // ==========================
    // GUARDAR PDF COMO TXT
    // ==========================
    public static void guardarPDF(String rutaSalida, String contenido) {
        try (FileWriter writer = new FileWriter(rutaSalida)) {
            writer.write(contenido);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando archivo: " + e.getMessage(), e);
        }
    }

    // ==========================
    // ANONIMIZAR CONTENIDO
    // ==========================
    public static String anonimizarContenido(String texto) {

        // Correos
        texto = texto.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "[CENSURADO]");

        // Cédula panameña
        texto = texto.replaceAll("\\b\\d{1}-\\d{3,4}-\\d{3,4}\\b", "[CENSURADO]");

        // Teléfonos
        texto = texto.replaceAll("\\+?507[- ]?\\d{3}[- ]?\\d{4}", "[CENSURADO]");
        texto = texto.replaceAll("\\b\\d{7,8}\\b", "[CENSURADO]");

        // Fechas
        texto = texto.replaceAll("\\b\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}\\b", "[CENSURADO]");
        texto = texto.replaceAll("\\b\\d{1,2} de [A-Za-zñÑ]+ de \\d{4}\\b", "[CENSURADO]");

        // Direcciones
        texto = texto.replaceAll("(?i)\\b(calle|avenida|av\\.?|barrio|sector|edificio|residencial|casa)\\b[^\\n]*",
                "[CENSURADO]");

        // Empresa
        texto = texto.replaceAll("(?i)\\b(Empresa|Compañía|Corporación|Corp\\.|S\\.A\\.|S.A\\.)\\b[^\\n]*",
                "[CENSURADO]");

        // Código de expediente
        texto = texto.replaceAll("\\bEXP[- ]?\\d{2,4}[- ]?\\d{2,6}\\b", "[CENSURADO]");

        // Nombres completos
        texto = texto.replaceAll("\\b([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s){1,3}[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\b",
                "[CENSURADO]");

        // Datos de proyecto
        texto = texto.replaceAll("(?i)\\bproyecto\\b[^\\n]*", "[CENSURADO]");

        return texto;
    }
}

    
