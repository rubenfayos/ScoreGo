/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.model.FTPManager;
import javaproject.model.PartituraModel;
import javaproject.model.mp3Player;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLVerPartituraController implements Initializable {
    
    private FTPManager ftp;
    private mp3Player mp3;
    private Partitura p;
    private PartituraModel pm;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.mp3=new mp3Player();
        this.pm=new PartituraModel();
        this.ftp=new FTPManager();
           
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
        
        this.pm.borrarPartitura(p);
        
    }

    @FXML
    private void descargar(ActionEvent event) {
        
        this.p=new Partitura();
        
        this.p.setSrc("/public_html/data/admin/files/Partituras/pdf/RutinaPPL.pdf");
        
        //Crea un directory chooser
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Elige la carpeta de  descarga");
        File selectedDir = dirChooser.showDialog(new Stage());
        //Coge la ruta de descarga
        String selectedDirPath = selectedDir.toString() + "\\si.pdf";
        
        System.out.println(selectedDirPath);
        
        //Descarga el archivo
        if(this.ftp.downloadFile(this.p.getSrc().toString(), selectedDirPath) == true){
            System.out.print("si");
        }else{
            System.out.print("Descarga fallida");
        }
        
    }
    
}
