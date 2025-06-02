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
    ArrayList<Cliente> clientes = new ArrayList<>();
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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nombre=" + nombre + ", comisiones=" + comisiones + ", metas=" + metas + ", ventas=" + ventas.get(0) + ", firmaVendedor=" + firmaVendedor + ", tipo=" + tipo + ", Sucursal=" + Sucursal + ", clientes=" + clientes.get(0) + ", comisionTotal=" + comisionTotal + '}';
    }

    public double obtenerCantidadVentaPorCategoria(String nombre, String clienteOVenta) {
        double cantidadFinal = 0.0;
        if (clienteOVenta.equalsIgnoreCase("Cliente")) {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getCategoria().equals(nombre)) {
                    cantidadFinal += clientes.get(i).getCantidad();
                }
            }
        } else if (clienteOVenta.equalsIgnoreCase("Venta")) {
            for (int i = 0; i < ventas.size(); i++) {
                if (ventas.get(i).getCategoria().equals(nombre)) {
                    cantidadFinal += ventas.get(i).getMonto();
                }
            }
        }
        return cantidadFinal;

    }
    
    public ArrayList <Double>  obtenerTodo (){
     String[] Categorias = {
          "Aceites y Lubricante",
          "Bujías",
          "Filtros de Aire",
          "Equipo",
          "Filtros de Aceite",
          "Filtros Otros",
          "Fricciones pastilla",
          "Liquido y Aditivos",
          "Llantas Importadas",
          "Fricciones en bloque",
          "Plomo",
          "Tubos y Protectores",
          "Remaches",
          "Repuestos",
          "Rines",
          "Servicios",
          "Fricciones en rollo",
          "Baterías",
          "Llanta Local",
          "Combustible",
          "Alquiler",
          "Lubricantes Importad"};
    ArrayList <Double> montos = null;
        for (String categoria : Categorias) {
            montos.add(obtenerCantidadVentaPorCategoria(categoria,"Venta" ));
        }        
    return montos;
    }
    
    public double obtenerCantidadVentaPorCategoriaFiltrada(ArrayList <Venta> ventas,ArrayList<Cliente> clientes,String nombre, String clienteOVenta){
        //El arraylist cliente y el arraylist venta deben ser los del vendedorActivo
        double cantidadFinal = 0.0;
        if (clienteOVenta.equalsIgnoreCase("Cliente")) {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getCategoria().equals(nombre)) {
                    cantidadFinal += clientes.get(i).getCantidad();
                }
            }
        } else if (clienteOVenta.equalsIgnoreCase("Venta")) {
            for (int i = 0; i < ventas.size(); i++) {
                if (ventas.get(i).getCategoria().equals(nombre)) {
                    cantidadFinal += ventas.get(i).getMonto();
                }
            }
        }
        return cantidadFinal;
    }

    public boolean cumplioMeta(Meta m) {
        double totalVentas = 0.0;

        for (Venta venta : ventas) {
            totalVentas += venta.getMonto();
        }

        try {
            double meta = Double.parseDouble(m.getMeta());
            if (totalVentas >= meta) {
                m.setCompletado(true);
                return true;
            }else{
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Error: Meta no es un número válido -> " + m.getMeta());
            return false;
        }
    }

}
