/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javaproject.model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javaproject.clases.Partitura;
import javaproject.clases.Singleton;

/**
 *
 * @author 1erDAM
 */
public class mp3Player implements Initializable {
    
    private URL resource;
    
    private Singleton s = Singleton.getInstance();
    private Partitura p = s.p;
    
    @FXML
    private Button detenerButton;
    @FXML
    private Button button;
    @FXML
    private Button pauseButton;
    @FXML
    private Label label;
    
    private MediaPlayer mp;
                    
   /**
    * Assign a mp3 for the mediaPlayer
    * @param url
    * @param rb 
    */
                    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Media md = new Media("https://scorego.ddns.net" + this.p.getMp3());
        
        this.mp = new MediaPlayer(md);
        
    }
    

    public void start(Stage primaryStage) {
    
    }
    
    
    
    /**
     * Starts the mp3
     * 
     */
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
      this.mp.play();
    }
    
    /**
     * Pause the mp3
     * @param event 
     */

    @FXML
    private void handlePauseButton(ActionEvent event) { 
        this.mp.pause();
    }
    
    /**
     * Stops completely the mp3
     * @param event 
     */

    @FXML
    private void handleDetenerButton(ActionEvent event) {
        this.mp.stop();
    }

    public mp3Player() {
    }

    		
}
	
        
    

