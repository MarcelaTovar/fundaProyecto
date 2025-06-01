package com.mycompany.fundamentoproyecto;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conexion {
    public static Connection conectorcito() {
        Properties props = new Properties();
        File configFile = new File("config.properties");

        if (!configFile.exists()) {
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                props.setProperty("db.server", "localhost");
                props.setProperty("db.port", "1433");
                props.setProperty("db.name", "NombreDeTuBase");
                props.setProperty("db.user", "sa");
                props.setProperty("db.password", "1234");
                props.store(out, "Configuración de conexión a SQL Server");
                JOptionPane.showMessageDialog(null,
                        "⚠️ Archivo 'config.properties' creado. Por favor edítalo y vuelve a ejecutar el programa.",
                        "Archivo de configuración", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "❌ No se pudo crear el archivo de configuración:\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }

        try (FileInputStream in = new FileInputStream(configFile)) {
            props.load(in);
            String servidor = props.getProperty("db.server");
            String puerto = props.getProperty("db.port");
            String nombreBase = props.getProperty("db.name");
            String USER = props.getProperty("db.user");
            String PASSWORD = props.getProperty("db.password");

            String URL = "jdbc:sqlserver://" + servidor + ":" + puerto + ";databaseName=" + nombreBase;

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null,
                    "✅ Conexión exitosa a la base de datos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return conn;

        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "❌ Error al conectar a la base de datos:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
