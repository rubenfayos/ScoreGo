/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.File;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.model.FTPManager;
import javaproject.model.mp3Player;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLVerPartituraBandaController implements Initializable {

    @FXML
    private AnchorPane verPartituraBandaPane;
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
    
    private Alert ac;
    
    private FTPManager ftp = new FTPManager();
    
    
    /**
     * Initializes the controller class.
     */
    private mp3Player mp3;
    private Partitura p;
    @FXML
    private AnchorPane mp3AP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         this.mp3=new mp3Player();
    }    

    @FXML
    private void reproducirMp3(ActionEvent event) {
        //muestra el panel con el reproductor mp3
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLBotonReproducir.fxml"));
            this.mp3AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void alert() {
    
    }

    @FXML
    private void eliminar(ActionEvent event) {
        
        //alerta de confirmación para eliminar una partitura dentro de una banda
        Alert acp = new Alert(Alert.AlertType.CONFIRMATION);
        acp.setTitle("Eliminar partitura");
        acp.setHeaderText("Eliminar partitura");
        acp.setContentText("¿Seguro que deseas eliminar la partitura?");
        Optional<ButtonType> result = acp.showAndWait();
        acp.show();
        if (result.get() == ButtonType.OK) {
           //falta el codigo de eliminación para que esto funcione, de momento la alerta solo se cerrará
           acp.close();
        }
        else {
            acp.close();
        } 
    }

    @FXML
    private void descargar(ActionEvent event) {
        //Crea un directory chooser
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Elige la carpeta de  descarga");
        File selectedDir = dirChooser.showDialog(new Stage());
        
        //Coge la ruta de descarga
        String selectedDirPath = selectedDir.toString() + "\\" + this.p.getNombre() + ".pdf";
        
        //Descarga el archivo
        if(this.ftp.downloadFile("/public_html" + p.getSrc(), selectedDirPath)){
            //muestra una alerta si la descarga funciona
            Alert alertaDescarga = new Alert(Alert.AlertType.INFORMATION);
            alertaDescarga.setTitle("INFORMACIÓN");
            alertaDescarga.setHeaderText("Archivo descargado");
            alertaDescarga.setContentText("Descarga completada");
            alertaDescarga.showAndWait();
            
                        
        }else{
            //muestra una alerta si la descarga falla
            Alert alertaDescargaError = new Alert(Alert.AlertType.ERROR);
            alertaDescargaError.setTitle("INFORMACIÓN");
            alertaDescargaError.setHeaderText("Archivo descargado");
            alertaDescargaError.setContentText("Descarga completada");
            alertaDescargaError.showAndWait();
        }
    }
    
}
