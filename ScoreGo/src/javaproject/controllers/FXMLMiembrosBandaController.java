/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLMiembrosBandaController implements Initializable {
    
    private BandasModel bm = new BandasModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Banda b = s.b;
    
    private Usuario us = new Usuario();

    @FXML
    private TableView<Usuario> tablaMiembros;
    @FXML
    private Label nombreUsuarioText;
    @FXML
    private Label nombreText;
    @FXML
    private Label apellidosText;
    @FXML
    private Label nacionalidadText;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private Button eliminarUsuarioButton;
    @FXML
    private AnchorPane AP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        usuarios = this.bm.listarUsuarios(b);
        
        this.nombreColumn.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
        
        this.tablaMiembros.setItems(usuarios);
        
        if(this.u.getId() == this.b.getAdministrador().getId())
            eliminarUsuarioButton.setVisible(true);
           
    }    

    @FXML
    private void mostrarUsuario(MouseEvent event) {
        
        this.us = this.tablaMiembros.getSelectionModel().getSelectedItem();
        
        nombreUsuarioText.setText(us.getNombreUsuario());
        nombreText.setText(us.getNombre());
        apellidosText.setText(us.getApellidos());
        nacionalidadText.setText(us.getNacionalidad());
        
    }

    @FXML
    private void eliminarUsuario(ActionEvent event) {
        
        if(bm.eliminarUsuarioBanda(us, b) > 0){
            
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMiembrosBanda.fxml"));
                this.AP.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }   
}
