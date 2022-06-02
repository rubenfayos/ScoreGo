/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Partitura;
import javaproject.model.mp3Player;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLVerPartituraBandaController implements Initializable {

    @FXML
    private AnchorPane verPartituraBandaPane;
    @FXML
    private AnchorPane anchorPaneBandasPost;
    @FXML
    private Label tituloPartitura;
    @FXML
    private Label autorPartitura;
    @FXML
    private Label usuarioSubidaPartitura;
    @FXML
    private Label fechaSubidaPartitura;
    @FXML
    private ImageView partituraVista;
    @FXML
    private Label descripcionPartitura;

    /**
     * Initializes the controller class.
     */
    private mp3Player mp3;
    private Partitura p;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         this.mp3=new mp3Player();
    }    

    @FXML
    private void reproducirMp3(ActionEvent event) {
        this.mp3.reproductor();
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void descargar(ActionEvent event) {
    }
    
}
