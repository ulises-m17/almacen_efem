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
public class TablaMaterial {

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
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
    public String getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    /**
     * @return the minimo
     */
    public String getMinimo() {
        return minimo;
    }

    /**
     * @param minimo the minimo to set
     */
    public void setMinimo(String minimo) {
        this.minimo = minimo;
    }

    /**
     * @return the maximo
     */
    public String getMaximo() {
        return maximo;
    }

    /**
     * @param maximo the maximo to set
     */
    public void setMaximo(String maximo) {
        this.maximo = maximo;
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
     * @return the fecha_inicio
     */
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
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

    /**
     * @return the cantidad_fabricar
     */
    public String getCantidad_fabricar() {
        return cantidad_fabricar;
    }

    /**
     * @param cantidad_fabricar the cantidad_fabricar to set
     */
    public void setCantidad_fabricar(String cantidad_fabricar) {
        this.cantidad_fabricar = cantidad_fabricar;
    }

    /**
     * @return the fecha_entrega
     */
    public String getFecha_entrega() {
        return fecha_entrega;
    }

    /**
     * @param fecha_entrega the fecha_entrega to set
     */
    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    private String codigo;
    private String existencias;
    private String minimo;
    private String maximo;
    private String estatus;
    private String fecha_inicio;
    private String tiempo_reposicion;
    private String cantidad_fabricar;
    private String fecha_entrega;
    private String precio;
    
    
    public TablaMaterial(String codigo,String existencias,String minimo,String maximo,String estatus, String fecha_inicio, String tiempo_reposicion,
            String cantidad_fabricar, String fecha_entrega,String precio ){
        
     this.codigo=codigo;
     this.existencias=existencias;
     this.minimo=minimo;
  this.maximo=maximo;
    this.estatus=estatus;
    this.fecha_inicio=fecha_inicio;
    this.tiempo_reposicion=tiempo_reposicion;
    this.cantidad_fabricar=cantidad_fabricar;
    this.fecha_entrega=fecha_entrega;
    this.precio=precio;
    
        
    }
}
