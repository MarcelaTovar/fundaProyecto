package com.mycompany.fundamentoproyecto;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportarPDF {

    public void exportarPDF(JTable t1, JTable t2, JTable t3) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar como PDF");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            if (!ruta.endsWith(".pdf")) {
                ruta += ".pdf";
            }

            try {
                Document documento = new Document();
                PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                documento.open();

                documento.add(new Paragraph("Reporte de Categorías", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                documento.add(Chunk.NEWLINE);
                agregarTablaPDF(documento, t1);

                documento.add(Chunk.NEWLINE);
                documento.add(new Paragraph("Reporte de Metas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                documento.add(Chunk.NEWLINE);
                agregarTablaPDF(documento, t2);

                documento.add(Chunk.NEWLINE);
                documento.add(new Paragraph("Reporte de Total", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                documento.add(Chunk.NEWLINE);
                agregarTablaPDF(documento, t3);
                
                documento.close();
                Desktop.getDesktop().open(new File(ruta));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarTablaPDF(Document doc, JTable tabla) throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());
        pdfTable.setWidthPercentage(100);

        // Encabezados
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            PdfPCell celda = new PdfPCell(new Phrase(tabla.getColumnName(i)));
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pdfTable.addCell(celda);
        }

        // Filas
        for (int row = 0; row < tabla.getRowCount(); row++) {
            for (int col = 0; col < tabla.getColumnCount(); col++) {
                Object valor = tabla.getValueAt(row, col);
                pdfTable.addCell(String.valueOf(valor));
            }
        }

        doc.add(pdfTable);
    }
}
