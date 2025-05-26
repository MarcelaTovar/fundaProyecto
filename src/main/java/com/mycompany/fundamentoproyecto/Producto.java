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
    private static final long serialVersionUID = 1L;
    String categoria;
    int id;
    double porcentajeComision;
    double comision;

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

  

    public void setComision(double comision) {
        this.comision = comision;
    }
    
    

    

    @Override
    public String toString() {
        return "Producto{" + "categoria=" + categoria + ", id=" + id + '}';
    }
    
    
    
    
}
