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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.PostModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPublicacionesBandaController implements Initializable {
    
    private PostModel psm = new PostModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Banda b = s.b;

    @FXML
    private ScrollPane scrollPanelPost;
    @FXML
    private GridPane postGP;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        
        
    }    


}
    

