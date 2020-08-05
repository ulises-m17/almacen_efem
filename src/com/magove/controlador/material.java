package com.magove.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.magove.consultas.Consultas;
import com.magove.mensajes.Mensajes;
import com.magove.tablas.TablaMaterial;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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

public class material implements Initializable{
    Consultas c = new Consultas();
    Mensajes m = new Mensajes();
    int opcion, incremento,total;
    int minimo,maximo,cantidad,dias,precio;
    String material;
     int nueva_existencia;
    int nueva_cantidadinicial;
    int idEstatus;

    @FXML
    private JFXButton btn_guardar;

    @FXML
    private JFXButton btn_cancela;

    @FXML
    private JFXComboBox<?> cmb_tMaterial;

    @FXML
    private TableView<TablaMaterial> tb_detalle;

    @FXML
    private TableColumn<?, ?> c_material;

    @FXML
    private TableColumn<?, ?> c_existencias;

    @FXML
    private TableColumn<?, ?> c_minimo;

    @FXML
    private TableColumn<?, ?> c_maximo;

    @FXML
    private TableColumn<?, ?> c_estatus;

    @FXML
    private TableColumn<?, ?> c_maximo111;
    
        @FXML
    private TableColumn<?, ?> cl_precio;

    @FXML
    private TableColumn<?, ?> c_fecha_inicio;

    @FXML
    private TableColumn<?, ?> c_reposicion;

    @FXML
    private TableColumn<?, ?> c_reposiscion;

    @FXML
    private TableColumn<?, ?> c_cantidad_fabricar;

    @FXML
    private TableColumn<?, ?> c_maximo11;

    @FXML
    private TableColumn<?, ?> c_fecha_entrega;

    @FXML
    private MenuItem mi_produccion;

    @FXML
    private MenuItem mi_edita;

    @FXML
    private MenuItem mi_nuevo;
        @FXML
    private MenuItem mi_carga;

    @FXML
    private Label lbvalor;
    @FXML
    private JFXTextField txt_material;

    @FXML
    private JFXDatePicker txt_calendario;

    @FXML
    private JFXTextField txt_tiempo_reposicion;

    @FXML
    private JFXTextField txt_cantidad;

    @FXML
    private JFXTextField txt_minimo;

    @FXML
    private JFXTextField txt_maximo;
    
    
    @FXML
    private JFXTextField txt_precio;
    
    
    @FXML
    private JFXButton btn_reportes;

    @FXML
    private JFXButton btn_regresa;

    @FXML
    private JFXButton btn_salir;

    @FXML
    void cancela(ActionEvent event) {
limpia();
    }

    
    @FXML
    void guarda(ActionEvent event) {
        if (opcion==1) {
    if(txt_minimo.getText().trim().length()!=0 && txt_maximo.getText().trim().length()!=0 && txt_cantidad.getText().trim().length()!=0
                     && txt_tiempo_reposicion.getText().trim().length()!=0){
     variables();
      if (c.actualiza_detalle(minimo, maximo, dias,cantidad,precio )) {
          m.agregado();
          limpia();
      }else{
          m.no_agregado();
      }
     
  }else{
      m.vacio();
  }
     }else if (opcion==2) {
            
           
            
           
            if (txt_calendario.getValue()!=null) {
                  if(c.programa_produccion(txt_calendario.getValue().toString(), dias, c.material)){
              
      
              
              c.consulta_fecha_entrega();
            
              if(c.dia.equals("Saturday")){
                 incremento=2;
                 total=dias+incremento;
                  c.actualizadias__produccion(txt_calendario.getValue().toString(), total, c.material);
              }else if(c.dia.equals("Sunday")){
                   incremento=1;
                   total=dias+incremento;
                    c.actualizadias__produccion(txt_calendario.getValue().toString(), total, c.material);
              }
          
              m.agregado();
                incremento=0;
                total=0;
            
      
              
            }else{
                m.no_agregado();
            }
            
            
            }else{
                m.vacio();
                txt_calendario.requestFocus();
            }
  
        }else if (opcion==3) {
            if(cmb_tMaterial.getValue()!=null){
                if(txt_material.getText().trim().length()!=0){
                    if (c.inserta_nuevo_material(txt_material.getText(),cmb_tMaterial.getValue().toString())) {
                       variables();
                        if(txt_minimo.getText().trim().length()!=0 || txt_maximo.getText().trim().length()!=0 || txt_cantidad.getText().trim().length()!=0 ||
                                txt_tiempo_reposicion.getText().trim().length()!=0 ){
                            
                            if(c.inserta_nuevo_detalle(cantidad, minimo,maximo,dias,precio,material)){
                                
                                m.agregado();
                                
                            }else{
                                m.no_agregado();
                            }
                        }else{
                           m.vacio();  
                        }
                    }else{
                        m.no_agregado();
                    }
                }else{
                    m.vacio();
                    txt_material.requestFocus();
                }
            }else{
                m.vacio();
                cmb_tMaterial.requestFocus();
            }
        }
         tb_detalle.getItems().clear();
    llena_tabla();
 limpia();
    }

    @FXML
    void ir_material(ActionEvent event) {
    c.t_material=cmb_tMaterial.getValue().toString();
    tb_detalle.getItems().clear();
    llena_tabla();
    }

   
    @FXML
    void numeros(KeyEvent event) {
char c= event.getCharacter().charAt(0);
if(!Character.isDigit(c)){
    event.consume();
}
    }
    
    
    @FXML
    void nuevo_material(ActionEvent event) {
    oculta();
    limpia();
    txt_tiempo_reposicion.setVisible(true);
    txt_material.setVisible(true);
    txt_cantidad.setVisible(true);
    txt_minimo.setVisible(true);
    txt_maximo.setVisible(true);
    txt_precio.setVisible(true);
    opcion=3;
    }
    
