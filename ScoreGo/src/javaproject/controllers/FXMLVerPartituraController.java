/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import javax.imageio.ImageIO;

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
        
       //coge la partitura
       this.p = pm.buscarPartitura(this.s.p.getId());
        
        if(this.u.getId() == this.p.getUsuario().getId()){
            editarButton.setVisible(true);
            eliminarButton.setVisible(true);
        }else
            guardarButton.setVisible(true); 
        
        
        this.tituloPartitura.setText(p.getNombre());
        this.autorPartitura.setText(p.getAutor());
        //this.fechaSubidaPartitura.setText(p.getFechaSubida().toString());
        
        Image image = new Image("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimg.fotocommunity.com%2Fatardeceres-1b2829bd-e28f-47e5-9a55-8d9988f8e184.jpg%3Fheight%3D1080&f=1&nofb=1");
        this.partituraVista.setImage(image);
        
        
        
           
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
            this.pm.borrarPartitura(p);
           //falta el codigo de eliminación para que esto funcione, de momento la alerta solo se cerrará
           ac.close();
        }
        else {
            ac.close();
        } 
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
