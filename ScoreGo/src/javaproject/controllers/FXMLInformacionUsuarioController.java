/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javaproject.controllers.FXMLPantallaPrincipalController;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.vistas.ScoreGo;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLInformacionUsuarioController implements Initializable {
    
    private Singleton s = Singleton.getInstance();
    private Usuario us;

    @FXML
    private AnchorPane anchorPaneBandasPost;
    @FXML
    private Label infoNombreUsuario;
    @FXML
    private Label infoCorreoUsuario;
    @FXML
    private Label infoNombreUsuario1;
    @FXML
    private ImageView imagenUsuarioInfo;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Button botonEditarDatos;
    @FXML
    private Hyperlink accesoWeb;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label infoPais;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.us=s.us;
        
        this.infoNombreUsuario.setText(us.getNombre());
        this.infoCorreoUsuario.setText(us.getCorreo());
        this.infoNombreUsuario1.setText(us.getFechaNacimiento().toString());
        this.infoPais.setText(us.getNacionalidad());
        
        //Image imag = new Image(us.get.toURI().toString());
        
        //imagenUsuarioInfo.setImage(imag);
        
        
    }    

    @FXML
    private void clickBotonCerrarSesion(ActionEvent event) throws IOException, Exception {
        
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("INFORMATION DIALOG");
        ac.setHeaderText("Cerrar sesión");
        ac.setContentText("¿Deseas cerrar la sesión?");
        
        ac.showAndWait();
            
        if(ac.getResult() == ButtonType.OK){
            
            ScoreGo sc = new ScoreGo();
            sc.start(new Stage());
        }           
    }

    @FXML
    private void clickBotonEditarDatos(ActionEvent event) {
        
         //Switch a Anchor Pane ajustes
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLAjustes.fxml"));
            this.AnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void clickAccesoWeb(ActionEvent event) {
    }
    
}
