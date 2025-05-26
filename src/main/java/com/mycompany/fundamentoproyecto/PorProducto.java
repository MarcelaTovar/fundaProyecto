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
    ArrayList<Producto> comisionPorProducto = new ArrayList();

    public PorProducto() {
    }

    public PorProducto(int sucursal) {
        super(sucursal);
    }

    public PorProducto(ArrayList<Producto> comisionPorProducto, int sucursal) {
        super(sucursal);
        this.comisionPorProducto = comisionPorProducto;
    }

    public ArrayList<Producto> getComisionPorProducto() {
        return comisionPorProducto;
    }

    public void setComisionPorProducto(ArrayList<Producto> comisionPorProducto) {
        this.comisionPorProducto = comisionPorProducto;
    }

    public double calcularComisionFinal(ArrayList<Venta> ventas) {
        for (int i = 0; i < ventas.size(); i++) {
            for (int j = 0; j < comisionPorProducto.size(); j++) {
                if (ventas.get(i).getCategoria().equalsIgnoreCase(comisionPorProducto.get(j).getCategoria())) {
                    comisionFinal += ventas.get(i).getMonto() * comisionPorProducto.get(j).getPorcentajeComision();
                }
            }
        }
        return comisionFinal;
    }

    public double calcularComisionPorProducto(ArrayList<Venta> ventas, String nombreCategoria) {
        double comisionFinalVenta = 0.0;
        double comisionFinalProducto = 0.0;
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalVenta += ventas.get(i).getMonto();
            }
        }
        for (int j = 0; j < comisionPorProducto.size(); j++) {
            if (comisionPorProducto.get(j).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalProducto += comisionPorProducto.get(j).getPorcentajeComision();
            }
        }
        comisionFinalProducto = comisionFinalVenta * comisionFinalProducto;
        return comisionFinalProducto;
    }

}
