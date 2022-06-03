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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.FTPManager;
import javaproject.model.PartituraModel;
import javaproject.model.mp3Player;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLVerPartituraController implements Initializable {
    
    private FTPManager ftp = new FTPManager();
    private mp3Player mp3 = new mp3Player();
    private Partitura p = new Partitura();
    private PartituraModel pm = new PartituraModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;

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
    @FXML
    private Button eliminarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button guardarButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
      
        //this.p=this.pm.listarPartitura(id);
        
        
        if(this.u.getId() == this.p.getUsuario().getId()){
            editarButton.setVisible(true);
            eliminarButton.setVisible(true);
        }else
            guardarButton.setVisible(true);
           

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

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        
        
    }
    
}
