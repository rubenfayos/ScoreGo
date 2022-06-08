/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

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
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;
import javaproject.model.PartituraModel;
import javaproject.model.PostModel;
import javaproject.model.UsuarioModel;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLBandaInfoController implements Initializable {
    
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Banda b = s.b;
    
    private BandasModel bm = new BandasModel();
    private PartituraModel pm = new PartituraModel();
    private PostModel psm = new PostModel();

    @FXML
    private Label desc;
    @FXML
    private Label totalMiembros;
    @FXML
    private Label totalPartituras;
    @FXML
    private Label totalPost;
    @FXML
    private Button eliminarButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        b=this.bm.listarBanda(this.b.getId());
        
        desc.setText(this.b.getDescripcion());
        totalMiembros.setText(Integer.toString(this.bm.listarUsuarios(b).size()));
        totalPartituras.setText(Integer.toString(this.pm.listarPartiturasBanda(b).size()));
        totalPost.setText(Integer.toString(this.psm.listarPostBanda(b).size()));
        
        if(b.getAdministrador().getId() == this.u.getId()){
            eliminarButton.setVisible(true);
        }
        
    }    

    @FXML
    private void eliminarBanda(ActionEvent event) {
        
        Alert ai = new Alert(Alert.AlertType.INFORMATION);
        ai.setTitle("Eliminar banda");
        ai.setContentText("La banda se ha eliminado correctamente");
        
        //alerta de confirmación para eliminar partitura en una banda
        
        Alert ac = new Alert(Alert.AlertType.CONFIRMATION);
        ac.setTitle("Eliminar banda");
        ac.setHeaderText("¿Seguro que deseas eliminar la partitura?");
        ac.setContentText("Esto eliminará todas las partituras asociadas");
        Optional<ButtonType> result = ac.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            if(this.bm.eliminarBanda(b) > 0){

                ai.showAndWait();
                
   
            }
            
        }
        
    }
    
}
