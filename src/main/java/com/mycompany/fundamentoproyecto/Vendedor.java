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
public class Vendedor {
    String id;
    ArrayList <Comision> comisiones;
    ArrayList <Meta> metas;
    ArrayList <Venta> ventas;
    String firmaVendedor;

    public Vendedor() {
    }

    public Vendedor(String id, ArrayList<Comision> comisiones, ArrayList<Meta> metas, ArrayList<Venta> ventas, String firmaVendedor) {
        this.id = id;
        this.comisiones = comisiones;
        this.metas = metas;
        this.ventas = ventas;
        this.firmaVendedor = firmaVendedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(ArrayList<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public ArrayList<Meta> getMetas() {
        return metas;
    }

    public void setMetas(ArrayList<Meta> metas) {
        this.metas = metas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public String getFirmaVendedor() {
        return firmaVendedor;
    }

    public void setFirmaVendedor(String firmaVendedor) {
        this.firmaVendedor = firmaVendedor;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", comisiones=" + comisiones + ", metas=" + metas + ", ventas=" + ventas + ", firmaVendedor=" + firmaVendedor + '}';
    }

    
    
    
}
