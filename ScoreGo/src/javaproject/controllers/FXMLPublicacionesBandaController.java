/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javaproject.clases.Banda;
import javaproject.clases.Post;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.PostModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPublicacionesBandaController implements Initializable {
    
    private PostModel psm = new PostModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Banda b = s.b;

    @FXML
    private ScrollPane scrollPanelPost;
    @FXML
    private GridPane postGP;
    @FXML
    private AnchorPane anchorPaneBandasInside;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Post> posts = this.psm.listarPostBanda(this.b);
        
        postGP.setPadding(new Insets(10, 10, 10, 10));
         
        for (int i = 0; i < posts.size()-1; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(200);
            postGP.getRowConstraints().add(rowConst);    
        }
        
        postGP.setPrefHeight(200*posts.size());
        
        int cont = 0;
        
        for(Post p : posts){
            
            Pane pa= new Pane();
            pa.setPrefHeight(200);
            
            Label titulo = new Label(p.getTitulo());
            titulo.setFont(Font.font("System", FontWeight.BOLD, 22));
            
            Label texto = new Label(p.getTexto());
            texto.setFont(new Font(14));
            
            Label fecha = new Label(p.getFechaPublicacion());
            fecha.setFont(new Font(14));
            
            Label usuario = new Label(p.getUsuario().getNombreUsuario());
            fecha.setFont(new Font(12));
            
            Separator sp = new Separator(Orientation.HORIZONTAL);
            sp.setPrefWidth(1000);
            
            pa.getChildren().addAll(titulo, fecha, texto, usuario, sp);
            
            pa.getChildren().get(1).setLayoutX(900);
            pa.getChildren().get(2).setLayoutY(30);
            pa.getChildren().get(3).setLayoutY(30);
            pa.getChildren().get(3).setLayoutX(900);
            pa.getChildren().get(4).setLayoutY(-5);
            
            postGP.add(pa, 0, cont);
            
            cont++;
            
        }
        
    }    

}
    

