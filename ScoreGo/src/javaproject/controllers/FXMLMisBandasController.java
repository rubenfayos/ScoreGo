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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLMisBandasController implements Initializable {
    
    private BandasModel bm;
    
    private Singleton s = Singleton.getInstance();
    private Usuario us;

    @FXML
    private GridPane gridPaneMisBandas;
    @FXML
    private Button botonEntrarABanda;
    @FXML
    private ImageView imageMiBanda1;
    @FXML
    private Label nombreBanda1;
    @FXML
    private Button botonAnyadirBanda;
    @FXML
    private AnchorPane AP;
    @FXML
    private ScrollPane scp;
    private AnchorPane ap;
    @FXML
    private AnchorPane unirseAP;
    @FXML
    private TextField unirseNombre;
    @FXML
    private PasswordField unirseContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.bm = new BandasModel();
        
        this.us=s.us;
        
    }    

    @FXML
    private void clickEntrarABanda1(ActionEvent event) {
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @FXML
    private void clickAnyadirBanda(ActionEvent event) {
        
        //Cambio de escenas a crear banda
        try {
            AnchorPane ap = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLcrearNuevaBanda.fxml"));
            this.AP.getChildren().setAll(ap);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void showUnirseBanda(ActionEvent event) {
        
        //Muestra para unirse a banda
        if(!unirseAP.isVisible())
            this.unirseAP.setVisible(true);
        else if(unirseAP.isVisible())
            this.unirseAP.setVisible(false);
        
    }

    @FXML
    private void UnirseBanda(ActionEvent event) {
        
        //Te une a la banda
        this.bm.unirseBanda(unirseNombre.getText(), unirseContraseña.getText(), this.us);
        
    }
    
}
