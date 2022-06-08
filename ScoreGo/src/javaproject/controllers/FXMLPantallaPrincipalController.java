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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPantallaPrincipalController implements Initializable {

    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;

    private AnchorPane AP;
    @FXML
    private AnchorPane SideBar;
    @FXML
    private AnchorPane PrincipalAP;
    @FXML
    private Button imageButton;

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

        
        if(this.u.getImg() == null){
            u.setImg("https://scorego.ddns.net/scoregoFiles/usuarios/UserImg.jpg");
        }
            
            Image img = new Image(u.getImg(), 80, 80, false, true);
            BackgroundImage fondoBotonBanda = new BackgroundImage(img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background fondoImagenUsuario = new Background(fondoBotonBanda);
            imageButton.setBackground(fondoImagenUsuario);

        
        
            

    }

    @FXML
    private void switchToAjustes(ActionEvent event) throws IOException {

        //Switch a Anchor Pane ajustes
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLAjustesUsuario.fxml"));
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

    @FXML
    private void switchToBuscadorPartituras(ActionEvent event) {

    }

}
