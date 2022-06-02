/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.UsuarioModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLAjustesUsuarioController implements Initializable {
    
    private SceneController sc;
    
    private UsuarioModel usuarioModel;
    
    private Usuario usuario;
    
    private Singleton s = Singleton.getInstance();

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.sc = new SceneController();
        
        this.usuarioModel = new UsuarioModel();
        
        Nacionalidad.getItems().setAll("España", "Francia", "México", "Italia", "Portugal");
        
        //Asigna el usuario
        this.usuario = s.us;
        
    }    

    @FXML
    private void modificarUsuario(ActionEvent event) {
        
        
        //Alerta confirmación creación usuario
        Alert ac = new Alert(Alert.AlertType.INFORMATION);
        ac.setTitle("INFORMATION DIALOG");
        ac.setHeaderText("Modificación usuario");
        ac.setContentText("El usuario ha sido modificado correctamente");
        
        
        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("INFORMATION DIALOG");
        ai.setHeaderText("Error");
        ai.setContentText("No has introducido la contraseña o es incorrecta");
        
        if(this.usuario.getContraseña().equals(ContraseñaModifyText.getText())){

                Usuario newUsr = this.usuario;

                if(!nombreText.getText().isEmpty())
                    newUsr.setNombre(nombreText.getText());
                if(!ApellidosText.getText().isEmpty())
                    newUsr.setApellidos(ApellidosText.getText());
                if(!CorreoText.getText().isEmpty())
                     newUsr.setCorreo(CorreoText.getText());
                if(FechaNacimientoText.getValue() == null){

                }else{
                    newUsr.setFechaNacimiento(java.sql.Date.valueOf(FechaNacimientoText.getValue()));
                }

                if(Nacionalidad.getValue() == null){

                }else{
                    newUsr.setNacionalidad(Nacionalidad.getValue().toString());
                }

                //Comprueba si la modificación es correcta
                if(this.usuarioModel.editarUsuario(newUsr, this.usuario) > 0){
                    ac.showAndWait();
                }else{

                }
        }else{
            ai.show();
        }
        
        
        
        
        
    }

    @FXML
    private void EliminarUsuario(ActionEvent event) throws IOException {
        
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("INFORMATION DIALOG");
        ac.setHeaderText("¿Deseas eliminar definitivamente el usuario?");
        ac.setContentText("Esta accion es irreversible");
        
        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("INFORMATION DIALOG");
        ai.setHeaderText("Error");
        ai.setContentText("No has introducido la contraseña o es incorrecta");
        
        //Alerta confirmación creación usuario
        Alert ae = new Alert(Alert.AlertType.INFORMATION);
        ae.setTitle("INFORMATION DIALOG");
        ae.setHeaderText("Eliminación usuario");
        ae.setContentText("El usuario se ha eliminado satisfactoriamente");
        
        if(this.usuario.getContraseña().equals(ContraseñaEliminarText.getText())){
            
            ac.showAndWait();
            
            if(ac.getResult() == ButtonType.OK){           
                if(this.usuarioModel.eliminarUsuario(this.usuario) > 0){
                    ae.showAndWait();
                    this.sc.switchToInicio(event);
                }
                    
            }
            
        }else
            ai.show();
        
    }
}
