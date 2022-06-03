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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
        
        double filas = 1;
        double size = partituras.size();
        
        if(partituras.size() >4){
            filas = size/4;
            if((filas%1) != 0)
                filas++;
        }
        
        int filas2 = (int) filas;
        
        int columnas=4;
        int cont = 0;
        
        //Define la cantidad de filas y su tamaño
        for (int i = 0; i < filas2-1; i++) {
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPrefHeight(220);
        partituraGP.getRowConstraints().add(rowConst);    
        }
        
        partituraGP.setPrefHeight(220*filas);

        for(int i = 0; i < filas; i++){
            for(int x = 0; x < columnas; x++){
                Pane pa= new Pane();
                Button b = new Button(partituras.get(cont).getNombre());
                b.setPrefSize(200, 200);
                //Coge el id de la partitura
                b.setId(Integer.toString(partituras.get(cont).getId()));
                b.setOnAction((event) -> {
                    
                    VerPartitura(b.getId());
                    
                });
                pa.getChildren().add(b);
                partituraGP.add(pa, x, i);
                cont++;
                
                if(cont == partituras.size()){
                    x=columnas;
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
    private void AñadirPartitura(ActionEvent event) {
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLSubirPartitura.fxml"));
            this.AP.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
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
    
}
