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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class FXMLBandaController implements Initializable {

    private Singleton s = Singleton.getInstance();
    private Banda b = s.b;
    private Usuario u = s.us;
    private BandasModel bm = new BandasModel();
    
    @FXML
    private AnchorPane AP;
    @FXML
    private Label nombreBandaText;
    @FXML
    private Button bottonAbandonar;
    @FXML
    private AnchorPane MenuAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    //Consigue el id de la banda
    this.b = this.bm.listarBanda(s.b.getId());
    
    nombreBandaText.setText(this.b.getNombre());
        
    try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }    

    @FXML
    private void partiturasBanda(ActionEvent event) {
        
        //Cambio de escena a partituras banda
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartiturasBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void miembrosBanda(ActionEvent event) {
        
        //Cambio de escena a miembros banda
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMiembrosBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void publicacionesBanda(ActionEvent event) {
        
        //Cambio de escena a publicaciones banda
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void subirPartituraBanda(ActionEvent event) {
        
        //Cambio de escena a subir partitura a banda
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPartituraBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void subirPostBanda(ActionEvent event) {
        
        //Cambio de escena a subir post a banda
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPost.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AbandonarBanda(ActionEvent event) {
        
        try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMisBandas.fxml"));
                this.MenuAP.getChildren().setAll(pane);
                } catch (IOException ex) {
                Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.AP.setVisible(false);
        
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("¿Deseas abandonar la banda?");
        ac.setContentText("Esta accion es irreversible");
        
        ac.showAndWait();
        
        if (ac.getResult() == ButtonType.OK) {
        
            if(this.bm.AbandonarBanda(b, this.u) > 0){

                Alert aepa = new Alert(Alert.AlertType.INFORMATION);

                    aepa.setTitle("INFORMACIÓN");
                    aepa.setHeaderText("Abandonar banda");
                    aepa.setContentText("Se ha abandonado la banda");
                    aepa.showAndWait();

                

            }
        
        }
        
    }
    
}
