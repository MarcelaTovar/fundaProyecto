/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SQLManagement {

    //String URL = "jdbc:sqlserver://"+"localhost:1433;databaseName=Ferco2011";
    String URL = "";
    String nombreBase = "";
    String puerto = "";
    String servidor = "";
    String USER = "";
    String PASSWORD = "";

    public SQLManagement() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNombreBase() {
        return nombreBase;
    }

    public void setNombreBase(String nombreBase) {
        this.nombreBase = nombreBase;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Connection conect() {
        Connection conn = null;
        try {
            URL = "jdbc:sqlserver://" + servidor + ":" + puerto + ";databaseName=" + nombreBase;
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null, "✅ Conexión exitosa a la base de datos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "❌ Error al conectar a la base de datos:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }

    public void llenarTabla(Connection conn, JTable tabla) {
        String[] columnas = {"Nombre", "Comision"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SlpName, Commission FROM OSLP");

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("SlpName"),
                    rs.getString("Commission")
                };
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al llenar la tabla:\n" + e.getMessage());
        }
    }
    
    public void buscarEnTabla (Connection conn, JTable tabla, String nombre){
        String[] columnas = {"Nombre", "Comision"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        try {
            Statement stmt = conn.createStatement();
            String query = "Select SlpName, Commission FROM OSLP WHERE SlpName = '"+nombre+"'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("SlpName"),
                    rs.getString("Commission")
                };
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al llenar la tabla:\n" + e.getMessage());
        }
    }
    
    public HashMap<String, Vendedor> cargarVendedores(Connection conn) {
        HashMap<String, Vendedor> vendedores = new HashMap<>();

        String sql = "SELECT SlpCode, SlpName FROM OSLP";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("SlpCode");
                String nombre = rs.getString("SlpName");

                Vendedor vendedor = new Vendedor(id, nombre);
                vendedores.put(id, vendedor); 
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al cargar usuarios: " + e.getMessage());
        }

        return vendedores;
    }


}
