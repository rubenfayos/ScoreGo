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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javaproject.clases.Partitura;
import javaproject.clases.Post;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.PartituraModel;

/**
 * FXML Controller class
 *
 * @author Fayos
 */
public class FXMLMisPartiturasController implements Initializable {
    
    private PartituraModel pm = new PartituraModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Partitura p = new Partitura();

    @FXML
    private AnchorPane AP;
    @FXML
    private ScrollPane scrollPanelMisPartituras;
    @FXML
    private GridPane partituraGP;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String num = "";

        //Coge las partiturasd de la base de datos
        ObservableList<Partitura> partituras = this.pm.listarPartiturasUsuario(this.u);
        
        int filas = 0;
        
        for(int i = 0; i <= partituras.size(); i=i+4){
            
            if(i < partituras.size())
                filas++;
        }
        
        partituraGP.setPrefHeight(248*filas);
        
        
        //Define la cantidad de filas y su tama??o
        for (int i = 0; i < filas-1; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(248);
            partituraGP.getRowConstraints().add(rowConst);    
        }
        
        int cont = 0;

        for(int i = 0; i < filas; i++){
            for(int x = 0; x < 4; x++){
                
                //Crea un nuevo pane donde se mostrar?? la partitura
                Pane pa= new Pane();
                
                //Crea un nuevo boton 
                Button b = new Button();
                b.setPrefSize(200, 200);
                //Coge el id de la partitura
                b.setId(Integer.toString(partituras.get(cont).getId()));
                
                Image img = new Image("https://scorego.ddns.net/scoregoFiles/partitura.jpg", 200, 200, false, true);
                    BackgroundImage fondoBotonBanda = new BackgroundImage( img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                    Background fondoImagenBanda = new Background(fondoBotonBanda);
                    b.setBackground(fondoImagenBanda);
                
                //A??ade la accion de ver la partitura al botton
                b.setOnAction((event) -> {
                    
                    VerPartitura(b.getId());
                    
                });
                
                pa.getChildren().add(b);

                //Creaci??n de label del nombre de la partitura
                pa.getChildren().add(crearLabel(partituras.get(cont).getNombre()));
                
                //A??ade el pane al GridPane
                partituraGP.add(pa, x, i);
                cont++;
                
                if(cont == partituras.size()){
                    x=4;
                    i=(int) filas;
                }
            }   
        }
    }    

    private void switchToPartitura(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void A??adirPartitura(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void VerPartitura(String id){
        
        this.p.setId(Integer.parseInt(id));
        this.s.p = this.p;
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLVerPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public Label crearLabel(String p){
        
        Label l = new Label(p);
               
                l.setLayoutY(210);
                l.setFont(new Font(16));
                l.setPrefWidth(200);
                l.setAlignment(Pos.CENTER);
    
        return l;
    }
    
    
}
