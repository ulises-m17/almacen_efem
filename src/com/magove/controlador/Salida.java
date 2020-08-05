package com.magove.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.magove.consultas.Consultas;
import com.magove.mensajes.Mensajes;
import com.magove.tablas.TablaDetalle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class Salida implements Initializable{
Consultas c = new Consultas();
Mensajes m = new Mensajes();
    public int descuento,id_estatus;
    
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
    private JFXButton btn_reportes;

    @FXML
    private TableView<TablaDetalle> tb_detalle;

    @FXML
    private TableColumn<?, ?> c_material;

    @FXML
    private TableColumn<?, ?> c_existencias;

    @FXML
    private TableColumn<?, ?> c_minimo;

    @FXML
    private TableColumn<?, ?> c_maximo;
    
     @FXML
    private TableColumn<?, ?> c_pieza;
    @FXML
    private TableColumn<?, ?> c_estatus;

    @FXML
    private TableColumn<?, ?> c_reposicion;

    @FXML
    private JFXButton btn_regresa;

    @FXML
    private JFXButton btn_salir;

    @FXML
    void cancela(ActionEvent event) {
txt_cantidad.setText("");
cmb_material.getItems().clear();
cmb_material.setDisable(true);
    }

    @FXML
    void guarda(ActionEvent event) {
        try {
             if (txt_cantidad.getText().trim().length()!=0) {
             
              c.cantidad= Integer.parseInt(txt_cantidad.getText());
        c.material= cmb_material.getValue().toString();
        c.Consulta_existencias();
        
                 if (c.existencias_consulta>=c.cantidad ) {
                     
                     if (c.cantidad>0) {
                         
                           if(c.salida_material()){
                         
                         descuento=c.existencias_consulta-c.cantidad;
                       
                              
                         
                       if(descuento==0){
                           id_estatus=3;
                       }else if (descuento>0 && descuento <=c.minimo) {
                           id_estatus=4;
                             
                         }else if (descuento>c.minimo) {
                             id_estatus=2;
                         }
                      
                       
                         if (c.actualiza_detalle_material_salida(descuento, id_estatus)) {
                        txt_cantidad.setText("");
          
                        
                        
                        
                        c.Consulta_existencias();
                        c.actualiza_detalle_material_salida_pf(c.maximo-c.existencias_consulta);
          
                         }else{
                             m.perdio_conexion();
                         }
   
                         m.agregado(); 
                          tb_detalle.getItems().clear();
                             llenar_tabla();
                         
                     }else{
                         m.perdio_conexion();
                     }
                     }else{
                        m.error_cantidad();
                        txt_cantidad.requestFocus();
                     }
                   
                     
                }else{
                     m.existencias_menos();

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
      tb_detalle.getItems().clear();
      llenar_tabla();
    }

    @FXML
    void numeros(KeyEvent event) {
char c= event.getCharacter().charAt(0);
        if (!Character.isDigit(c)) {
            event.consume();
        }
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
         System.exit(0);
      }
    }

    
    @FXML
    void salir(ActionEvent event) {
System.exit(0);
    }
    
    public void llenar_tabla(){
      
      c.llena_tabla_en_salida();
      tb_detalle.setItems(c.lista_detalle);
      c_material.setCellValueFactory(new PropertyValueFactory("codigo"));
      c_existencias.setCellValueFactory(new PropertyValueFactory("existencias"));
      c_estatus.setCellValueFactory(new PropertyValueFactory("estatus"));
      c_minimo.setCellValueFactory(new PropertyValueFactory("minimo"));
      c_maximo.setCellValueFactory(new PropertyValueFactory("maximo"));
      c_reposicion.setCellValueFactory(new PropertyValueFactory("tiempo_reposicion"));
      c_pieza.setCellValueFactory(new PropertyValueFactory("cantidad_por_fabricar"));
      
      
    }
    
     @FXML
    void reportes(ActionEvent event) {
c.reporte_salidas();
        try {
    String path = "C:\\Users\\Dise 01\\Documents\\NetBeansProjects\\Almacen EFEM\\salida_material.jasper";
            
            
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     c.Consulta_tiposMaterial();
        cmb_tMaterial.setItems(c.lista_tipoM);
   
    }

}
