/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javaproject.clases.Banda;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLMiembrosBandaController implements Initializable {
    
    private BandasModel bm = new BandasModel();
    private Singleton s = Singleton.getInstance();
    private Banda b = s.b;

    @FXML
    private TableView<Usuario> tablaMiembros;
    @FXML
    private Label nombreUsuarioText;
    @FXML
    private Label nombreText;
    @FXML
    private Label apellidosText;
    @FXML
    private Label nacionalidadText;
    @FXML
    private TableColumn nombreColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        usuarios = this.bm.listarUsuarios(b);
        
        this.nombreColumn.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
        
        this.tablaMiembros.setItems(usuarios);
        
        
        
    }    

    @FXML
    private void mostrarUsuario(MouseEvent event) {
        
        Usuario u = this.tablaMiembros.getSelectionModel().getSelectedItem();
        
        nombreUsuarioText.setText(u.getNombreUsuario());
        nombreText.setText(u.getNombre());
        apellidosText.setText(u.getApellidos());
        nacionalidadText.setText(u.getNacionalidad());
        
    }
    
}
