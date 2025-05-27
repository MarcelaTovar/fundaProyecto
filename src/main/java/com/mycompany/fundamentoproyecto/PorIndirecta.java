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
public class PorIndirecta extends Comision{
    Vendedor vendedorDelQueRecibe = new Vendedor();
    ArrayList <Producto> productosComisionados = new ArrayList ();
  

    
    public PorIndirecta() {
    }

    public PorIndirecta(Vendedor vendedorDelQueRecibe, int sucursal,ArrayList <Producto> productosComisionados  ) {
        super(sucursal);
        this.vendedorDelQueRecibe = vendedorDelQueRecibe;
        this.productosComisionados = productosComisionados;
    }

    public Vendedor getVendedorDelQueRecibe() {
        return vendedorDelQueRecibe;
    }

    public void setVendedorDelQueRecibe(Vendedor vendedorDelQueRecibe) {
        this.vendedorDelQueRecibe = vendedorDelQueRecibe;
    }

    

    public ArrayList<Producto> getProductosComisionados() {
        return productosComisionados;
    }

    public void setProductosComisionados(ArrayList<Producto> productosComisionados) {
        this.productosComisionados = productosComisionados;
    }
    
    

 

    
    /*public double calcularComisionFinal(ArrayList<Venta> ventas) {
        for (int i = 0; i < ventas.size(); i++) {
            for (int j = 0; j < comisionPorProducto.size(); j++) {
                if (ventas.get(i).getCategoria().equalsIgnoreCase(comisionPorProducto.get(j).getCategoria())) {
                    comisionFinal += ventas.get(i).getMonto() * comisionPorProducto.get(j).getPorcentajeComision();
                }
            }
        }
        return comisionFinal;
    }*/

    public double calcularComisionPorProducto(ArrayList<Venta> ventas, String nombreCategoria) {
        double comisionFinalVenta = 0.0;
        double comisionFinalProducto = 0.0;
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalVenta += ventas.get(i).getMonto();
            }
        }
        for (int j = 0; j < productosComisionados.size(); j++) {
            if (productosComisionados.get(j).getCategoria().equalsIgnoreCase(nombreCategoria)) {
                comisionFinalProducto += productosComisionados.get(j).getPorcentajeComision();
            }
        }
        comisionFinalProducto = comisionFinalVenta * comisionFinalProducto;
        return comisionFinalProducto;
    }
    
}
