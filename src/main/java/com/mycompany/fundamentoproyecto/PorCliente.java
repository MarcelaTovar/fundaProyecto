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
    


    
    
}
