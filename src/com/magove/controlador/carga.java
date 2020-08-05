package com.magove.controlador;

import com.magove.mensajes.Mensajes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.magove.consultas.Consultas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class carga implements Initializable{

    Consultas c= new Consultas();
  Mensajes m = new Mensajes();
  
    int nueva_existencia;
    int nueva_cantidadinicial;
    int idEstatus;
    
    
    @FXML
    private JFXComboBox<?> cmb_tMaterial;

    @FXML
    private JFXComboBox<?> cmb_material;

    @FXML
    private JFXTextField txt_cantidad;

    @FXML
    private JFXButton btn_guardar;

    @FXML
    private JFXButton btn_cancela;

    @FXML
    private JFXButton btn_regresa;
    
        @FXML
    private JFXButton btn_reportes;

    @FXML
    private JFXButton btn_salir;

    @FXML
    void cancela(ActionEvent event) {
txt_cantidad.setText("");
cmb_material.getItems().clear();
cmb_material.setDisable(true);
    }

    
    @FXML
    void reportes(ActionEvent event) {
c.reporte_entradas();
        try {
    String path = "C:\\Users\\Dise 01\\Documents\\NetBeansProjects\\Almacen EFEM\\entradas_material.jasper";
            
            
     Map pa = new HashMap();
      
    JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
    JasperPrint jprint=JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(c.lista_entrada_salida));
    JasperViewer viewer = new JasperViewer(jprint,false);
    viewer.setVisible(true);
    viewer.setTitle("Reporte de material EFEM");
    
    } catch (JRException ex) {
       m.error_reporte();
    }
    }
    
    
    @FXML
    void guarda(ActionEvent event) {
        try {
             if (txt_cantidad.getText().trim().length()!=0) {
              c.cantidad= Integer.parseInt(txt_cantidad.getText());
        c.material= cmb_material.getValue().toString();
        
        if(c.cantidad>0){
             if (c.carga_material()) {
            
          
           
           // actualizar el detalle del material
           c.Consulta_existencias();
           nueva_cantidadinicial=c.cantidadInicial_consulta+c.cantidad;
           nueva_existencia=c.existencias_consulta+c.cantidad;
          
           if(nueva_existencia<c.minimo){
               idEstatus=4;
           }else{
               idEstatus=2;
           }
           
           if(c.actualiza_detalle_material(nueva_cantidadinicial, nueva_existencia, idEstatus)){
               m.agregado();
           }else{
               m.no_agregado();
           }
           
            txt_cantidad.setText("");
            
        }else{
            
             m.no_agregado();
        }
        }else{
            m.error_cantidad();
        }
        
       
        
        }else{
            m.vacio();
            txt_cantidad.requestFocus();
        }
        } catch (Exception e) {
            m.vacio();
        }
       
      
    }

    @FXML
    void ir_material(ActionEvent event) {
        cmb_material.setDisable(false);
        c.t_material=cmb_tMaterial.getValue().toString();
      cmb_material.getItems().clear();
        c.Consulta_material();
      cmb_material.setItems(c.lista_material);
      
    }

    @FXML
    void regresar(ActionEvent event) throws SQLException {
  try {
      
      c.con.close();
      
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
      } catch (IOException ex) {
         m.ventana();
          System.exit(0);
      }
    }

    @FXML
    void salir(ActionEvent event) throws SQLException {
         c.con.close();
System.exit(0);
    }
   @FXML
    void numeros(KeyEvent event) {
  char c = event.getCharacter().charAt(0);
    if(!Character.isDigit(c))
    {
        event.consume();
    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c.Consulta_tiposMaterial();
        cmb_tMaterial.setItems(c.lista_tipoM);
        
    }
}

     