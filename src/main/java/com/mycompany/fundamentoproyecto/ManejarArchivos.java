package com.mycompany.fundamentoproyecto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManejarArchivos {

    private final String rutaArchivo;

    public ManejarArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Escribir HashMap al archivo binario
    public void escribirArchivo(Map<String, Vendedor> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(datos);
            System.out.println("Archivo escrito exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Leer HashMap desde el archivo binario
    @SuppressWarnings("unchecked")
    public Map<String, Vendedor> leerArchivo() {
        Map<String, Vendedor> datos = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            datos = (Map<String, Vendedor>) ois.readObject();
            System.out.println("✅ Archivo leído exitosamente. Contenido:");

            // Imprimir los datos del HashMap
            for (Map.Entry<String, Vendedor> entry : datos.entrySet()) {
                System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Archivo no encontrado. Se devolverá un HashMap vacío.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
        }
        return datos;
    }

    // Agregar o actualizar un registro
    public void actualizarArchivo(Vendedor nuevoRegistro) {
        Map<String, Vendedor> datos = leerArchivo();
        datos.put(nuevoRegistro.getId(), nuevoRegistro); // actualiza si ya existe
        escribirArchivo(datos);
    }

    public Vendedor buscarVendedor(String nombre) {
        Map<String, Vendedor> datos = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            datos = (Map<String, Vendedor>) ois.readObject();
            System.out.println("📂 Archivo leído exitosamente.");

            for (Vendedor v : datos.values()) {
                if (v.getNombre().equalsIgnoreCase(nombre)) { // Ignora mayúsculas/minúsculas
                    return v; // Retorna el primer vendedor que coincida por nombre
                }
            }

            System.out.println("🔍 No se encontró un vendedor con el nombre: " + nombre);

        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Archivo no encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
        }

        return null;

    }

    public void editarTabla(JTable tabla) {
        // Leer el archivo binario
        Map<String, Vendedor> vendedores = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            vendedores = (HashMap<String, Vendedor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Comision");

        // Llenar el modelo con los datos del HashMap
        for (Vendedor v : vendedores.values()) {
            modelo.addRow(new Object[]{v.getNombre(), v.sumarComision()});
        }

        // Asignar el modelo a la tabla
        tabla.setModel(modelo);

    }

    public void buscarYMostrarVendedorEnTabla(String nombreBuscado, JTable tabla) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Map<String, Vendedor> datos = (Map<String, Vendedor>) ois.readObject();

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpia la tabla antes de llenarla

            for (Vendedor v : datos.values()) {
                if (v.getNombre().equalsIgnoreCase(nombreBuscado)) {
                    // Agrega una fila con los datos del vendedor encontrado
                    modelo.addRow(new Object[]{
                        v.getNombre(),
                        v.getComisionTotal()
                    });
                    return; // solo muestra el primero que coincida
                }
            }

            // Si no se encuentra
            JOptionPane.showMessageDialog(null, "❌ Vendedor no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
