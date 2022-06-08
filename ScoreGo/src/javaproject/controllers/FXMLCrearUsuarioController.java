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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javaproject.clases.Usuario;
import javaproject.model.FTPManager;
import javaproject.model.UsuarioModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLCrearUsuarioController implements Initializable {
    
    private UsuarioModel usuarioModel;
    private FTPManager ftp = new FTPManager();
    private File img;

    @FXML
    private Button añadirUsuarioBoton;
    @FXML
    private TextField add_nombre_usuario;
    @FXML
    private TextField add_apellido_usuario;
    @FXML
    private TextField add_id_usuario;
    @FXML
    private TextField add_contraseña_usuario;
    @FXML
    private TextField add_correo_usuario;
    @FXML
    private DatePicker add_date_usuario;
    @FXML
    private ChoiceBox<String> add_nacionalidad_usuario;
    @FXML
    private AnchorPane AP;
    @FXML
    private ImageView imagenPerfil;
        
   

    public void initialize(URL url, ResourceBundle rb) {
        
        //Se hace la conexión con la base de datos
        this.usuarioModel=new UsuarioModel();
        
        //Selector de nacionalidad del registro de usuario
        add_nacionalidad_usuario.getItems().setAll("España", "Francia", "México", "Italia", "Portugal");
        
           
    }    
    
    @FXML
    private void añadirUsuario(ActionEvent event) throws IOException {
        
        //Alerta confirmación creación usuario
        Alert ac = new Alert(Alert.AlertType.INFORMATION);
        ac.setTitle("INFORMATION DIALOG");
        ac.setHeaderText("Creación usuario");
        ac.setContentText("Usuario creado satisfactoriamente");
        
        //Alerta error por falta de campo
        Alert aw = new Alert(Alert.AlertType.WARNING);
        aw.setTitle("¡¡WARNING DIALOG!!");
        aw.setHeaderText("Error");
        aw.setContentText("Faltan campos por introducir");

        //Alerta error por usuario existente
        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("INFORMATION DIALOG");
        ai.setHeaderText("Error");
        ai.setContentText("El usuario " + add_id_usuario.getText() + " ya existe");
        
        if (add_nombre_usuario.getText().isEmpty() || add_apellido_usuario.getText().isEmpty() || add_id_usuario.getText().isEmpty() || 
            add_contraseña_usuario.getText().isEmpty() || add_correo_usuario.getText().isEmpty() || add_date_usuario.getValue() == null || 
            add_nacionalidad_usuario.getValue() == null){
                aw.show();
        }else{
            
            //Coge los datos del usuario de los campos
            Usuario u = new Usuario(add_nombre_usuario.getText(), add_apellido_usuario.getText(), add_id_usuario.getText(), add_contraseña_usuario.getText(), add_correo_usuario.getText(), java.sql.Date.valueOf(add_date_usuario.getValue()) ,add_nacionalidad_usuario.getValue().toString());
            this.ftp.makeDirectory(u.getNombreUsuario());
            
            if(this.img != null)
                u.setImg(this.ftp.subirIMGUsuario(this.img.getAbsolutePath(), this.img.getName(), u.getNombreUsuario()));
            
            //Comprueba que la insercion SQL sea correcta
            if(this.usuarioModel.crearUsuario(u) >0){
                ac.showAndWait();
                
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaInicio.fxml"));
                    this.AP.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }else{
                ai.show();
            }
        }
        
        
    }

    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaInicio.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 

    @FXML
    private void subirImagen(ActionEvent event) {
        
        fileChooserIMG();
    }
    
    
    public void fileChooserIMG(){
        
        //Crea un file chooser para imagenes
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img", "*.png", "*.jpg"));
        this.img = fileChooser.showOpenDialog(null);
        
        if(this.img != null){
        
            Image imag = new Image(this.img.toURI().toString());

            imagenPerfil.setImage(imag);
        
        }
        
    }
}
