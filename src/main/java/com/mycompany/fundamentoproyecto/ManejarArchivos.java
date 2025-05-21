package com.mycompany.fundamentoproyecto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
            System.out.println("Archivo leído exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se devolverá un HashMap vacío.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return datos;
    }

    // Agregar o actualizar un registro
    public void actualizarArchivo(Vendedor nuevoRegistro) {
        Map<String, Vendedor> datos = leerArchivo();
        datos.put(nuevoRegistro.getId(), nuevoRegistro); // actualiza si ya existe
        escribirArchivo(datos);
    }
}

