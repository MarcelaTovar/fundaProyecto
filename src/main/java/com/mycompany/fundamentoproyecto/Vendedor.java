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
public class Vendedor implements Serializable {

    String id;
    String nombre;
    ArrayList<Comision> comisiones = new ArrayList<>();
    ArrayList<Meta> metas = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    String firmaVendedor;
    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> Sucursal = new ArrayList<>();
    int comisionTotal;
    private static final long serialVersionUID = 1L;

    public Vendedor() {
    }

    public Vendedor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Vendedor(String nombre, String id, ArrayList<Comision> comisiones, ArrayList<Meta> metas, ArrayList<Venta> ventas, String firmaVendedor, ArrayList<String> tipo, ArrayList<String> Sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.comisiones = comisiones;
        this.metas = metas;
        this.ventas = ventas;
        this.firmaVendedor = firmaVendedor;
        this.tipo = tipo;
        this.Sucursal = Sucursal;
    }

    public int getComisionTotal() {
        return comisionTotal;
    }

    public void setComisionTotal(int comisionTotal) {
        this.comisionTotal = comisionTotal;
    }

    public ArrayList<String> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<String> tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getSucursal() {
        return Sucursal;
    }

    public void setSucursal(ArrayList<String> Sucursal) {
        this.Sucursal = Sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Vendedor{" + "id=" + id + ", nombre=" + nombre + ", comisiones=" + comisiones + ", metas=" + metas + ", ventas=" + ventas.get(0) + ", firmaVendedor=" + firmaVendedor + ", tipo=" + tipo + ", Sucursal=" + Sucursal + '}';
    }

}
