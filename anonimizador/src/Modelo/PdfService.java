package Modelo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.Tika;

import java.io.File;

public class PdfService {

    // EXTRAER TEXTO DEL PDF CON APACHE TIKA
    public static String extraerTextoPDF(File pdf) throws Exception {
        Tika tika = new Tika();
        return tika.parseToString(pdf);
    }

    // CREAR PDF NUEVO CON TEXTO ANONIMIZADO (PDFBox 3.0.6)
    public static void crearPDFAnonimizado(String textoAnon, File salida) throws Exception {

        // Creamos el documento con try-with-resources para asegurar cierre
        try (PDDocument doc = new PDDocument()) {

            PDPage pagina = new PDPage();
            doc.addPage(pagina);

            // Cargamos la fuente estándar (Helvetica) como PDType1Font usando el enum Standard14Fonts
            PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            // Creamos el content stream (también en try-with-resources)
            try (PDPageContentStream cs = new PDPageContentStream(doc, pagina)) {

                cs.beginText();

                // Establecer la fuente y tamaño
                cs.setFont(font, 12);

                // Posición inicial del texto (ajusta si lo necesitas)
                cs.newLineAtOffset(50, 700);

                // Escribir línea por línea bajando 15 unidades por línea
                String[] lineas = textoAnon.split("\n");
                for (String linea : lineas) {
                    // Evitar que showText falle con cadenas vacías largas
                    if (linea == null) linea = "";
                    cs.showText(linea);
                    cs.newLineAtOffset(0, -15); // bajar línea
                }

                cs.endText();
            }

            // Guardar y cerrar documento (doc se cierra automáticamente por try-with-resources)
            doc.save(salida);
        }
    }
}
