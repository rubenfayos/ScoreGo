/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javaproject.model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author 1erDAM
 */
public class mp3Player implements Initializable {
    
  
    @FXML
    private Button detenerButton;
    @FXML
    private Button button;
    @FXML
    private Button pauseButton;
    
    private MediaPlayer mp;
                    
   
                    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        URL resource = getClass().getResource("media.mp3");
            // wir legen das Mediaobjekt and und weisen unsere Resource zu
        Media media = new Media(resource.toString());
            // wir legen den Mediaplayer an und weisen ihm das Media Objekt zu
        this.mp = new MediaPlayer(media);
        
    }
    

    public void start(Stage primaryStage) {
          
    }
    
    @FXML
    private Label label;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
      this.mp.play();
    }

    @FXML
    private void handlePauseButton(ActionEvent event) { 
        this.mp.pause();
    }

    @FXML
    private void handleDetenerButton(ActionEvent event) {
        this.mp.stop();
    }
    
    public void reproductor(){
        
               
    }

    public mp3Player() {
    }
    
    
    
    
        
		
}
	
        
    

