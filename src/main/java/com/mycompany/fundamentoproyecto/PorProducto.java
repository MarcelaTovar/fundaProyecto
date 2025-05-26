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

    public PorProducto(int sucursal) {
        super(sucursal);
    }

    public PorProducto(ArrayList<Producto> comisionPorProducto,int sucursal) {
        super(sucursal);
        this.comisionPorProducto = comisionPorProducto;
    }
    
    

    public ArrayList<Producto> getComisionPorProducto() {
        return comisionPorProducto;
    }

    public void setComisionPorProducto(ArrayList<Producto> comisionPorProducto) {
        this.comisionPorProducto = comisionPorProducto;
    }
    
    public double calcularComision(ArrayList <Venta> ventas){
        for (int i = 0; i < ventas.size(); i++) {
            for (int j = 0; j < comisionPorProducto.size(); j++) {
                if (ventas.get(i).getCategoria().equals(comisionPorProducto.get(j).getCategoria())) {
                    comisionFinal = ventas.get(i).getMonto() * comisionPorProducto.get(j).getPorcentajeComision();
                }
            }
        }
        return comisionFinal;
    }
    
    
}
