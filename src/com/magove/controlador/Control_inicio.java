package com.magove.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.magove.consultas.Consultas;
import com.magove.mensajes.Mensajes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Control_inicio implements Initializable{
Consultas c= new Consultas();
Mensajes m= new Mensajes();

    @FXML
    private JFXTextField txt_usuario;

    @FXML
    private JFXPasswordField txt_contrasenia;

    @FXML
    private JFXButton btn_salir;
    
    
      @FXML
    void Cancela(ActionEvent event) {
txt_contrasenia.setText("");
txt_usuario.setText("");
    }

    @FXML
    void Entra(ActionEvent event) {
        try {
             c.usuario_ingresado=txt_usuario.getText().toString();
        c.pasword=txt_contrasenia.getText().toString();
        
        Image img = new Image("com/magove/recursos/error.png");
        if (c.usuario_ingresado.trim().length()!=0) {
            if(c.pasword.trim().length()!=0){
               c.Consulta_usuario();
                 
                  if(c.usuario_ingresado.equals(c.usuario) ){
                      if(c.pasword.equals(c.contrasenia)){
                          try {
                          ((Stage) btn_salir .getScene().getWindow()).close();
                          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/magove/vista/menu.fxml"));
                          Parent root;
                          root= (Parent) loader.load();
                          Stage stage_material = new Stage();
                          
                          stage_material.initStyle(StageStyle.UNDECORATED);
                          
                          stage_material.setTitle("Menú principal");
                          stage_material.centerOnScreen();
                          stage_material.getIcons().add(new Image("/com/magove/recursos/Magove (1).png"));
                          stage_material.setScene(new Scene(root));
                          stage_material.show();
                          c.con.close();
                          
                      } catch (Exception e) {
                          m.ventana();
                        System.exit(0);
                      } 
                      }else{
                           Notifications notificacion = Notifications.create()
                              .title("ERROR")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(img))
                              .text("Contraseña incorrecta")
                              .position(Pos.BOTTOM_CENTER);
                      notificacion.show();
                      txt_contrasenia.requestFocus();
                      }
                              
                     
                  }else{
                      Notifications notificacion = Notifications.create()
                              .title("ERROR")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(img))
                              .text("Usuario incorrecto")
                              .position(Pos.BOTTOM_CENTER);
                      notificacion.show();
                      txt_contrasenia.requestFocus();
                  }
    
         
            }else{
                  m.vacio();
txt_contrasenia.requestFocus();
            }
                
        }else{
            m.vacio();
txt_usuario.requestFocus();
        }
        } catch (Exception e) { 
            m.perdio_conexion();
        }
      
        }
       
        
        
        // codigo para mostrar notificacion en la pantalla


    @FXML
    void salir(ActionEvent event) {
System.exit(0);
    }

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    
    }

}
