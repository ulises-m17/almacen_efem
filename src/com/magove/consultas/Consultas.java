/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magove.consultas;

import com.magove.coneccion.Coneccion;
import com.magove.controlador.material;
import com.magove.mensajes.Mensajes;
import com.magove.tablas.TablaDetalle;
import com.magove.tablas.TablaMaterial;
import com.magove.tablas.Tabla_entrada_salida;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dise 01
 */
public class Consultas {
    Coneccion obj = new Coneccion();  // instanciamos a la clase de la coneccion
 public Connection con = obj.Conexion();
 Mensajes m = new Mensajes();
 
 public Statement st;
 public ResultSet rs;
 
 
 
 
 /////////////////////////////////////// VARIABLES //////////////////////////////////////////////////
 
 public String t_material;
 public String usuario_ingresado;
 public String pasword;
 
 public int cantidad,cantidadInicial_consulta,existencias_consulta,minimo,maximo;
 public String material,dia;
 
  public static String contrasenia,usuario;
 public static String nombre_usuario,id_planta,idEmpleado,valor;
 
 
 

 
 
 
 
 
 
 
 /////////////////////////  LISTAS    /////////////////////////////////////////////////
 
 
 public ObservableList lista_tipoM = FXCollections.observableArrayList();
 public ObservableList lista_material = FXCollections.observableArrayList();
 public ObservableList<TablaDetalle> lista_detalle = FXCollections.observableArrayList();
 public ObservableList<TablaMaterial> lista_detalle_material= FXCollections.observableArrayList();
 public ObservableList<Tabla_entrada_salida> lista_entrada_salida= FXCollections.observableArrayList();
 
 
 
 /////////////////////////////   CONSULTAS  //////////////////////////////////////
 
 public String Consulta_usuario(){
    try {
        st=con.createStatement();
        rs=st.executeQuery("SELECT\n" +
"empleado.pasword,\n" +
"empleado.id_empleado,\n" +
"empleado.usuario,\n" +
"empleado.id_planta,\n" +
"empleado.nombre\n" +
"FROM\n" +
"empleado\n" +
"where usuario='"+usuario_ingresado+"'"); 
     
         while (rs.next()) {
             usuario=rs.getString("empleado.usuario");
             contrasenia=rs.getString("empleado.pasword");
             nombre_usuario=rs.getString("empleado.nombre");
             id_planta=rs.getString("empleado.id_planta");
             idEmpleado=rs.getString("empleado.id_empleado");
            
        }
         rs.close();
         
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
  
     return null;
     
 }
 
 public String Consulta_tiposMaterial(){
    
     try {
         st=con.createStatement();
         rs=st.executeQuery("SELECT tipo_material from tipo_material ORDER BY tipo_material ASC;");
         while (rs.next()) {             
         
             lista_tipoM.add(rs.getString("tipo_material"));
             
         }
         
      rs.close();
     } catch (Exception e) {
         m.perdio_conexion();
     }
     
     
     
     return null;
 }
 
 public String Consulta_material(){
     
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT codigo from material WHERE id_tipo_material=(SELECT id_tipo_material FROM\n" +
                    "tipo_material WHERE tipo_material ='"+t_material+"') ORDER BY codigo ASC;");
            
            while (rs.next()) {                
                lista_material.add(rs.getString("codigo"));
            }
            
        rs.close();
        } catch (SQLException ex) {
            m.perdio_conexion();
        } 
        return null;
 }
 
 
 public Boolean carga_material(){
       
        try {
            st=con.createStatement();
            st.executeUpdate("INSERT entrada_material VALUES "
     + "(null,NOW(),'"+cantidad+"',(SELECT id_material from material WHERE codigo='"+material+"'),'"+idEmpleado+"')");
                    
                    return true;
        } catch (SQLException ex) {
           m.perdio_conexion();
        }
        return false;
 }
 
 public String Consulta_existencias(){
        
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT\n" +
"detalle_material.cantidad_inicial,\n" +
"detalle_material.minimo,\n" +
"detalle_material.maximo,\n" +
"detalle_material.existencias\n" +
"FROM\n" +
"detalle_material\n" +
"WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and \n" +
"id_planta='"+id_planta+"'");
            
