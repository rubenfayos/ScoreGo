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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLMisPartiturasController implements Initializable {

    @FXML
    private AnchorPane AP;
    @FXML
    private ScrollPane scrollPanelMisPartituras;
    @FXML
    private AnchorPane anchorPanePartituras;
    @FXML
    private Button botonSwitchToPartitura;
    @FXML
    private ImageView imagenPartituraMisPartituras;
    @FXML
    private GridPane partituraGP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        partituraGP.add(AP, 0, 0);
        partituraGP.add(AP, 0, 1);
        partituraGP.add(AP, 0, 2);
    }    

    @FXML
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
