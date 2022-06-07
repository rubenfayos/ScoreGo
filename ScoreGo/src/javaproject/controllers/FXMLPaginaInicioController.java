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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javaproject.clases.Banda;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;
import javaproject.clases.Usuario;
import javaproject.model.BandasModel;
import javaproject.model.PartituraModel;

/**
 * FXML Controller class
 *
 * @author 1erDAM
 */
public class FXMLPaginaInicioController implements Initializable {
    
    PartituraModel pm = new PartituraModel();
    BandasModel bm = new BandasModel();
    private Singleton s = Singleton.getInstance();
    private Usuario u = s.us;
    private Partitura p = new Partitura();

    @FXML
    private GridPane PartiturasGP;
    @FXML
    private GridPane BandasGP;
    @FXML
    private AnchorPane AP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mostrarPartituras();
         
        mostrarBandas();
         
           
    }

    private void mostrarPartituras(){
        
        ObservableList<Partitura> partituras = FXCollections.observableArrayList();
        
        partituras = this.pm.listarPartiturasInicio();
        
        for(int x = 0; x < 4; x++){
                
                //Crea un nuevo pane donde se mostrará la partitura
                Pane pa= new Pane();
                
                //Crea un nuevo boton 
                Button b = new Button();
                b.setPrefSize(200, 200);
                //Coge el id de la partitura
                b.setId(Integer.toString(partituras.get(x).getId()));
                //Añade la accion de ver la partitura al botton
                b.setOnAction((event) -> {
                    
                    VerPartitura(b.getId());
                    
                });
                
                pa.getChildren().add(b);

                //Creación de label del nombre de la partitura
                pa.getChildren().add(crearLabel(partituras.get(x).getNombre()));
                
                //Añade el pane al GridPane
                PartiturasGP.add(pa, x, 0);   
        }
        
    }
        
    private void mostrarBandas(){
        
        ObservableList<Banda> bandas = FXCollections.observableArrayList();
        
        bandas = this.bm.listarBandas(this.u);
        
        int columnas = 0;
        
        if(bandas.size() > 4)
            columnas = 4;
        else
            columnas = bandas.size();
        
        for(int x = 0; x < columnas; x++){
            Pane pa= new Pane();
            Button b = new Button();
            b.setPrefSize(200, 200);
            //seleciona el fondo de los botones
            //BackgroundImage fondoBotonBanda = new BackgroundImage( new Image("https://imagenes.elpais.com/resizer/GCZ-g8TivYpeM5dCIbrlC5d6I2I=/1200x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/5OZUGSTV3ZFRTFVRZSTYIOGDZY.jpg", 190, 190, true, true) ,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            //Background fondoImagenBanda = new Background(fondoBotonBanda);
            //b.setBackground(fondoImagenBanda);

            //Coge el id de la banda
            b.setId(Integer.toString(bandas.get(x).getId()));
            b.setOnAction((event) -> {

                switchToBanda(b.getId());

            });

            pa.getChildren().add(b);
            pa.getChildren().add(crearLabel(bandas.get(x).getNombre()));
            BandasGP.add(pa, x, 0);

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
    
    private void switchToBanda(String id) {
        
        Banda b = new Banda();
        b.setId(Integer.parseInt(id));
        this.s.b=b;
        
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/javaproject/vistas/FXMLBanda.fxml"));
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
