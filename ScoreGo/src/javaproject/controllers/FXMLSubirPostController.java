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
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;

/**
 * FXML Controller class
 *
 * @author jnava
 */
public class FXMLSubirPostController implements Initializable {
  
    PostModel pm = new PostModel();
    private Singleton s = Singleton.getInstance();
    private Banda b = s.b;
    private Usuario u = s.us;

    @FXML
    private AnchorPane AP;
    @FXML
    private TextField tituloPost;
    @FXML
    private TextArea textoPost;
    @FXML
    private TextField tituloEliminar;
    @FXML
    private TextField editarTítulo;
    @FXML
    private TextArea EditarPost;

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
        
       p.setTítulo(tituloPost.getText());
       p.setTexto(textoPost.getText());
       
       
       this.pm.subirPost(p, this.b, this.u);
        
   
    
    }

    @FXML
    private void eliminarPost(ActionEvent event) {
        
               Post p = new Post();

               
               p.setTexto(tituloEliminar.getText());
    
               this.pm.eliminarPost(p, this.u, this.b);
    
    }

    @FXML
    private void editarPost(ActionEvent event) {
        Post p = new Post();
        
        p.setTexto(EditarPost.getText());
        p.setTítulo(editarTítulo.getText());
        
        this.pm.editarPost(p, this.b, this.u);
    }
         
    }
   
