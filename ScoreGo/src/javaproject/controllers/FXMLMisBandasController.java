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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javaproject.clases.Partitura;
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
    private Button botonEntrarABanda;
    @FXML
    private ImageView imageMiBanda1;
    @FXML
    private Label nombreBanda1;
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
    private PasswordField unirseContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        
        ObservableList<Partitura> partituras = this.bm;
        
        double filas = 1;
        double size = partituras.size();
        
        if(partituras.size() >4){
            filas = size/4;
            if((filas%1) != 0)
                filas++;
        }
        
        int filas2 = (int) filas;
        
        int columnas=4;
        int cont = 0;
        
        //Define la cantidad de filas y su tamaño
        for (int i = 0; i < filas2-1; i++) {
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPrefHeight(220);
        gridPaneMisBandas.getRowConstraints().add(rowConst);    
        }
        
        gridPaneMisBandas.setPrefHeight(220*filas);

        for(int i = 0; i < filas; i++){
            for(int x = 0; x < columnas; x++){
                Pane pa= new Pane();
                Button b = new Button(partituras.get(cont).getNombre());
                b.setPrefSize(200, 200);
                //Coge el id de la partitura
                b.setId(Integer.toString(partituras.get(cont).getId()));
                b.setOnAction((event) -> {
                    VerPartitura(b.getId());
                });
                pa.getChildren().add(b);
                partituraGP.add(pa, x, i);
                cont++;
                
                if(cont == partituras.size()){
                    x=columnas;
                    i=(int) filas;
                }
    
            }
            
        }

    */
             
    }    

    @FXML
    private void clickEntrarABanda1(ActionEvent event) {
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
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
        
        //Te une a la banda
        this.bm.unirseBanda(unirseNombre.getText(), unirseContraseña.getText(), this.us);
        
    }
    
}
