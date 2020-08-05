/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magove.coneccion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dise 01
 */
public class Coneccion {
    
    public Connection con;
    
    public Connection Conexion(){
        
        
        try {
          
                 Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/efem?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8","root","");
             System.out.println("si se pudo conectar");
           
           
        } catch (Exception e) {
            System.out.println("no se pudo conectar");
        }
        
                return con;
    }
}
