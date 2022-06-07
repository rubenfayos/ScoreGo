/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import javaproject.model.PostModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    
    private Post p = new Post();

    @FXML
    private AnchorPane AP;
    @FXML
    private TextField tituloPost;
    @FXML
    private TextArea textoPost;

    @FXML
    private TableView<Post> postTV;
    @FXML
    private TableColumn tituloColumn;
    @FXML
    private TableColumn fechaColumn;
    @FXML
    private Label mostrarTitulo;
    @FXML
    private Label mostrarTexto;
    @FXML
    private TextField nuevoTitulo;
    @FXML
    private TextArea nuevoTextoPost;
    @FXML
    private Button confirmarEditar;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button editarButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Post> posts = this.pm.listarPostUsuario(this.u);
        
        this.tituloColumn.setCellValueFactory(new PropertyValueFactory("titulo"));
        this.fechaColumn.setCellValueFactory(new PropertyValueFactory("fechaPublicacion"));
        
        this.postTV.setItems(posts);
        
    }    

    @FXML
    private void SubirPost(ActionEvent event) {
     

       this.p = new Post();
        
       this.p.setTitulo(tituloPost.getText());
       this.p.setTexto(textoPost.getText());
       
       
       if(this.pm.subirPost(p, this.b, this.u) > 0){
           Alert ain = new Alert(Alert.AlertType.INFORMATION);
                ain.setTitle("INFORMACIÓN");
                ain.setHeaderText("Post subido");
                ain.setContentText("Post subido correctamente");
                ain.showAndWait();
       }
        
    }

    @FXML
    private void eliminarPost(ActionEvent event) {

        this.pm.eliminarPost(this.p);
    
    }

    @FXML
    private void editarPost(ActionEvent event) {
        
        if(!nuevoTitulo.isVisible()){
            nuevoTitulo.setVisible(true);
            nuevoTextoPost.setVisible(true);
            confirmarEditar.setVisible(true);
        }else{
            nuevoTitulo.setVisible(false);
            nuevoTextoPost.setVisible(false);
            confirmarEditar.setVisible(false);
        }
        
        
    }

    @FXML
    private void mostrarPost(MouseEvent event) {
        
        this.p = this.postTV.getSelectionModel().getSelectedItem();
        
        mostrarTitulo.setText(p.getTitulo());
        mostrarTexto.setText(p.getTexto());
        
        eliminarButton.setVisible(true);
        editarButton.setVisible(true);
        
    }

    @FXML
    private void confirmarEditar(ActionEvent event) {
        
        Post nuevoPost = this.p;
        
        nuevoPost.setTitulo(nuevoTitulo.getText());
        nuevoPost.setTexto(nuevoTextoPost.getText());
        
        if(this.pm.editarPost(nuevoPost, this.p) > 0){
            
        }   
        
        
        
    }        
}
   
