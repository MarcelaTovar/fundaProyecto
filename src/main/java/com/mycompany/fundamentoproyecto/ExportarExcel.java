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

    public void exportarExcel(JTable t1, JTable t2, JTable t3) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel (.xlsx)", "xlsx");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            if (!ruta.endsWith(".xlsx")) {
                ruta += ".xlsx";
            }

            File archivoXLSX = new File(ruta);
            if (archivoXLSX.exists()) {
                archivoXLSX.delete();
            }
            archivoXLSX.createNewFile();

            Workbook libro = new XSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream(archivoXLSX);

            // 🟢 Hoja 1
            Sheet hoja1 = libro.createSheet("Tabla 1");
            exportarTablaAHoja(t1, hoja1);

            // 🟢 Hoja 2
            Sheet hoja2 = libro.createSheet("Tabla 2");
            exportarTablaAHoja(t2, hoja2);
            
             // 🟢 Hoja 3
            Sheet hoja3 = libro.createSheet("Tabla 3");
            exportarTablaAHoja(t3, hoja3);
            
            libro.write(archivo);
            
            archivo.close();
            Desktop.getDesktop().open(archivoXLSX);
        }
    }

    // 🔧 Método auxiliar para escribir una JTable en una hoja
    private void exportarTablaAHoja(JTable tabla, Sheet hoja) {
        hoja.setDisplayGridlines(false);

        // Encabezados
        Row filaEncabezados = hoja.createRow(0);
        for (int c = 0; c < tabla.getColumnCount(); c++) {
            Cell celda = filaEncabezados.createCell(c);
            celda.setCellValue(tabla.getColumnName(c));
        }

        // Datos
        for (int f = 0; f < tabla.getRowCount(); f++) {
            Row fila = hoja.createRow(f + 1);
            for (int c = 0; c < tabla.getColumnCount(); c++) {
                Cell celda = fila.createCell(c);
                Object valor = tabla.getValueAt(f, c);
                if (valor instanceof Number) {
                    celda.setCellValue(((Number) valor).doubleValue());
                } else {
                    celda.setCellValue(String.valueOf(valor));
                }
            }
        }
    }
}
