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
    private static final long serialVersionUID = 1L;
    double comisionFinal;

    public Comision() {
    }

    public Comision(int sucursal) {
        this.sucursal = sucursal;
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
    
}
