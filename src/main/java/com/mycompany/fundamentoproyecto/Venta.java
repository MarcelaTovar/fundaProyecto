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
public class Venta implements Serializable{
    String categoria;
    String fecha;
    double monto;
    boolean propia;
    String sucursal;

    public Venta() {
    }

    public Venta(String categoria, String fecha) {
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public Venta(String categoria, String fecha, double monto, String sucursal) {
        this.categoria = categoria;
        this.fecha = fecha;
        this.monto = monto;
        this.sucursal = sucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Venta{" + "categoria=" + categoria + ", fecha=" + fecha + ", monto=" + monto + '}';
    }

    
    
    
    
}
