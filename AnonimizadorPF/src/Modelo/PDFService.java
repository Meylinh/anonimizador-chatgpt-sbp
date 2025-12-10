package Modelo;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFService {

    public static String leerPDF(String ruta) {
        try (PDDocument doc = Loader.loadPDF(new File(ruta))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);
        } catch (Exception e) {
            throw new RuntimeException("Error al leer PDF", e);
        }
    }
}
