/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magove.tablas;

/**
 *
 * @author Dise 01
 */
public class TablaDetalle {

    /**
     * @return the cantidad_por_fabricar
     */
    public int getCantidad_por_fabricar() {
        return cantidad_por_fabricar;
    }

    /**
     * @param cantidad_por_fabricar the cantidad_por_fabricar to set
     */
    public void setCantidad_por_fabricar(int cantidad_por_fabricar) {
        this.cantidad_por_fabricar = cantidad_por_fabricar;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the minimo
     */
    public int getMinimo() {
        return minimo;
    }

    /**
     * @param minimo the minimo to set
     */
    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    /**
     * @return the maximo
     */
    public int getMaximo() {
        return maximo;
    }

    /**
     * @param maximo the maximo to set
     */
    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    /**
     * @return the tiempo_reposicion
     */
    public String getTiempo_reposicion() {
        return tiempo_reposicion;
    }

    /**
     * @param tiempo_reposicion the tiempo_reposicion to set
     */
    public void setTiempo_reposicion(String tiempo_reposicion) {
        this.tiempo_reposicion = tiempo_reposicion;
    }
    private String codigo;
    private int existencias;
    private String estatus;
    private int minimo;
    private int maximo;
    private String tiempo_reposicion;
    private int cantidad_por_fabricar;
    
    public TablaDetalle(String codigo,int existencias,String estatus,int minimo, int maximo,String tiempo_reposicion,int cantidad_por_fabricar){
        this.codigo=codigo;
        this.estatus=estatus;
        this.existencias=existencias;
        this.maximo=maximo;
        this.minimo=minimo;
        this.tiempo_reposicion=tiempo_reposicion;
        this.cantidad_por_fabricar=cantidad_por_fabricar;
    }
    
    
}
