/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Osmin Tovar
 */
public class PorCliente extends Comision{
    ArrayList <Cliente> clientes = new ArrayList();
    
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
        double comisionFinalVenta = 0.0;
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
        return comisionFinalProducto;
    }


    
    
}
