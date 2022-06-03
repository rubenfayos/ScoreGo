package javaproject.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 
/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */

public class FTPManager {
    
    private FTPClient ftpClient;
 
    public static void main(String[] args) {
             
    }
    
    public FTPManager(){
        
    }
    
    public void getConexion(){
        
        String server = "cloudscorego.ddns.net";
        int port = 21;
        String user = "admin_cloud";
        String pass = "12345678";
        
        this.ftpClient = new FTPClient();
        try {
 
            this.ftpClient.connect(server, port);
            this.ftpClient.login(user, pass);
            this.ftpClient.enterLocalPassiveMode();
 
            this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } 
        
    }
    
    public String subirPDF(String archivoLocal, String nombre) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/data/admin/files/Partituras/pdf/";
        firstRemoteFile+=nombre.toString();
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("Archivo subido");
        }
        
        return firstRemoteFile;
    	
    }
    
    public String subirMP3(String archivoLocal, String nombre) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/data/admin/files/Partituras/mp3/";
        firstRemoteFile+=nombre.toString();
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("Archivo subido");
        }
        
        return firstRemoteFile;
    	
    }
    	
    public boolean downloadFile(String rutaRemota, String rutaLocal) {
        
        boolean done = false;
        
        this.getConexion();
        
        //Define la ruta local donde se descargará
        try (FileOutputStream fos = new FileOutputStream(rutaLocal)) {
            
            //Recibe el archivo
            done = this.ftpClient.retrieveFile(rutaRemota, fos);

            if(done){
                System.out.println("The file was downloaded successfully.");
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return done;
        
    }   	  	
}