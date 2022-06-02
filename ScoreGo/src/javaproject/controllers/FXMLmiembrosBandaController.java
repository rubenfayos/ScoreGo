/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLmiembrosBandaController implements Initializable {

    @FXML
    private ImageView vistaFotoUsuario;
    @FXML
    private TableView<?> tablaMiembros;
    @FXML
    private AnchorPane panelDatosUsuario;
    @FXML
    private Label nombreMiembro;
    @FXML
    private Label fechaCreacionPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
