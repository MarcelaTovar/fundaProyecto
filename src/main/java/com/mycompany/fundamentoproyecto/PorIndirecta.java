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
public class PorIndirecta extends Comision {

    Vendedor vendedorDelQueRecibe = new Vendedor();
    ArrayList<Producto> productosComisionados = new ArrayList();

    public PorIndirecta() {
    }

    public PorIndirecta(Vendedor vendedorDelQueRecibe, int sucursal, ArrayList<Producto> productosComisionados) {
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

    public double calcularComisionFinal(ArrayList<Venta> ventas) {
        double totalGanado = 0.0;

        for (Venta venta : ventas) {
            String tipoVenta = venta.getCategoria();
            double cantidad = venta.getMonto();

            for (Producto porcentajeDefinido : this.getProductosComisionados()) {
                if (porcentajeDefinido.getCategoria().equals(tipoVenta)) {
                    double porcentaje = porcentajeDefinido.getPorcentajeComision(); // Porcentaje como 0.5
                    totalGanado += cantidad * porcentaje;
                    break;
                }
            }
        }

        return totalGanado;
    }

    public double calcularComisionPorProducto(ArrayList<Venta> ventas, String nombreCategoria) {
        double totalGanado = 0.0;

        for (Venta v : ventas) {
            if (v.getCategoria().equals(nombreCategoria)) {
                double cantidad = v.getMonto();

                for (Producto porcentajeDefinido : this.getProductosComisionados()) {
                    if (porcentajeDefinido.getCategoria().equals(nombreCategoria)) {
                        double porcentaje = porcentajeDefinido.getPorcentajeComision();
                        totalGanado += cantidad * porcentaje;
                        break;
                    }
                }
            }
        }
        return totalGanado;
    }

}
