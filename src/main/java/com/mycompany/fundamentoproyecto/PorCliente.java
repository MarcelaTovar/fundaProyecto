/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Osmin Tovar
 */
public class PorCliente extends Comision {

    ArrayList<Cliente> clientes = new ArrayList();

    public PorCliente() {
    }

    public PorCliente(int sucursal) {
        super(sucursal);

    }

    public PorCliente(ArrayList<Cliente> clientes, int sucursal) {
        super(sucursal);
        this.clientes = clientes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public double calcularComisionPorCliente(ArrayList<Cliente> clientes, String nombreCategoria) {
        /*double comisionFinalVenta = 0.0;
        double comisionFinalProducto = 0.0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalVenta += clientes.get(i).getCantidad();
            }
        }
        for (int j = 0; j < this.clientes.size(); j++) {
            if (this.clientes.get(j).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalProducto += this.clientes.get(j).getPorcentaje();
            }
        }
        comisionFinalProducto = comisionFinalVenta * comisionFinalProducto;
        return comisionFinalProducto;*/
        double totalGanado = 0.0;

        for (Cliente c : clientes) {
            if (c.getCategoria().equals(nombreCategoria)) {
                double cantidad = c.getCantidad();

                for (Cliente porcentajeDefinido : this.clientes) {
                    if (porcentajeDefinido.getCategoria().equals(nombreCategoria)) {
                        double porcentaje = porcentajeDefinido.getPorcentaje();
                        totalGanado += cantidad * porcentaje;
                        break;
                    }
                }
            }
        }

        return totalGanado;
    }

    /*public double calcularComisionTotal(Vendedor v) {
        Map<String, Double> tipoCliente = new HashMap<>();

        // 1. Agrupar los clientes y lo que se les vendio por tipo
        for (Cliente c : v.getClientes()) {
            String tipo = c.getCategoria();
            double venta = c.getCantidad();
            tipoCliente.put(tipo, tipoCliente.getOrDefault(tipo, 0.0) + venta);
        }

        double totalGanado = 0.0;

        // 2. Recorrer los tipos de cliente y multiplicar por su tarifa 
        for (Map.Entry<String, Double> entry : tipoCliente.entrySet()) {
            String tipo = entry.getKey();
            double cantidadVendida = entry.getValue();

            for (Comision c : v.getComisiones()) {
                if (c instanceof PorCliente) {
                    for (int i = 0; i < clientes.size(); i++) {
                        if (clientes.get(i).getCategoria().equals(tipo)) {
                            PorCliente tm = (PorCliente) c;
                            totalGanado += cantidadVendida * c.getPorcentaje();
                            break; // Ya encontramos el tipo, no hace falta seguir
                        }
                    }
                }

            }
        }

        return totalGanado;
    }*/
    public double calcularComisionFinal(Vendedor v) {
        double totalGanado = 0.0;

        for (Cliente c : v.getClientes()) {
            String tipoCliente = c.getCategoria();
            double cantidad = c.getCantidad();

            for (Cliente porcentajeDefinido : this.getClientes()) {
                if (porcentajeDefinido.getCategoria().equals(tipoCliente)) {
                    double porcentaje = porcentajeDefinido.getPorcentaje(); // Porcentaje como 0.5
                    totalGanado += cantidad * porcentaje;
                    break;
                }
            }
        }

        return totalGanado;
    }
    
    public double calcularComisionFinalFiltrada(ArrayList<Cliente> v) {
        double totalGanado = 0.0;

        for (Cliente c : v) {
            String tipoCliente = c.getCategoria();
            double cantidad = c.getCantidad();

            for (Cliente porcentajeDefinido : this.getClientes()) {
                if (porcentajeDefinido.getCategoria().equals(tipoCliente)) {
                    double porcentaje = porcentajeDefinido.getPorcentaje(); // Porcentaje como 0.5
                    totalGanado += cantidad * porcentaje;
                    break;
                }
            }
        }

        return totalGanado;
    }

}
