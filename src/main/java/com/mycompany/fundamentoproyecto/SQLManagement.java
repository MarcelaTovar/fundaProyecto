/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SQLManagement {
    
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Ferco2011";
    private static final String USER = "marcela";
    private static final String PASSWORD = "sqlserver";

    public Connection conect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }
}

