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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.PartituraModel;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLMisPartiturasController implements Initializable {
    
    private PartituraModel pm = new PartituraModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = new Usuario();

    @FXML
    private AnchorPane AP;
    @FXML
    private ScrollPane scrollPanelMisPartituras;
    @FXML
    private GridPane partituraGP;
    @FXML
    private Pane Pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.u=this.s.us;
        
        ObservableList<Partitura> partituras = this.pm.listarPartituras(this.u);
        
        int columnas = 1;
        
        if(partituras.size() >4)
            columnas = partituras.size()/4;
        
        int filas = 4;
        int cont = 0;

        for(int i = 0; i < columnas; i++){
            for(int x = 0; x < filas; x++){
                Pane pa= new Pane();
                Label l = new Label(partituras.get(cont).getNombre());
                pa.getChildren().add(l);
                partituraGP.add(pa, x, i);
                cont++;
                
                if(cont == partituras.size()){
                    x=filas;
                    i=columnas;
                }
    
            }
            
        }
        
    }    

    private void switchToPartitura(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AñadirPartitura(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
