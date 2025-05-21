/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

/**
 *
 * @author Osmin Tovar
 */
public class Producto {
    String categoria;
    String nombre;
    int valor;

    public Producto() {
    }

    public Producto(String categoria, String nombre, int valor) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Producto{" + "categoria=" + categoria + ", nombre=" + nombre + ", valor=" + valor + '}';
    }
    
    
}
