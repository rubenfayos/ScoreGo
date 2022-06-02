/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controllers;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javaproject.clases.Usuario;

/**
 *
 * @author 1erDAM
 */
public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button crearUsuarioButton;
    
    @FXML
    public void switchToCrearUsuario(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLCrearUsuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
    public void switchToInicio(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaInicio.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void switchToIniciarSesion(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLIniciarSesion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void switchToPantallaPrincipal(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaPrincipal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
     
     

    public SceneController() {
    }
    
    
    
    
}
