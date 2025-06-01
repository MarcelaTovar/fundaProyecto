/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        return Conexion.conectorcito();  // nueva forma de conectar
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
                vendedores.put(nombre, vendedor);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al cargar usuarios: " + e.getMessage());
        }

        return vendedores;
    }

    public void cargarventas(String rutaBinario, Connection conexion) {
        Map<String, Vendedor> vendedores = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaBinario))) {
            vendedores = (Map<String, Vendedor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer binario: " + e.getMessage());
            return;
        }
        String query = "SELECT 'Fact' as 'Tipo Doc', t0.docnum, T0.Docdate, T1.[WhsCode], T3.[ItmsGrpNam]\n"
                + "as 'Grupo', T2.U_Categ as 'Categoria', T2.[ItemCode], T2.[ItemName], T4.[SlpName],\n"
                + "CASE T1.[Currency] WHEN 'USD' THEN (T1.Rate*T1.[INMPrice]*T1.Quantity) ELSE (T1.\n"
                + "[INMPrice]*T1.Quantity) END as 'Monto'\n"
                + "FROM OINV T0\n"
                + "INNER JOIN INV1 T1 ON T0.DocEntry = T1.DocEntry\n"
                + "INNER JOIN OITM T2 ON T1.ItemCode = T2.ItemCode\n"
                + "INNER JOIN OITB T3 ON T2.ItmsGrpCod = T3.ItmsGrpCod\n"
                + "INNER JOIN OSLP T4 ON T0.SlpCode = T4.SlpCode\n"
                + "WHERE T0.[DocDate] >='01-01-2011'\n"
                + "and T0.[DocDate] <= '30-04-2025'\n"
                + "AND (T0.[Canceled] = 'N')\n"
                + "UNION ALL\n"
                + "SELECT 'NC', t0.docnum, T0.DocDate, T1.[WhsCode], T3.[ItmsGrpNam], T2.U_Categ, T2.\n"
                + "[ItemCode], T2.[ItemName], T4.[SlpName], (T1.[INMPrice]*T1.Quantity*-1)\n"
                + "FROM ORIN T0\n"
                + "INNER JOIN RIN1 T1 ON T0.DocEntry = T1.DocEntry\n"
                + "INNER JOIN OITM T2 ON T1.ItemCode = T2.ItemCode\n"
                + "INNER JOIN OITB T3 ON T2.ItmsGrpCod = T3.ItmsGrpCod\n"
                + "INNER JOIN OSLP T4 ON T0.SlpCode = T4.SlpCode\n"
                + "WHERE T0.[DocDate] >= '01-01-2011' and T0.[DocDate] <= '30-04-2025' AND (T0.Canceled = 'N')";
        try (PreparedStatement ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String fecha = rs.getString("Docdate");
                String tipo = rs.getString("Grupo");
                String nombre = rs.getString("SlpName");
                double monto = rs.getDouble("Monto");

                int suc = rs.getInt("docnum");
                String sucursal = "";
                switch (Integer.parseInt(Integer.toString(suc).substring(0, 1))) {
                    case 1:
                        sucursal = "1";
                        break;
                    case 2:
                        sucursal = "2";
                        break;
                    case 3:
                        sucursal = "3";
                        break;
                    case 4:
                        sucursal = "4";
                        break;
                    default:
                        break;
                }

                String nombreLimpio = nombre.trim();
                if (vendedores.containsKey(nombreLimpio)) {
                    Vendedor v = vendedores.get(nombreLimpio);
                    Venta venta = new Venta(tipo, fecha, monto,sucursal);
                    v.getVentas().add(venta);
                    System.out.println("➕ Venta agregada a: " + nombre + " → " + tipo + ", " + fecha + ", L " + monto);
                }

            }

        } catch (SQLException e) {
            System.err.println("❌ Error en la consulta: " + e.getMessage());
            return;
        }

        // 3. Guardar de nuevo el binario actualizado
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaBinario))) {
            oos.writeObject(vendedores);
            System.out.println("✅ Binario actualizado con comisiones extra.");
        } catch (IOException e) {
            System.err.println("❌ Error al guardar binario actualizado: " + e.getMessage());
        }
    }

    public void cargarClientes(String rutaBinario, Connection conexion) {
        Map<String, Vendedor> vendedores = new HashMap<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaBinario))) {
            vendedores = (Map<String, Vendedor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer binario: " + e.getMessage());
            return;
        }
        String query = "SELECT\n"
                + "C.CardCode AS 'Código Cliente',\n"
                + "C.CardName AS 'Nombre Cliente',\n"
                + "S.SlpName AS 'Vendedor',\n"
                + "G.GroupName AS 'Categoría Cliente',\n"
                + "P.DocDate AS 'Fecha de Pago',\n"
                + "I.DocDate AS 'Fecha de Factura',\n"
                + "I.DocNum AS 'Número de Factura',\n"
                + "SUM(RP.SumApplied) AS 'Monto Pagado'\n"
                + "FROM\n"
                + "ORCT P -- Pagos recibidos\n"
                + "INNER JOIN RCT2 RP ON P.DocEntry = RP.DocNum -- Relación pagos con facturas\n"
                + "INNER JOIN OINV I ON RP.DocEntry = I.DocEntry -- Facturas relacionadas con el\n"
                + "INNER JOIN OCRD C ON I.CardCode = C.CardCode -- Clientes\n"
                + "INNER JOIN OCRG G ON C.GroupCode = G.GroupCode -- Categoría del cliente\n"
                + "INNER JOIN OSLP S ON I.SlpCode = S.SlpCode -- Vendedores\n"
                + "WHERE\n"
                + "P.DocDate BETWEEN '01-01-2011' AND '30-04-2025' -- Solo pide rango de fechas\n"
                + "AND P.Canceled = 'N' -- Solo pagos no cancelados\n"
                + "GROUP BY\n"
                + "C.CardCode, C.CardName, S.SlpName, G.GroupName, P.DocDate, I.DocDate, I.DocNum\n"
                + "ORDER BY\n"
                + "P.DocDate DESC, I.DocNum";
        try (PreparedStatement ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String codigo = rs.getString("Código Cliente");
                String nombreC = rs.getString("Nombre Cliente");
                String categoria = rs.getString("Categoría Cliente");
                String nombre = rs.getString("Vendedor");
                String fecha = rs.getString("Fecha de Pago");
                double monto = rs.getDouble("Monto Pagado");

                String nombreLimpio = nombre.trim();
                if (vendedores.containsKey(nombreLimpio)) {
                    Vendedor v = vendedores.get(nombreLimpio);
                    Cliente cliente = new Cliente(codigo, nombreC, fecha, monto,categoria);
                    v.getClientes().add(cliente);
                    System.out.println("➕ Cliente agregado a: " + nombre + " → " + categoria + ", " + fecha + ", L " + monto);
                }

            }
        } catch (SQLException e) {
            System.err.println("❌ Error en la consulta: " + e.getMessage());
            return;
        }

        // 3. Guardar de nuevo el binario actualizado
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaBinario))) {
            oos.writeObject(vendedores);
            System.out.println("✅ Binario actualizado con comisiones extra.");
        } catch (IOException e) {
            System.err.println("❌ Error al guardar binario actualizado: " + e.getMessage());
        }
    }

}
