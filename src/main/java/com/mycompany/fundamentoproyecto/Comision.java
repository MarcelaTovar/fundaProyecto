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
public class Comision implements Serializable {
    int sucursal;
    double porcentaje;
     private static final long serialVersionUID = 1L;

    public Comision() {
    }

    public Comision(int sucursal, double porcentaje) {
        this.sucursal = sucursal;
        this.porcentaje = porcentaje;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Comision{" + "sucursal=" + sucursal + ", porcentaje=" + porcentaje + '}';
    }
    
}
