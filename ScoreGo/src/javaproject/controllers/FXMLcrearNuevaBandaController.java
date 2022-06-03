
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this templates
 */
package javaproject.controllers;

import java.beans.EventHandler;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;
import javafx.scene.image.Image;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLcrearNuevaBandaController implements Initializable {
    
    private BandasModel bm;
    
    private File img;
    
    private Usuario usuario;
    
    private Singleton s = Singleton.getInstance();

    @FXML
    private AnchorPane anchorPaneBandasPost;
    @FXML
    private TextField crearNombreDeBanda;
    @FXML
    private TextArea crearDescripcionDeBanda;
    @FXML
    private Button botonAnyadirFotoPerfil;
    @FXML
    private ImageView previsualizacion;
    @FXML
    private PasswordField contraseñaBanda;
    @FXML
    private AnchorPane anchorPaneCrearBanda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.bm = new BandasModel();
        
        this.usuario = s.us;
        
    }    

    @FXML
    private void fotoPerfil(ActionEvent event) {
        
        fileChooserIMG();
        
    }

    @FXML
    private void crearBanda(ActionEvent event) {
        
        Alert ae = new Alert(AlertType.ERROR);
        ae.setTitle("ERROR");
        ae.setHeaderText("Esto es un mensaje de error");
        ae.setContentText("Faltan campos por introducir");
        
        Alert ae2 = new Alert(AlertType.ERROR);
        ae2.setTitle("ERROR");
        ae2.setHeaderText("Esto es un mensaje de error");
        ae2.setContentText("No se ha seleccionado ninguna imagen");
        
        Alert ai = new Alert(AlertType.INFORMATION);
        ai.setTitle("INFORMACIÓN");
        ai.setHeaderText("Esto es un mensaje de información");
        ai.setContentText("La banda se ha creado correctamente");
        
        if(crearNombreDeBanda.getText().isEmpty() || crearDescripcionDeBanda.getText().isEmpty() || contraseñaBanda.getText().isEmpty()){
            ae.show();
        }
        
        if(previsualizacion.getImage() == null){
            ae2.show();
        }
        
        
        if(crearNombreDeBanda.getText() != null && crearDescripcionDeBanda.getText() != null && contraseñaBanda.getText() != null && previsualizacion.getImage() != null){
            
            ai.show();
            
            crearNombreDeBanda.setText(""); 
            crearDescripcionDeBanda.setText("");
            contraseñaBanda.setText("");
            previsualizacion.setImage(null);
            
            try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPublicacionesBanda.fxml"));
            this.anchorPaneCrearBanda.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        }
        
        Banda b = new Banda();
        
        b.setNombre(crearNombreDeBanda.getText());
        b.setContraseña(contraseñaBanda.getText());
        b.setDescripcion(crearDescripcionDeBanda.getText());
        b.setImg(this.img.getAbsolutePath());
        b.setAdministrador(this.usuario);
        
        this.bm.crearBanda(b);
        
        
        
    }
    
    public void fileChooserIMG(){
        
        //Crea un file chooser para pdf
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img", "*.png", "*.jpg"));
        this.img = fileChooser.showOpenDialog(null);
        
        Image imag = new Image(this.img.toURI().toString());
        
        previsualizacion.setImage(imag);
        
        /*final Button openButton = new Button("Open a Picture...");
        
        openButton.setOnAction(
                new EventHandler<ActionEvent>(){
                    private Window stage;
                    public void handle (final ActionEvent e){
                        File file = fileChooser.showOpenDialog(stage);
                        if(file != null){
                            openFile(file);
                        }
                    }

            private void openFile(File file) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
                });*/
        
        
    }
    
    
}
