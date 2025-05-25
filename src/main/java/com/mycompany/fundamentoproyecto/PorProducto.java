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
public class PorProducto extends Comision implements Serializable {
     private static final long serialVersionUID = 1L;
     ArrayList <Producto> comisionPorProducto = new ArrayList();

     
    public PorProducto() {
    }

    public PorProducto(int sucursal, double porcentaje) {
        super(sucursal, porcentaje);
    }

    public PorProducto(ArrayList<Producto> comisionPorProducto) {
        this.comisionPorProducto = comisionPorProducto;
    }

    public ArrayList<Producto> getComisionPorProducto() {
        return comisionPorProducto;
    }

    public void setComisionPorProducto(ArrayList<Producto> comisionPorProducto) {
        this.comisionPorProducto = comisionPorProducto;
    }
    
    public void calcularComision(){
        for (int i = 0; i < comisionPorProducto.size(); i++) {
            comisionFinal += comisionPorProducto.get(i).comision();
        }
    }
    
    
}
