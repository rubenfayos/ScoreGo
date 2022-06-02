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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLSubirPartituraBandaController implements Initializable {

    @FXML
    private AnchorPane subirPartituraABandaPane;
    @FXML
    private TextField nombrePartitura;
    @FXML
    private TextField autorPartitura;
    @FXML
    private AnchorPane tablaInstrumentos;
    @FXML
    private CheckBox ClarineteCB;
    @FXML
    private CheckBox OboeCB;
    @FXML
    private CheckBox SaxoTenorCB;
    @FXML
    private CheckBox FlautaTraveseraCB;
    @FXML
    private CheckBox TubaCB;
    @FXML
    private CheckBox BombardinoCB;
    @FXML
    private CheckBox TrompetaCB;
    @FXML
    private CheckBox TrombonCB;
    @FXML
    private CheckBox TrompaCB;
    @FXML
    private CheckBox PercusionCB;
    @FXML
    private CheckBox SaxoCB;
    @FXML
    private Label archivoText;
    @FXML
    private Label audioText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubirPartituraABanda(ActionEvent event) {
    }

    @FXML
    private void SubirArchivo(ActionEvent event) {
    }

    @FXML
    private void SubirAudio(ActionEvent event) {
    }
    
}
