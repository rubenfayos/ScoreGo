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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
 * @author 1erDAM
 */
public class FXMLVerPartituraBandaController implements Initializable {
    
    private FTPManager ftp = new FTPManager();
    private mp3Player mp3 = new mp3Player();
    private Partitura p = new Partitura();
    private PartituraModel pm = new PartituraModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private FTPManager ftpManager = new FTPManager();
    File pdf;
    File newMp3;

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
    
    @FXML
    private AnchorPane mp3AP;
    @FXML
    private AnchorPane AP;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button guardarButton;
    @FXML
    private Pane editarPane;
    @FXML
    private TextField editarTitulo;
    @FXML
    private TextField editarAutor;
    @FXML
    private TextArea editarDescripcion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //coge la partitura
       this.p = pm.buscarPartitura(this.s.p.getId());
       this.s.p = p;
        
        if(this.u.getId() == this.p.getUsuario().getId()){
            editarButton.setVisible(true);
            eliminarButton.setVisible(true);
        }else
            guardarButton.setVisible(true); 
        
        
        this.tituloPartitura.setText(p.getNombre());
        this.autorPartitura.setText("Autor: " + p.getAutor());
        this.usuarioSubidaPartitura.setText("Usuario: " + p.getUsuario().getNombreUsuario());
        this.fechaSubidaPartitura.setText("Fecha de subida: " + p.getFechaSubida().toString());
        this.descripcionPartitura.setText(p.getDescripcion());
        
        Image image = new Image("https://scorego.ddns.net/scoregoFiles/partitura.jpg");
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
        
        Alert ai = new Alert(Alert.AlertType.INFORMATION);
        ai.setTitle("Eliminar partitura");
        ai.setContentText("La partitura se ha eliminado correctamente");
        
        //alerta de confirmaci??n para eliminar partitura en una banda
        
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("Eliminar partitura");
        ac.setHeaderText("Eliminar partitura");
        ac.setContentText("??Seguro que deseas eliminar la partitura?");
        Optional<ButtonType> result = ac.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            if(this.pm.borrarPartitura(p) > 0){
                ai.showAndWait();
                
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartiturasBanda.fxml"));
                    this.AP.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }    
        }
        
        else {
            
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
        if(this.ftp.downloadFile("/public_html" + p.getSrc(), selectedDirPath) == true){
             Alert alertaDescarga = new Alert(Alert.AlertType.INFORMATION);
            alertaDescarga.setTitle("INFORMACI??N");
            alertaDescarga.setHeaderText("Archivo descargado");
            alertaDescarga.setContentText("Descarga completada");
            alertaDescarga.showAndWait();
        }else{
            Alert alertaDescargaError = new Alert(Alert.AlertType.ERROR);
            alertaDescargaError.setTitle("INFORMACI??N");
            alertaDescargaError.setHeaderText("Archivo descargado");
            alertaDescargaError.setContentText("Descarga completada");
            alertaDescargaError.showAndWait();
        }
        
    }
    
    

    @FXML
    private void editar(ActionEvent event) throws IOException {
        
        Partitura newP = this.p;
        
        if(!editarTitulo.getText().isEmpty()){
            newP.setNombre(editarTitulo.getText());
            
            //Renombra el nombre de los archivos
            newP.setSrc(this.ftp.renamePdf(newP.getSrc(), this.u.getNombreUsuario(), newP.getNombre()));
            newP.setMp3(this.ftp.renameMp3(newP.getMp3(), this.u.getNombreUsuario(), newP.getNombre()));
            
        }
        
        if(!editarAutor.getText().isEmpty())       
            newP.setAutor(editarAutor.getText());
        if(!editarDescripcion.getText().isEmpty())
            newP.setDescripcion(editarDescripcion.getText());
        
        if(this.pdf != null)
            newP.setSrc(this.ftpManager.subirPDF(this.pdf.getAbsolutePath(), newP.getNombre() + ".pdf", this.u.getNombreUsuario()));
        
        if(this.newMp3 != null)
            newP.setMp3(this.ftpManager.subirMP3(this.newMp3.getAbsolutePath(), newP.getNombre() + ".mp3", this.u.getNombreUsuario()));
        
        
        if(this.pm.editarPartitura(newP, this.p) > 0){
            Alert aepa = new Alert(Alert.AlertType.INFORMATION);
                aepa.setTitle("INFORMACI??N");
                aepa.setHeaderText("Partitura editada");
                aepa.setContentText("Partitura editada correctamente");
                aepa.showAndWait();
                
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartiturasBanda.fxml"));
                    this.AP.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        }
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        if(this.pm.guardarPartitura(p, u) > 0){
            
             Alert aepa = new Alert(Alert.AlertType.INFORMATION);
             
                aepa.setTitle("INFORMACI??N");
                aepa.setHeaderText("Partitura Guarda");
                aepa.setContentText("La partitura se ha guardado correctamente");
                aepa.showAndWait();
                
        }  
        
    }

    @FXML
    private void showEditar(ActionEvent event) {
        
        if(!editarPane.isVisible())
            editarPane.setVisible(true);
        else if(editarPane.isVisible())
            editarPane.setVisible(false);
        
    }

    @FXML
    private void editarPdf(ActionEvent event) {
        
        fileChooserPDF();
        
    }

    @FXML
    private void editarMp3(ActionEvent event) {
        
        fileChooserMP3();
        
    }
    
    private void fileChooserPDF(){
         
        this.pdf = null;
        
        //Crea un file chooser para pdf
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        this.pdf = fileChooser.showOpenDialog(null);
          
    }
    
    private void fileChooserMP3(){
        
        this.mp3 = null;
        
        //Crea un file chooser para mp3
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3", "*.mp3"));
        this.newMp3 = fileChooser.showOpenDialog(null);
        
        
    }
    
}
