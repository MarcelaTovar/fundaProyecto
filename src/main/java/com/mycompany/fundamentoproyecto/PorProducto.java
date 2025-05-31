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

    public double calcularComisionFinal(Vendedor v) {
        double totalGanado = 0.0;

        for (Venta venta : v.getVentas()) {
            String tipoVenta = venta.getCategoria();
            double cantidad = venta.getMonto();

            for (Producto porcentajeDefinido : this.getComisionPorProducto()) {
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

                for (Producto porcentajeDefinido : this.comisionPorProducto) {
                    if (porcentajeDefinido.getCategoria().equals(nombreCategoria)) {
                        double porcentaje = porcentajeDefinido.getPorcentajeComision();
                        totalGanado += cantidad * porcentaje;
                        break;
                    }
                }
            }
        }
        double num = Math.round(totalGanado * 100.0) / 100.0;
        return num;
    }
    public double calcularComisionFinalFiltrada(ArrayList <Venta> v) {
          double totalGanado = 0.0;

        for (Venta venta : v) {
            String tipoVenta = venta.getCategoria();
            double cantidad = venta.getMonto();

            for (Producto porcentajeDefinido : this.getComisionPorProducto()) {
                if (porcentajeDefinido.getCategoria().equals(tipoVenta)) {
                    double porcentaje = porcentajeDefinido.getPorcentajeComision(); // Porcentaje como 0.5
                    totalGanado += cantidad * porcentaje;
                    break;
                }
            }
        }

        return totalGanado;
    }

}
