/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLMisBandasController implements Initializable {
    
    private BandasModel bm = new BandasModel();
    private Singleton s = Singleton.getInstance();
    private Usuario us = s.us;

    @FXML
    private GridPane gridPaneMisBandas;
    @FXML
    private Button botonAnyadirBanda;
    @FXML
    private AnchorPane AP;
    @FXML
    private ScrollPane scp;
    private AnchorPane ap;
    @FXML
    private AnchorPane unirseAP;
    @FXML
    private TextField unirseNombre;
    @FXML
    private PasswordField unirseContrase??a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<Banda> bandas = this.bm.listarBandas(us);
        
        int filas = 0;
        
        for(int i = 0; i <= bandas.size(); i=i+4){
            
            if(i < bandas.size())
                filas++;
        }
        
        int columnas=4;
        int cont = 0;
        
        //Define la cantidad de filas y su tama??o
        for (int i = 0; i < filas-1; i++) {
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPrefHeight(248);
        gridPaneMisBandas.getRowConstraints().add(rowConst);    
        }
        
        gridPaneMisBandas.setPrefHeight(248*filas);

        for(int i = 0; i < filas; i++){
            for(int x = 0; x < columnas; x++){
                Pane pa= new Pane();
                Button b = new Button();
                b.setPrefSize(200, 200);
                //seleciona el fondo de los botones
                
                if(!bandas.get(cont).getImg().isEmpty()){
                    Image img = new Image(bandas.get(cont).getImg(), 200, 200, false, true);
                    BackgroundImage fondoBotonBanda = new BackgroundImage( img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                    Background fondoImagenBanda = new Background(fondoBotonBanda);
                    b.setBackground(fondoImagenBanda);
                }
                
                //Coge el id de la banda
                b.setId(Integer.toString(bandas.get(cont).getId()));
                b.setOnAction((event) -> {
                    switchToBanda(b.getId());
                });
                
                pa.getChildren().add(b);
                pa.getChildren().add(crearLabel(bandas.get(cont).getNombre()));
                gridPaneMisBandas.add(pa, x, i);
                cont++;
                
                if(cont == bandas.size()){
                    x=columnas;
                    i=(int) filas;
                }
    
            }
            
        }
             
    }    

    private void switchToBanda(String id) {
        
        Banda b = new Banda();
        b.setId(Integer.parseInt(id));
        this.s.b=b;
        
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @FXML
    private void clickAnyadirBanda(ActionEvent event) {
        
        //Cambio de escenas a crear banda
        try {
            AnchorPane ap = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLcrearNuevaBanda.fxml"));
            this.AP.getChildren().setAll(ap);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void showUnirseBanda(ActionEvent event) {
        
        //Muestra para unirse a banda
        if(!unirseAP.isVisible())
            this.unirseAP.setVisible(true);
        else if(unirseAP.isVisible())
            this.unirseAP.setVisible(false);
        
    }

    @FXML
    private void UnirseBanda(ActionEvent event) {
        
        
        Alert ac2 = new Alert(Alert.AlertType.CONFIRMATION);
        ac2.setTitle("CONFIRMATION");
        ac2.setHeaderText("Esto es un mensaje de confirmaci??n");
        ac2.setContentText("??Desea unirse a la banda?");
        ac2.showAndWait();
            
            if(ac2.getResult() == ButtonType.OK){
                //Te une a la banda
                if(this.bm.unirseBanda(unirseNombre.getText(), unirseContrase??a.getText(), this.us) > 0){
            
                    Alert ai = new Alert(AlertType.INFORMATION);
                    ai.setTitle("INFORMACI??N");
                    ai.setHeaderText("Esto es un mensaje informativo");
                    ai.setContentText("Te has unido a la banda correctamente");
                    ai.showAndWait();

                   try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMisBandas.fxml"));
                    this.AP.getChildren().setAll(pane);
                    } catch (IOException ex) {
                    Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
            }
            
        }
        
    }
    
    public Label crearLabel(String p){
        
        Label l = new Label(p);
               
                l.setLayoutY(210);
                l.setFont(new Font(16));
                l.setPrefWidth(200);
                l.setAlignment(Pos.CENTER);
    
        return l;
    }
    
    
    
}
