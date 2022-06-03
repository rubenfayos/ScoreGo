/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.FTPManager;
import javaproject.model.PartituraModel;
/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLSubirPartituraController extends FXMLPantallaPrincipalController implements Initializable {

    private PartituraModel partituraModel;
    private Usuario us;
    private Singleton s = Singleton.getInstance();
    
    private ArrayList<Integer> instrumentos ;
    
    private FTPManager ftpManager;
    private File pdf;
    private File mp3;
    

    @FXML
    private TextField nombrePartitura;
    @FXML
    private TextField autorPartitura;
    @FXML
    private CheckBox ClarineteCB;
    @FXML
    private CheckBox OboeCB;
    @FXML
    private CheckBox SaxoTenorCB;
    @FXML
    private CheckBox FlautaTraveseraCB;
    @FXML
    private CheckBox TubaCB;
    @FXML
    private CheckBox BombardinoCB;
    @FXML
    private CheckBox TrompetaCB;
    @FXML
    private CheckBox TrombonCB;
    @FXML
    private CheckBox TrompaCB;
    @FXML
    private CheckBox PercusionCB;
    @FXML
    private CheckBox SaxoCB;
    @FXML
    private Label archivoText;
    @FXML
    private Label audioText;
    @FXML
    private AnchorPane tablaInstrumentos;
    @FXML
    private AnchorPane AP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.partituraModel = new PartituraModel();
        
        this.instrumentos = new ArrayList<Integer>();
        
        this.us=s.us;
        
        this.ftpManager = new FTPManager();
        
    }    
    
    @FXML
    private void SubirPartitura(ActionEvent event) throws IOException {
        
        Alert ae = new Alert(AlertType.ERROR);
        ae.setTitle("ERROR");
        ae.setHeaderText("Esto es un mensaje de error");
        ae.setContentText("Faltan campos por introducir");
        
        Alert ae2 = new Alert(AlertType.ERROR);
        ae2.setTitle("ERROR");
        ae2.setHeaderText("Esto es un mensaje de error");
        ae2.setContentText("Debes seleccionar al menos un instrumento");
        
        Alert ai = new Alert(AlertType.INFORMATION);
        ai.setTitle("INFORMACIÓN");
        ai.setHeaderText("Esto es un mensaje de información");
        ai.setContentText("La partitura se ha subido correctamente");
        
        if(nombrePartitura.getText().isEmpty() || autorPartitura.getText().isEmpty()){
            ae.show();
        }else if(!ClarineteCB.isSelected() && !OboeCB.isSelected() && !SaxoTenorCB.isSelected() && !FlautaTraveseraCB.isSelected() && 
                !TubaCB.isSelected() && !BombardinoCB.isSelected() && !TrompetaCB.isSelected() && !TrombonCB.isSelected() &&
                !TrompaCB.isSelected() && !PercusionCB.isSelected() && !SaxoCB.isSelected()){
            ae2.show();
        }

        comprobarInstrumentos();
        
        Partitura p = new Partitura();
        
        p.setAutor(autorPartitura.getText());
        p.setNombre(nombrePartitura.getText());
        p.setUsuario(this.us);
        //Sube el mp3 al servidor
        p.setMp3(this.ftpManager.subirMP3(this.mp3.getAbsolutePath(), this.mp3.getName()));
        //Sube el pdf al servidor
        p.setSrc(this.ftpManager.subirPDF(this.pdf.getAbsolutePath(), this.pdf.getName()));
        p.setInstrumentos(this.instrumentos);
        
        if(this.partituraModel.subirPartitura(p) == 1){
            ai.showAndWait();
            this.partituraModel.guardarPartitura(p, this.us);
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLMisPartituras.fxml"));
                this.AP.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
    }
    
    @FXML
    private void SubirArchivo(ActionEvent event) {
        
        fileChooserPDF();   
    }

    @FXML
    private void SubirAudio(ActionEvent event) {
        
        fileChooserMP3();
    }
    
    private void comprobarInstrumentos(){
        
        if(this.ClarineteCB.isSelected())
            this.instrumentos.add(1);
        
        if(this.SaxoCB.isSelected())
            this.instrumentos.add(2);
        
        if(this.OboeCB.isSelected())
            this.instrumentos.add(3);
        
        if(this.FlautaTraveseraCB.isSelected())
            this.instrumentos.add(4);
        
        if(this.SaxoTenorCB.isSelected())
            this.instrumentos.add(5);
        
        if(this.TubaCB.isSelected())
            this.instrumentos.add(6);
        
        if(this.BombardinoCB.isSelected())
            this.instrumentos.add(7);
        
        if(this.TrompetaCB.isSelected())
            this.instrumentos.add(8);
        
        if(this.TrombonCB.isSelected())
            this.instrumentos.add(9);
        
        if(this.TrompaCB.isSelected())
            this.instrumentos.add(10);
        
        if(this.PercusionCB.isSelected())
            this.instrumentos.add(11);
     
    }
    
    private void fileChooserPDF(){
        
        //Crea un file chooser para pdf
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));
        this.pdf = fileChooser.showOpenDialog(null);
        
        this.archivoText.setText(this.pdf.getName().toString());
        
    }
    
    private void fileChooserMP3(){
        
        //Crea un file chooser para mp3
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("MP3", "*.mp3"));
        this.mp3 = fileChooser.showOpenDialog(null);
        
        this.audioText.setText(this.mp3.getName().toString());
        
    }

    

}