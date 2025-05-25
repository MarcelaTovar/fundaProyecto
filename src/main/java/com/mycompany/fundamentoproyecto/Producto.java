/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.io.Serializable;

/**
 *
 * @author Osmin Tovar
 */
public class Producto implements Serializable{
    String categoria;
    int id;
    double porcentajeComision;
    int cantidadVendida;

    public Producto() {
    }

    public Producto(String categoria, int id) {
        this.categoria = categoria;
        this.id = id;
    }

    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double comision(){
        double comision = 0;
        comision = 10000 * porcentajeComision;
        return comision;
    }
    

    @Override
    public String toString() {
        return "Producto{" + "categoria=" + categoria + ", id=" + id + '}';
    }
    
    
    
    
}
