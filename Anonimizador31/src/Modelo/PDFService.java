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

        // Correos electrónicos
        texto = texto.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "[CENSURADO]");

        // Cédula panameña
        texto = texto.replaceAll("\\b\\d{1}-\\d{3,4}-\\d{3,4}\\b", "[CENSURADO]");

        // Teléfonos
        texto = texto.replaceAll("\\+?507[- ]?\\d{3}[- ]?\\d{4}", "[CENSURADO]");
        texto = texto.replaceAll("\\b\\d{7,8}\\b", "[CENSURADO]");

        // Fechas con números
        texto = texto.replaceAll("\\b\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}\\b", "[CENSURADO]");

        // Fechas con palabras (ej: 21 de enero de 2025)
        texto = texto.replaceAll("(?i)\\b\\d{1,2} de [a-záéíóúñ]+ de \\d{4}\\b", "[CENSURADO]");

        // Direcciones
        texto = texto.replaceAll("(?i)(calle|avenida|av\\.?|barrio|sector|edificio|residencial|casa|torre|piso|apartamento|ciudad)[^\\n]*",
                "[CENSURADO]");

        // Empresas
        texto = texto.replaceAll("(?i)(empresa|compañía|corporación|corp\\.|s\\.a\\.|s.a\\.)[^\\n]*", "[CENSURADO]");

        // Ciudad o país "Panamá"
        texto = texto.replaceAll("(?i)panamá", "[CENSURADO]");

        // Código de expediente tipo EXP-XXXX
        texto = texto.replaceAll("\\bEXP[- ]?\\d{2,4}[- ]?\\d{2,6}\\b", "[CENSURADO]");

        // Nombres completos (hasta 4 palabras)
        texto = texto.replaceAll("\\b([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s){1,3}[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\b", "[CENSURADO]");

        // La palabra "proyecto" y lo que siga en esa línea
        texto = texto.replaceAll("(?i)proyecto[^\\n]*", "[CENSURADO]");

        // Números largos (facturas, códigos internos, etc) – 6 a 15 dígitos
        texto = texto.replaceAll("\\b\\d{6,15}\\b", "[CENSURADO]");
        
     // Teléfonos Panamá (+507 con variaciones)
        texto = texto.replaceAll("(?i)\\+?\\s*507[\\s-]*\\d{2,8}", "[CENSURADO]");

        // Formatos 7 u 8 dígitos con o sin guiones
        texto = texto.replaceAll("\\b\\d{7,8}\\b", "[CENSURADO]");

        // Formatos 3-4 o 4-4
        texto = texto.replaceAll("\\b\\d{3}[\\s-]?\\d{4}\\b", "[CENSURADO]");
        
     // Números de factura (F-12345, FA-2024-98, etc.)
        texto = texto.replaceAll("(?i)\\bF[A-Z]?-?\\d{3,8}\\b", "[CENSURADO]");




        return texto;
    }
}

