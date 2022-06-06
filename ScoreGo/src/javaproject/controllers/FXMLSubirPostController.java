/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import javaproject.model.PostModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Banda;
import javaproject.clases.Partitura;
import javaproject.clases.Post;
import javaproject.clases.Usuario;

/**
 * FXML Controller class
 *
 * @author jnava
 */
public class FXMLSubirPostController implements Initializable {

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
     
                
         
         String texto = String.valueOf(textoPost);
         String titulo = String.valueOf(tituloPost);
         
        insertar(texto,titulo);
         
    }
    public static void insertar(String texto, String titulo){
        
        Usuario u = new Usuario();
        Banda b = new Banda();
        PostModel pm = new PostModel();
        
      
        
        pm.subirPost(u.getId(),b.getId(), titulo, texto);
        
        
    }
}