            while (rs.next()) {

              cantidadInicial_consulta=rs.getInt("cantidad_inicial");
                      existencias_consulta=rs.getInt("existencias");
                      minimo=rs.getInt("minimo");
                      maximo=rs.getInt("maximo");
                     
                
            }
                      rs.close();
 
        } catch (SQLException ex) {
          m.perdio_conexion();
        }
        return null;
 } 
 
 public Boolean actualiza_detalle_material(int c, int e, int estatus){
        try {
            st=con.createStatement();
            st.executeUpdate("UPDATE detalle_material SET fecha_cantidad_inicial=NOW(), cantidad_inicial='"+c+"', existencias='"+e+"',id_estatus='"+estatus+"' WHERE\n" +
"id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
              
            return true;
        } catch (SQLException ex) {
           m.perdio_conexion();
        }
        return false;
 }
 
 public Boolean salida_material(){ 
   try {
     st=con.createStatement();
   st.executeUpdate("INSERT INTO salida_material VALUES"
       + " (null,NOW(), '"+cantidad+"',(SELECT id_material from material WHERE codigo='"+material+"'), '"+idEmpleado+"')");
            
            
            return true;
            
            
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
        return false;
 }
 
 public Boolean actualiza_detalle_material_salida(int nuevaExis,int id){
     try {
         st=con.createStatement();    

         st.executeUpdate("UPDATE detalle_material SET existencias='"+nuevaExis+"',id_estatus='"+id+"' "
                     + "WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
        return true;
     } 
     
         catch (SQLException ex) {
              m.perdio_conexion();
         }
      
     
     
     return false;
 }
 
  public void actualiza_detalle_material_salida_pf(int cantidad_fabricar){
     try {
         st=con.createStatement();    

         st.executeUpdate("UPDATE detalle_material SET cantidad_por_fabricar='"+cantidad_fabricar+"'"
                     + "WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
       
     } 
     
    
         catch (SQLException ex) {
              m.perdio_conexion();
         }
 
 }
 
 
 public  String llena_tabla_en_salida(){
     
        try {
            st= con.createStatement();
            rs=st.executeQuery("SELECT\n" +
"material.codigo,\n" +
"detalle_material.existencias,\n" +
"estatus.estatus,\n" +
"detalle_material.minimo,\n" +
"detalle_material.maximo,\n" +
"detalle_material.cantidad_por_fabricar,\n" +
"detalle_material.tiempo_de_reposicion\n" +
"FROM\n" +
"material\n" +
"INNER JOIN detalle_material ON material.id_material = detalle_material.id_material\n" +
"INNER JOIN estatus ON estatus.id_estatus = detalle_material.id_estatus\n" +
"INNER JOIN tipo_material ON tipo_material.id_tipo_material = material.id_tipo_material\n" +
"WHERE material.id_tipo_material=(SELECT id_tipo_material from tipo_material WHERE tipo_material='"+t_material+"')"
                    + " AND id_planta='"+id_planta+"'");
            
            while (rs.next()) {
lista_detalle.add(new TablaDetalle(rs.getString("material.codigo"),rs.getInt("detalle_material.existencias"),rs.getString("estatus.estatus"),rs.getInt("detalle_material.minimo"),
        rs.getInt("detalle_material.maximo"),rs.getString("detalle_material.tiempo_de_reposicion"),rs.getInt("cantidad_por_fabricar")));
                
            }
            
            rs.close();
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
return null;
 }
 
 public void Consulta_detalle_material(){
   
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT\n" +
                    "detalle_material.existencias,\n" +
                    "material.codigo,\n" +
                    "detalle_material.minimo,\n" +
                    "detalle_material.maximo,\n" +
                    "detalle_material.precio_unitario,\n" +
                    "estatus.estatus,\n" +
                    "detalle_material.fecha_inicio_produccion,\n" +
                    "detalle_material.tiempo_de_reposicion,\n" +
                    "detalle_material.cantidad_por_fabricar,\n" +
                    "detalle_material.fecha_entrega\n" +
                    "FROM\n" +
                    "material\n" +
                    "INNER JOIN detalle_material ON material.id_material = detalle_material.id_material\n" +
                    "INNER JOIN estatus ON estatus.id_estatus = detalle_material.id_estatus\n" +
                    "INNER JOIN tipo_material ON tipo_material.id_tipo_material = material.id_tipo_material\n" +
                    "INNER JOIN planta ON planta.id_planta = detalle_material.id_planta\n" +
                    "WHERE material.id_tipo_material=(SELECT id_tipo_material from tipo_material WHERE tipo_material='"+t_material+"') AND detalle_material.id_planta='"+id_planta+"'");
            
            while (rs.next()) {
                
                lista_detalle_material.add(new TablaMaterial(rs.getString("material.codigo"),rs.getString("detalle_material.existencias"),rs.getString("detalle_material.minimo"),
                        rs.getString("detalle_material.maximo"),rs.getString("estatus.estatus"),rs.getString("detalle_material.fecha_inicio_produccion"),rs.getString("detalle_material.tiempo_de_reposicion")
                        ,rs.getString("detalle_material.cantidad_por_fabricar"),rs.getString("detalle_material.fecha_entrega"),rs.getString("precio_unitario")));
                
            }
            
            rs.close();
        } catch (SQLException ex) {
           m.perdio_conexion();
        }
            
       
 }
 
 
  public void Consulta_entrgas_hoy(){
   
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT\n" +
"detalle_material.existencias,\n" +
"material.codigo,\n" +
"detalle_material.minimo,\n" +
"detalle_material.maximo,\n" +
"estatus.estatus,\n" +
"detalle_material.fecha_inicio_produccion,\n" +
"detalle_material.precio_unitario,\n" +
"detalle_material.tiempo_de_reposicion,\n" +
"detalle_material.cantidad_por_fabricar,\n" +
"detalle_material.fecha_entrega\n" +
"FROM\n" +
"material\n" +
"INNER JOIN detalle_material ON material.id_material = detalle_material.id_material\n" +
"INNER JOIN estatus ON estatus.id_estatus = detalle_material.id_estatus\n" +
"INNER JOIN tipo_material ON tipo_material.id_tipo_material = material.id_tipo_material\n" +
"INNER JOIN planta ON planta.id_planta = detalle_material.id_planta\n" +
"WHERE fecha_entrega=(SELECT DATE(NOW())) AND detalle_material.id_planta='"+id_planta+"'");
            
            while (rs.next()) {
                
                lista_detalle_material.add(new TablaMaterial(rs.getString("material.codigo"),rs.getString("detalle_material.existencias"),rs.getString("detalle_material.minimo"),
                        rs.getString("detalle_material.maximo"),rs.getString("estatus.estatus"),rs.getString("detalle_material.fecha_inicio_produccion"),rs.getString("detalle_material.tiempo_de_reposicion")
                        ,rs.getString("detalle_material.cantidad_por_fabricar"),rs.getString("detalle_material.fecha_entrega"),rs.getString("precio_unitario")));
                
            }
            rs.close();
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
            
       
 }
 
 
 public Boolean actualiza_detalle( int minimo, int maximo, int tiempo, int cantidad,double precio){
      
        try {
            st=con.createStatement();
            st.executeUpdate("UPDATE detalle_material SET \n" +
                    "minimo='"+minimo+"',maximo='"+maximo+"',precio_unitario='"+precio+"',cantidad_por_fabricar='"+cantidad+"', tiempo_de_reposicion='"+tiempo+"' \n" +
                            "WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
            
            return true;
           
            
      
        } catch (SQLException ex) {
           m.perdio_conexion();
        }    
        return false;
 }
 
 public Boolean inserta_nuevo_material(String codigo, String tipo){
        try {
            st=con.createStatement();
            st.executeUpdate("INSERT INTO material  VALUES (null,'"+codigo+"',(SELECT id_tipo_material FROM tipo_material where tipo_material='"+tipo+"'))");
            return true;
        } catch (SQLException ex) {
           m.perdio_conexion();
        }
     return false;
 }
 
 public Boolean inserta_nuevo_detalle(int cantidad,int minimo,int maximo, int dias,double precio,String material){
        try {
  st=con.createStatement();
 st.executeUpdate("INSERT INTO detalle_material (fecha_cantidad_inicial,cantidad_inicial, existencias, minimo, maximo,fecha_inicio_produccion,cantidad_por_fabricar, tiempo_de_reposicion,fecha_entrega,precio_unitario,id_estatus, id_material, id_planta) \n" +
"VALUES (NOW(),'"+cantidad+"', '"+cantidad+"', '"+minimo+"', '"+maximo+"','0000-00-00', '0', '"+dias+"','0000-00-00','"+precio+"', '2', (SELECT material.id_material FROM material WHERE codigo='"+material+"'), '"+id_planta+"')");
            
            return true;
            
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
        return false;
 }
 
 public Boolean programa_produccion(String fecha, int tiempo, String material){
     
        try {
            st=con.createStatement();
            st.executeUpdate("UPDATE detalle_material SET fecha_inicio_produccion='"+fecha+"',id_estatus=1, "
            + "fecha_entrega=(select DATE_ADD('"+fecha+"',INTERVAL '"+tiempo+"' DAY)) "
    + "WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
            return true;
        } catch (SQLException ex) {
          m.perdio_conexion();
        }
        return false;
 }
 
 public void consulta_fecha_entrega(){
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT DAYNAME((SELECT\n" +
"detalle_material.fecha_entrega\n" +
"FROM\n" +
"detalle_material WHERE detalle_material.id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"')) as 'dia'");
        
         while (rs.next()) {         
            dia=rs.getString("dia");
     }
         rs.close();
        }
       
        catch (SQLException ex) {
          m.perdio_conexion();
        }
 }
 
  public void actualizadias__produccion(String fecha, int tiempo, String material){
     
        try {
            st=con.createStatement();
            st.executeUpdate("UPDATE detalle_material SET fecha_entrega=(select DATE_ADD('"+fecha+"',INTERVAL '"+tiempo+"' DAY)) "
    + "WHERE id_material=(SELECT id_material from material where codigo='"+material+"') and id_planta='"+id_planta+"'");
            
        } catch (SQLException ex) {
          m.perdio_conexion();
        }
       
 }
  
  
  
  public void reporte_salidas(){
   
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT\n" +
"material.codigo,\n" +
"salida_material.cantidad,\n" +
"salida_material.fecha_salida,\n" +
"empleado.nombre\n" +
"FROM\n" +
"salida_material\n" +
"INNER JOIN material ON material.id_material = salida_material.id_material\n" +
"INNER JOIN empleado ON empleado.id_empleado = salida_material.id_empleado\n" +
"WHERE empleado.id_planta='"+id_planta+"'\n" +
"ORDER BY fecha_salida DESC");
            
            while (rs.next()) {
                
               lista_entrada_salida.add(new Tabla_entrada_salida(rs.getString("codigo"), rs.getString("cantidad"),rs.getString("fecha_salida"), rs.getString("nombre")) );
                
            }
            rs.close();
        } catch (SQLException ex) {
            m.perdio_conexion();
        }
            
        }
        
 public void reporte_entradas(){
   
        
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT\n" +
                    "material.codigo,\n" +
                    "entrada_material.cantidad,\n" +
                    "entrada_material.fecha_ingreso,\n" +
                    "empleado.nombre\n" +
                    "FROM\n" +
                    "entrada_material\n" +
                    "INNER JOIN material ON entrada_material.id_material = material.id_material\n" +
                    "INNER JOIN empleado ON entrada_material.id_empleado = empleado.id_empleado\n" +
                    "WHERE empleado.id_planta='"+id_planta+"'\n" +
                            "ORDER BY fecha_ingreso DESC");
            
            while (rs.next()) {
                
                lista_entrada_salida.add(new Tabla_entrada_salida(rs.getString("codigo"), rs.getString("entrada_material.cantidad"),rs.getString("entrada_material.fecha_ingreso"),
                        rs.getString("nombre")) );
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
       
 }
 
 public String valor_almacen(){
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT ROUND(Sum((detalle_material.existencias * detalle_material.precio_unitario)),2)  AS 'total' FROM detalle_material");
       
            while (rs.next()) {                
                valor=rs.getString("total");
            }
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "El valor del almacén es:  "+valor+" MXN.";
 }
  
  

  
 
 /////////////////////////// FALTA AÑADIR LA CONSULTA DONDE SE ACTUALIZA 
 
}
