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
public class Venta {
    ArrayList <Producto> productos;
    String fecha;
    boolean propia;

    public Venta() {
    }

    public Venta(ArrayList<Producto> productos, String fecha, boolean propia) {
        this.productos = productos;
        this.fecha = fecha;
        this.propia = propia;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPropia() {
        return propia;
    }

    public void setPropia(boolean propia) {
        this.propia = propia;
    }

    @Override
    public String toString() {
        return "Venta{" + "productos=" + productos + ", fecha=" + fecha + ", propia=" + propia + '}';
    }
    
    
}
