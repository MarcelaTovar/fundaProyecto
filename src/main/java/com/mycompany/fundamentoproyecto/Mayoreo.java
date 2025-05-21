/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.util.ArrayList;

/**
 *
 * @author Osmin Tovar
 */
public class Mayoreo extends Vendedor{
    String sucursal;

    public Mayoreo() {
    }

    public Mayoreo(String sucursal, String id, ArrayList<Comision> comisiones, ArrayList<Meta> metas, ArrayList<Venta> ventas, String firmaVendedor) {
        super(id, comisiones, metas, ventas, firmaVendedor);
        this.sucursal = sucursal;
    }
    
    

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Mayoreo{" + "sucursal=" + sucursal + '}';
    }
    
}
