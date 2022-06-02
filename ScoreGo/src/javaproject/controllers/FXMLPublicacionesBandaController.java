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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPublicacionesBandaController implements Initializable {

    @FXML
    private AnchorPane anchorPaneBandasPost;
    @FXML
    private Button botonSubirBanda;
    @FXML
    private Button botonPartiturasBanda;
    @FXML
    private Button botonMiembrosBanda;
    @FXML
    private Button botonPublicacionesBanda;
    @FXML
    private ScrollPane scrollPanelPost;
    @FXML
    private AnchorPane anchorPanelPost;
    @FXML
    private AnchorPane panelUsuarioFechaPost;
    @FXML
    private Circle fotoUsuarioPost;
    @FXML
    private Label nombreUsuarioPost;
    @FXML
    private Label fechaSubidaPost;
    @FXML
    private ImageView imagePost;
    @FXML
    private AnchorPane panelPieDeFoto;
    @FXML
    private Label piePublicacion;
    @FXML
    private Label fechaEscrituraPiePublicacion;
    @FXML
    private AnchorPane anchorPaneBandasInside;
    @FXML
    private AnchorPane publicacionesSubPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickSubirABanda(ActionEvent event) {
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPartituraBanda.fxml"));
            this.publicacionesSubPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void clickPartiturasBanda(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartitura.fxml"));
            this.publicacionesSubPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void clickMiembrosBanda(ActionEvent event) {
        
    }

    @FXML
    private void clickPublicacionesBanda(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
            this.anchorPaneBandasInside.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    }
    

