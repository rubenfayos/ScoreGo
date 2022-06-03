/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.PartituraModel;
import javaproject.model.mp3Player;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLVerPartituraController implements Initializable {
    
    private mp3Player mp3;
    private Partitura p;

    @FXML
    private AnchorPane anchorPaneBandasPost;
    @FXML
    private Label tituloPartitura;
    @FXML
    private Label autorPartitura;
    @FXML
    private Label usuarioSubidaPartitura;
    @FXML
    private Label fechaSubidaPartitura;
    @FXML
    private ImageView partituraVista;
    @FXML
    private Label descripcionPartitura;
    @FXML
    private AnchorPane mp3AP;
    
    private Singleton s = Singleton.getInstance();
    
    private Usuario us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.mp3=new mp3Player();
        
      
        
    }    

    @FXML
    private void reproducirMp3(ActionEvent event) {

        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLBotonReproducir.fxml"));
            this.mp3AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        //alerta de confirmación para eliminar partitura en una banda
        
        
        
         Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("Eliminar partitura");
        ac.setHeaderText("Eliminar partitura");
        ac.setContentText("¿Seguro que deseas eliminar la partitura?");
        Optional<ButtonType> result = ac.showAndWait();
        ac.show();
        if (result.get() == ButtonType.OK) {
            PartituraModel p = new PartituraModel();
            p.borrarPartitura(this.us, this.p );
           //falta el codigo de eliminación para que esto funcione, de momento la alerta solo se cerrará
           ac.close();
        }
        else {
            ac.close();
        } 
    }
    

    @FXML
    private void descargar(ActionEvent event) {
        System.out.println("Va Antoni que ");
    }
    
}
