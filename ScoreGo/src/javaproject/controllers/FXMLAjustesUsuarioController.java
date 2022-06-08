/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

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

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLAjustesUsuarioController implements Initializable {

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
    @FXML
    private Button modificarBoton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void EliminarUsuario(ActionEvent event) throws IOException {

        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("Eliminar usuario");
        ac.setHeaderText("¿Desea eliminar definitivamente el usuario?");
        ac.setContentText("Esta accion es irreversible");

        Alert ai = new Alert(Alert.AlertType.ERROR);
        ai.setTitle("Error");
        ai.setHeaderText("Error");
        ai.setContentText("La contraseña o es incorrecta");

        //Alerta confirmación creación usuario
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
    Glow glow = new Glow();

    @FXML
   
    //Aplicar un efecto de brillo al boton
    private void modificarDatosUsuarioHover(MouseEvent event) {
        //effecto mientras el raton esté dentro del boton "modificar"
        modificarBoton.setEffect(glow);
    }

    @FXML
    private void modificarDatosUsuarioExit(MouseEvent event) {
        modificarBoton.setEffect(null);
    }
}
