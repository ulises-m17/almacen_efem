/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magove.controlador;

import com.magove.coneccion.Coneccion;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dise 01
 */
public class Inicio extends Application{

    
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root =FXMLLoader.load(getClass().getResource("/com/magove/vista/inicio.fxml"));
         Scene ecena= new Scene(root);
         primaryStage.setScene(ecena);   
        primaryStage.setTitle("Acceso a almacén");
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/com/magove/recursos/Magove (1).png"));
        primaryStage.show();  
        
    }
    
 
    public static void main(String [] args){
        launch(args);
        Coneccion c= new Coneccion();
        c.Conexion();
        
    }
    
}
