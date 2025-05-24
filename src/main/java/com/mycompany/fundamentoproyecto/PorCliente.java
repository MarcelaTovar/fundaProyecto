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
public class PorCliente extends Comision{

    public PorCliente() {
    }

    public PorCliente(int sucursal, double porcentaje) {
        super(sucursal, porcentaje);
    }
    
}
