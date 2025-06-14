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
public class Cliente implements Serializable{
    String id;
    String nombre;
    String fecha;
    double cantidad;
    double porcentaje;
    String categoria;

    public Cliente(String id, String nombre, String fecha, double cantidad,String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public Cliente(double porcentaje, String categoria) {
        this.porcentaje = porcentaje;
        this.categoria = categoria;
    }
    

    
    public Cliente() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", cantidad=" + cantidad + ", porcentaje=" + porcentaje + '}';
    }

    

    

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
