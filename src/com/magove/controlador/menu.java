package com.magove.controlador;

import com.jfoenix.controls.JFXButton;
import com.magove.consultas.Consultas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class menu implements Initializable{

    @FXML
    private Label lb_usuario;

    
    @FXML
    private JFXButton btn_salir;


    @FXML
    void ingreso_material(ActionEvent event) {
 try {
            ((Stage) btn_salir .getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/magove/vista/carga.fxml"));
            Parent root;
            root= (Parent) loader.load();
             Stage stage_material = new Stage();
             
            stage_material.initStyle(StageStyle.UNDECORATED);
            stage_material.setTitle("Carga de material");
            stage_material.centerOnScreen();
            Image img = new Image("/com/magove/recursos/Magove (1).png");
            stage_material.getIcons().add(img);
    
            stage_material.setScene(new Scene(root));
            stage_material.show(); 
            
        } catch (Exception e) {
          
            System.exit(0);
        }
    }

    @FXML
    void material(ActionEvent event) {
        try {
            ((Stage) btn_salir .getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/magove/vista/material.fxml"));
            Parent root;
            root= (Parent) loader.load();
             Stage stage_material = new Stage();
             
            stage_material.initStyle(StageStyle.UNDECORATED);
            stage_material.setTitle("Material EFEM");
            stage_material.centerOnScreen();
            Image img = new Image("/com/magove/recursos/Magove (1).png");
            stage_material.getIcons().add(img);
    
            stage_material.setScene(new Scene(root));
            stage_material.show(); 
            
        } catch (Exception e) {
          
            System.exit(0);
        }
    }
    
    
    @FXML
    void regresar(ActionEvent event) {
 try {
          ((Stage) btn_salir .getScene().getWindow()).close();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/magove/vista/inicio.fxml"));
          Parent root;
          root= (Parent) loader.load();
          Stage stage_material = new Stage();
          
          stage_material.initStyle(StageStyle.UNDECORATED);
          stage_material.setTitle("Acceso a almacén");
          stage_material.centerOnScreen();
          stage_material.getIcons().add(new Image("/com/magove/recursos/Magove (1).png"));
          stage_material.setScene(new Scene(root)); 
          stage_material.show();
      } catch (IOException ex) {
         
          System.exit(0);
      }
    }

   

    @FXML
    void salida_material(ActionEvent event) {
     try {
          ((Stage) btn_salir .getScene().getWindow()).close();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/magove/vista/salida.fxml"));
          Parent root;
          root= (Parent) loader.load();
          Stage stage_material = new Stage();
          
          stage_material.initStyle(StageStyle.UNDECORATED);
         
          stage_material.setTitle("Salida de material");
          stage_material.centerOnScreen();
          stage_material.getIcons().add(new Image("/com/magove/recursos/Magove (1).png"));
          stage_material.setScene(new Scene(root)); 
          stage_material.show();
      } catch (IOException ex) {
         
          System.exit(0);
      }
    }

    @FXML
    void salir(ActionEvent event) {
System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Consultas c= new Consultas();
 lb_usuario.setText("Bienvenid@: "+c.nombre_usuario);
    }

}