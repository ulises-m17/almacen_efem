package com.magove.mensajes;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Mensajes {
   Image imgE = new Image("com/magove/recursos/error.png");
   Image imgR= new Image("com/magove/recursos/success.png");
   
    public String vacio(){    
        Notifications notificacion =  Notifications.create()
                .position(Pos.BOTTOM_CENTER)
                .title("Error")
                .darkStyle()
                .text("No puede haber campos vacíos")
                .hideAfter(Duration.seconds(3))
                .graphic(new ImageView(imgE));
                notificacion.show();
                return null;
}
    
    
    
    public String agregado(){
       Notifications notificacion= Notifications.create()
               .position(Pos.CENTER_RIGHT)
               .title("Realizado")
               .text("Acción realizada con exito")
               .hideAfter(Duration.seconds(3))
               .graphic(new ImageView(imgR));
               notificacion.show();
        
        return null;
    }
    
   public String no_agregado(){
        Notifications notificacion = Notifications.create()
                              .title("ERROR")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(imgE))
                              .text("No realizado")
                              .position(Pos.CENTER);
                      notificacion.show();
       return null;
   }
    
    
    public String ventana(){
         Notifications notificacion = Notifications.create()
                              .title("La ventana no se pudo abrir")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(imgE))
                              .text("Reinicia el programa")
                              .position(Pos.CENTER);
                      notificacion.show();
       return null;
    }
    
    public String existencias_menos(){
         Notifications notificacion = Notifications.create()
                              .title("Error")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(imgE))
                              .text("Las existencias son menor a la cantidad solicitada")
                              .position(Pos.CENTER);
                      notificacion.show();
       return null;
    }
    
    
     public String perdio_conexion(){
         Notifications notificacion = Notifications.create()
                              .title("Error de conexión")
                              .hideAfter(Duration.seconds(3))
                              .darkStyle()
                              .graphic(new ImageView(imgE))
                              .text("Se perdió la conexión a la base de datos")
                              .position(Pos.CENTER);
                      notificacion.show();
       return null;
    }
    
     public void seleccione_fila(){
         Notifications notificacion= Notifications.create()
                 .title("Error")
                 .hideAfter(Duration.seconds(3))
                 .text("Debe seleccionar una fila de la tabla")
                 .graphic(new ImageView(imgE))
                 .position(Pos.CENTER)
                 .darkStyle();
                 notificacion.show();
     }
    
     public void error_reporte(){
         Notifications noti = Notifications.create()
                 .title("Error")
                 .darkStyle()
                 .text("Ocurrio un error al generar el reporte")
                 .hideAfter(Duration.seconds(3))
                 .graphic(new ImageView(imgE))
                 .position(Pos.CENTER);
                 noti.show();
     }
    
     
    public void error_cantidad(){
        Notifications noti = Notifications.create()
                .darkStyle()
                .title("Cantidad erronea")
                .text("Cantidad no valida,intentalo de nuevo")
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .graphic(new ImageView(imgE));
                noti.show();
    } 
}
