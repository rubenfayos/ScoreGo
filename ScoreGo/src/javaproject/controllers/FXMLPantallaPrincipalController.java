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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPantallaPrincipalController implements Initializable {
    
    Stage stage;
    private Scene scene;
    private Parent root;
    
    private AnchorPane AP;
    @FXML
    private AnchorPane SideBar;
    @FXML
    private AnchorPane PrincipalAP;
    @FXML
    private Button imageButton;
    @FXML
    private ImageView img;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //Inicia el Anchor Pane de la página inicio
       try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPaginaInicio.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
       BackgroundImage imagenUsuario = new BackgroundImage( new javafx.scene.image.Image("https://imagenes.elpais.com/resizer/GCZ-g8TivYpeM5dCIbrlC5d6I2I=/1200x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/5OZUGSTV3ZFRTFVRZSTYIOGDZY.jpg", 78, 80, true, true) ,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                Background fondoImagenUsuario = new Background(imagenUsuario);
                this.imageButton.setBackground(fondoImagenUsuario);
                Image fotoPerfil = new Image("https://imagenes.elpais.com/resizer/GCZ-g8TivYpeM5dCIbrlC5d6I2I=/1200x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/5OZUGSTV3ZFRTFVRZSTYIOGDZY.jpg");
       //img.setImage(fotoPerfil);
    }    

    @FXML
    private void switchToAjustes(ActionEvent event) throws IOException {
        
        //Switch a Anchor Pane ajustes
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLAjustes.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToInicio(ActionEvent event) {
        
        //Switch a Anchor Pane inicio
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPaginaInicio.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void switchToPartituras(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMisPartituras.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void switchToBandas(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMisBandas.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void switchToUsuario(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLInformacion.fxml"));
            this.PrincipalAP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
