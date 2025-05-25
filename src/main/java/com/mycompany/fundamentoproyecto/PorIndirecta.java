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
public class PorIndirecta extends Comision{
    Vendedor vendedorDelQueRecibe = new Vendedor();

    public PorIndirecta() {
    }

    public PorIndirecta(Vendedor vendedorDelQueRecibe, int sucursal) {
        super(sucursal);
        this.vendedorDelQueRecibe = vendedorDelQueRecibe;
    }

    public Vendedor getVendedorDelQueRecibe() {
        return vendedorDelQueRecibe;
    }

    public void setVendedorDelQueRecibe(Vendedor vendedorDelQueRecibe) {
        this.vendedorDelQueRecibe = vendedorDelQueRecibe;
    }

    
    
    
    
}
