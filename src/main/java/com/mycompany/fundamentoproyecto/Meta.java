/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

/**
 *
 * @author Osmin Tovar
 */
public class Meta {
    int bono;
    String meta;
    boolean completado;

    public Meta() {
    }

    public Meta(int bono, String meta, boolean completado) {
        this.bono = bono;
        this.meta = meta;
        this.completado = completado;
    }

    public int getBono() {
        return bono;
    }

    public void setBono(int bono) {
        this.bono = bono;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "Meta{" + "bono=" + bono + ", meta=" + meta + ", completado=" + completado + '}';
    }
    
}