    @FXML
    void edita_material(ActionEvent event) {
        
    oculta();
    limpia();
    
    txt_tiempo_reposicion.setVisible(true);
    txt_cantidad.setVisible(true);
    txt_minimo.setVisible(true);
    txt_maximo.setVisible(true);
    txt_precio.setVisible(true);
    opcion=1;
    
  TablaMaterial t = tb_detalle.getSelectionModel().getSelectedItem();
  if(t!=null){
  txt_tiempo_reposicion.setText(t.getTiempo_reposicion());
  txt_cantidad.setText(t.getCantidad_fabricar());
  txt_minimo.setText(t.getMinimo());
  txt_maximo.setText(t.getMaximo());
  txt_precio.setText(t.getPrecio());
 
  c.material=t.getCodigo();
  }else{
     m.seleccione_fila();
  } 
    }

    @FXML
    void programa_produccion(ActionEvent event) {
        oculta();
        limpia();
        txt_calendario.setVisible(true);
      
        TablaMaterial t = tb_detalle.getSelectionModel().getSelectedItem();
      if(t!=null){
          dias=Integer.parseInt(t.getTiempo_reposicion());
            c.material=t.getCodigo();
            
        opcion=2;
      }else{
         m.seleccione_fila();
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
    
    public void variables(){
         minimo=Integer.parseInt(txt_minimo.getText());
  maximo=Integer.parseInt(txt_maximo.getText());
  cantidad=Integer.parseInt(txt_cantidad.getText());
  dias=Integer.parseInt(txt_tiempo_reposicion.getText());
  material=txt_material.getText();
  precio=Integer.parseInt(txt_precio.getText());
    }
    
    public void oculta(){
    txt_calendario.setVisible(false);
    txt_tiempo_reposicion.setVisible(false);
    txt_cantidad.setVisible(false);
    txt_minimo.setVisible(false);
    txt_maximo.setVisible(false);
    txt_material.setVisible(false);
    txt_precio.setVisible(false);
    }
    
    public void limpia(){
        txt_calendario.setValue(null);
        txt_cantidad.setText("");
        txt_material.setText("");
        txt_maximo.setText("");
        txt_minimo.setText("");
        txt_tiempo_reposicion.setText("");
        txt_precio.setText("");
  
    }

    public void llena_tabla(){
        
        c.Consulta_detalle_material();
        
        tb_detalle.setItems(c.lista_detalle_material); 
        c_material.setCellValueFactory(new PropertyValueFactory("codigo"));
        c_existencias.setCellValueFactory(new PropertyValueFactory("existencias"));
        c_minimo.setCellValueFactory(new PropertyValueFactory("minimo"));
        c_maximo.setCellValueFactory(new PropertyValueFactory("maximo"));
        c_estatus.setCellValueFactory(new PropertyValueFactory("estatus"));
        c_fecha_inicio.setCellValueFactory(new PropertyValueFactory("fecha_inicio"));
        c_reposiscion.setCellValueFactory(new PropertyValueFactory("tiempo_reposicion"));
        c_cantidad_fabricar.setCellValueFactory(new PropertyValueFactory("cantidad_fabricar"));
        c_fecha_entrega.setCellValueFactory(new PropertyValueFactory("fecha_entrega"));
        cl_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        
    }
    
        @FXML
    void reportes(ActionEvent event) {

        try {
    String path = "C:\\Users\\Dise 01\\Documents\\NetBeansProjects\\Almacen EFEM\\detalle_material.jasper";
            
            
     Map pa = new HashMap();
      
    JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
    JasperPrint jprint=JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(c.lista_detalle_material));
    JasperViewer viewer = new JasperViewer(jprint,false);
    viewer.setVisible(true);
    viewer.setTitle("Reporte de material EFEM");
    
    } catch (JRException ex) {
       m.error_reporte();
    }
        
    }
    
        @FXML
    void carga(ActionEvent event) {
   TablaMaterial t = tb_detalle.getSelectionModel().getSelectedItem();
      if(t!=null){
         
       c.cantidad=Integer.parseInt(t.getCantidad_fabricar());
       c.material=t.getCodigo();
          
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
                 
                        c.actualiza_detalle_material_salida_pf(0);
               
               m.agregado();
           }else{
               m.no_agregado();
           }
           
            txt_cantidad.setText("");
            
        }else{
            
             m.no_agregado();
        }
       
       
      }else{
        m.seleccione_fila();
      }  
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        c.Consulta_tiposMaterial();
        cmb_tMaterial.setItems(c.lista_tipoM);
        
        c.Consulta_entrgas_hoy();
         tb_detalle.setItems(c.lista_detalle_material);
        
        c_material.setCellValueFactory(new PropertyValueFactory("codigo"));
        c_existencias.setCellValueFactory(new PropertyValueFactory("existencias"));
        c_minimo.setCellValueFactory(new PropertyValueFactory("minimo"));
        c_maximo.setCellValueFactory(new PropertyValueFactory("maximo"));
        c_estatus.setCellValueFactory(new PropertyValueFactory("estatus"));
        c_fecha_inicio.setCellValueFactory(new PropertyValueFactory("fecha_inicio"));
        c_reposiscion.setCellValueFactory(new PropertyValueFactory("tiempo_reposicion"));
        c_cantidad_fabricar.setCellValueFactory(new PropertyValueFactory("cantidad_fabricar"));
        c_fecha_entrega.setCellValueFactory(new PropertyValueFactory("fecha_entrega"));
        cl_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        lbvalor.setText(c.valor_almacen());
 
        
    }

}
