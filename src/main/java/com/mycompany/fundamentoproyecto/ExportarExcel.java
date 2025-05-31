/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // ✅ Importar esta clase

public class ExportarExcel {

    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel (.xlsx)", "xlsx"); // ✅ Cambiar filtro
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            if (!ruta.endsWith(".xlsx")) {
                ruta += ".xlsx"; // ✅ Usar .xlsx
            }
            try {
                File archivoXLSX = new File(ruta);
                if (archivoXLSX.exists()) {
                    archivoXLSX.delete();
                }
                archivoXLSX.createNewFile();

                Workbook libro = new XSSFWorkbook(); // ✅ Usar XSSFWorkbook
                FileOutputStream archivo = new FileOutputStream(archivoXLSX);
                Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);

                // Crear encabezados
                Row filaEncabezados = hoja.createRow(0);
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = filaEncabezados.createCell(c);
                    celda.setCellValue(t.getColumnName(c));
                }

                // Llenar datos
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f + 1);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        Object valor = t.getValueAt(f, c);
                        if (valor instanceof Number) {
                            celda.setCellValue(((Number) valor).doubleValue());
                        } else {
                            celda.setCellValue(String.valueOf(valor));
                        }
                    }
                }

                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLSX);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
}
