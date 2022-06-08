/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.UsuarioModel;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javaproject.model.FTPManager;


/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLAjustesUsuarioController implements Initializable {

    private UsuarioModel usuarioModel = new UsuarioModel();

    private Singleton s = Singleton.getInstance();
    private Usuario usuario = s.us;
    private FTPManager ftp = new FTPManager();
    private File img;

    

    @FXML
    private AnchorPane AjustesUsuario;
    @FXML
    private TextField nombreText;
    @FXML
    private TextField ApellidosText;
    @FXML
    private TextField CorreoText;
    @FXML
    private DatePicker FechaNacimientoText;
    @FXML
    private PasswordField ContraseñaModifyText;
    @FXML
    private Button EliminarButton;
    @FXML
    private TextField ContraseñaEliminarText;
    @FXML
    private ChoiceBox<String> Nacionalidad;
    @FXML
    private Button modificarBoton;
    @FXML
    private ImageView Imagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Nacionalidad.getItems().setAll("España", "Francia", "México", "Italia", "Portugal");


    }

    @FXML
    private void modificarUsuario(ActionEvent event) throws IOException {

        //Alerta confirmación modificacion usuario
        Alert ac = new Alert(Alert.AlertType.INFORMATION);
        ac.setTitle("INFORMATION DIALOG");
        ac.setHeaderText("Modificación usuario");
        ac.setContentText("El usuario ha sido modificado correctamente");
        //Alerta error en modificacion del usuario
        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("INFORMATION DIALOG");
        ai.setHeaderText("Error");
        ai.setContentText("No has introducido la contraseña o es incorrecta");

        if (this.usuario.getContraseña().equals(ContraseñaModifyText.getText())) {

            Usuario newUsr = this.usuario;

            if (!nombreText.getText().isEmpty()) {
                newUsr.setNombre(nombreText.getText());
            }
            if (!ApellidosText.getText().isEmpty()) {
                newUsr.setApellidos(ApellidosText.getText());
            }
            if (!CorreoText.getText().isEmpty()) {
                newUsr.setCorreo(CorreoText.getText());
            }
            if (FechaNacimientoText.getValue() == null) {

            } else {
                newUsr.setFechaNacimiento(java.sql.Date.valueOf(FechaNacimientoText.getValue()));
            }

            if (Nacionalidad.getValue() == null) {

            } else {
                newUsr.setNacionalidad(Nacionalidad.getValue().toString());
            }
            
            if(this.img != null){
                    newUsr.setImg(this.ftp.subirIMGUsuario(this.img.getAbsolutePath(), this.img.getName(), newUsr.getNombreUsuario()));
                }      

            //Comprueba si la modificación es correcta
            if (this.usuarioModel.editarUsuario(newUsr, this.usuario) > 0) {
                
                ac.showAndWait();
            } else {

            }
        } else {
            ai.show();
        }
    }

    @FXML
    //Método para eliminar el usuario
    private void EliminarUsuario(ActionEvent event) throws IOException {
        
        //Alerta confirmación de eliminación usuario
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("Eliminar usuario");
        ac.setHeaderText("¿Desea eliminar definitivamente el usuario?");
        ac.setContentText("Esta accion es irreversible");
        
        //Alerta error en eliminación usuario
        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("Error");
        ai.setHeaderText("Error");
        ai.setContentText("La contraseña o es incorrecta");

        //Alerta confirmación eliminación usuario
        Alert ae = new Alert(Alert.AlertType.INFORMATION);
        ae.setTitle("Usuario aliminado");
        ae.setHeaderText("Eliminación usuario");
        ae.setContentText("El usuario se ha eliminado satisfactoriamente");

        //Alerta de campo contraseña vacío
        Alert av = new Alert(Alert.AlertType.INFORMATION);
        av.setTitle("Contraseña vacía");
        av.setHeaderText("Contraseña vacía");
        av.setContentText("El campo contraseña está vacío");

        if (this.usuario.getContraseña().equals(ContraseñaEliminarText.getText())) {

            ac.showAndWait();

        } else if (ContraseñaEliminarText.getText().isEmpty()) {

            av.showAndWait();
        }

        if (ac.getResult() == ButtonType.OK) {
            if (this.usuarioModel.eliminarUsuario(this.usuario) > 0) {
                ae.showAndWait();
                //Se devuelve al usuario a la pantalla de inicio tras eliminar al usuario 
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLPantallaInicio.fxml"));
                    this.AjustesUsuario.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ai.show();
            }
        }
    }
    //Creamos un efecto de brillo
    Glow glow = new Glow();
    


    @FXML
   
    //Aplicar un efecto de brillo al boton
    private void modificarDatosUsuarioHover(MouseEvent event) {
        //Efecto mientras el raton esté dentro del boton "modificar"
        modificarBoton.setEffect(glow);
    }

    @FXML
    private void modificarDatosUsuarioExit(MouseEvent event) {
        //Se anula el efecto al sacar el raton del área del boton
        modificarBoton.setEffect(null);
    }

    @FXML
    //Aplicar un efecto de brillo al boton
    private void eliminarMouseExited(MouseEvent event) {
        //se anula el efecto al sacar el raton del área del boton
        EliminarButton.setEffect(null);
    }

    @FXML
     
    private void eliminarMouseEntered(MouseEvent event) {
        //Efecto mientras el raton esté dentro del boton "eliminar"
        EliminarButton.setEffect(glow);
    }

    @FXML
    private void EditarImagen(ActionEvent event) {
        
        fileChooserIMG();
    }
    
    
    public void fileChooserIMG(){
        
        //Crea un file chooser para imagenes
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img", "*.png", "*.jpg"));
        this.img = fileChooser.showOpenDialog(null);
        
        if(this.img != null){
        
            Image imag = new Image(this.img.toURI().toString());

            this.Imagen.setImage(imag);
        
        }
        
    }
}
