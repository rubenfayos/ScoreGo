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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Banda;
import javaproject.clases.Post;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLSubirPostController implements Initializable {
    
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Banda b = s.b;

    @FXML
    private AnchorPane AP;
    @FXML
    private TextField tituloPost;
    @FXML
    private TextArea textoPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubirPost(ActionEvent event) {
        
        Post p = new Post();
        
    }
    
}
