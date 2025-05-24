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
public class PorProducto extends Comision implements Serializable {
     private static final long serialVersionUID = 1L;
     
    public PorProducto() {
    }

    public PorProducto(int sucursal, double porcentaje) {
        super(sucursal, porcentaje);
    }
    
    
    
}
