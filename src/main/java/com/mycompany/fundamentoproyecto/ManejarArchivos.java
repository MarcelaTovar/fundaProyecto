package com.mycompany.fundamentoproyecto;

import java.io.*;
import java.util.ArrayList;
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

    public void guardarComoBinario(String rutaTexto, String rutaBinario) {
        ArrayList<Producto> productos = new ArrayList<>();

        // 1. Leer el archivo de texto
        try (BufferedReader br = new BufferedReader(new FileReader(rutaTexto))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 2) {
                    String categoria = partes[1].trim();
                    int id = Integer.parseInt(partes[0].trim());

                    productos.add(new Producto(categoria, id));
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo de texto: " + e.getMessage());
            return;
        }

        // 2. Escribir el ArrayList en un archivo binario
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaBinario))) {
            oos.writeObject(productos);
            System.out.println("✅ Datos guardados exitosamente en binario.");
        } catch (IOException e) {
            System.err.println("❌ Error al escribir el archivo binario: " + e.getMessage());
        }
    }

    public ArrayList<Producto> leerProducto(String rutaBinario) {
        ArrayList<Producto> productos = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaBinario))) {
            productos = (ArrayList<Producto>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer el archivo binario: " + e.getMessage());
        }

        return productos;
    }

    // Leer HashMap desde el archivo binario
    @SuppressWarnings("unchecked")
    public Map<String, Vendedor> leerArchivo() {
        Map<String, Vendedor> datos = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            datos = (Map<String, Vendedor>) ois.readObject();
            System.out.println("✅ Archivo leído exitosamente. Contenido:");

            // Imprimir el contenido del archivo
            for (Map.Entry<String, Vendedor> entry : datos.entrySet()) {
                // System.out.println("🧾 Clave: " + entry.getKey());
                // System.out.println("👤 Vendedor: " + entry.getValue());
                // System.out.println("--------------------------------------------");
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
        datos.put(nuevoRegistro.getNombre(), nuevoRegistro); // actualiza por nombre
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
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");

        // Llenar el modelo con los datos del HashMap
        for (Vendedor v : vendedores.values()) {
            modelo.addRow(new Object[]{v.getId(), v.getNombre()});
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
