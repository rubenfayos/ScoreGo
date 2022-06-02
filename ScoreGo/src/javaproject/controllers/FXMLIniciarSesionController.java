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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.UsuarioModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLIniciarSesionController implements Initializable {
    
    private UsuarioModel usuarioModel;
    
    private Singleton s = Singleton.getInstance();

    @FXML
    private TextField idInicioSesion;
    @FXML
    private TextField ContraseñaInicioSesion;
    @FXML
    private AnchorPane AP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioModel= new UsuarioModel();
    }    

    @FXML
    private void IniciarSesion(ActionEvent event) throws IOException {
        
        
        //Alertas
        Alert a2 = new Alert(Alert.AlertType.ERROR, "Las credenciales de inicio de sesion son incorrectas");
        Alert a1 = new Alert(Alert.AlertType.ERROR, "Alguno de los campos está vacio");
        
        //Comprueba el si algún campo está vacio
        if(idInicioSesion.getText().isEmpty() || ContraseñaInicioSesion.getText().isEmpty())
            a1.show();
        else{
            //Recibe el usuario si este existe
            this.s.us=this.usuarioModel.iniciarSesion(idInicioSesion.getText(), ContraseñaInicioSesion.getText());
        
            //comprueba si ha recibido el usuario
            if(this.s.us.getNombreUsuario() == null)
                a2.show();
            else   
                try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaPrincipal.fxml"));
                this.AP.getChildren().setAll(pane);
                } catch (IOException ex) {
                Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            };     
    }

    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaInicio.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
