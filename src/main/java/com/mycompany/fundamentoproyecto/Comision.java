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
public class Comision implements Serializable {
    int sucursal;
    double porcentaje;
    private static final long serialVersionUID = 1L;
    double comisionFinal;
    boolean porVenta = false;
    boolean porCobro = false;

    public Comision() {
    }

    public Comision(int sucursal) {
        this.sucursal = sucursal;
    }

    public Comision(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }



    @Override
    public String toString() {
        return "Comision{" + "sucursal=" + sucursal;
    }

    public double getComisionFinal() {
        return comisionFinal;
    }

    public void setComisionFinal(double comisionFinal) {
        this.comisionFinal = comisionFinal;
    }
    
    public double comisionSinImportar(ArrayList <Venta> ventas){
        double temp = 0.0;
        for (int i = 0; i < ventas.size(); i++) {
            temp += ventas.get(i).getMonto();
        }
        comisionFinal = (temp * porcentaje);
        return comisionFinal;
    }
    public double comisionPorCobro(ArrayList <Cliente> clientes){
        double temp = 0.0;
        for (int i = 0; i < clientes.size(); i++) {
            temp += clientes.get(i).getCantidad();
        }
        comisionFinal = (temp * porcentaje);
        return comisionFinal;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isPorVenta() {
        return porVenta;
    }

    public void setPorVenta(boolean porVenta) {
        this.porVenta = porVenta;
    }

    public boolean isPorCobro() {
        return porCobro;
    }

    public void setPorCobro(boolean porCobro) {
        this.porCobro = porCobro;
    }
    
}
