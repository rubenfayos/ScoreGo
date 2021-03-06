package javaproject.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javaproject.clases.Usuario;

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
    //Método para conectarse al FTP
    public void getConexion(){
        
        String server = "scorego.ddns.net";
        int port = 21;
        String user = "admin_scorego";
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
    
    public String subirPDF(String archivoLocal, String nombre, String usuario) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/scoregoFiles/usuarios/" + usuario + "/partituras/pdf/";
        firstRemoteFile+=nombre;
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        //indicador de que el archivo ha sido subido con exito
        if (done) {
            System.out.println("Archivo subido");
        }
        
        return "/scoregoFiles/usuarios/" + usuario + "/partituras/pdf/" + nombre;
    	
    }
    
     public String subirIMGPartitura(String archivoLocal, String usuario, String nombre) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/scoregoFiles/usuarios/" + usuario + "/partituras/pdf/";
        firstRemoteFile+=nombre;
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("Archivo subido");
        }
        
        return "/scoregoFiles/usuarios/" + usuario + "/partituras/pdf/" + nombre;
    	
    }
    
    public String subirMP3(String archivoLocal, String nombre, String usuario) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/scoregoFiles/usuarios/" + usuario + "/partituras/mp3/";
        firstRemoteFile+=nombre;
        InputStream inputStream = new FileInputStream(firstLocalFile);
		
        
        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
        //indicador de que el archivo ha sido subido con exito
            System.out.println("Archivo subido");
        }
        
        return "/scoregoFiles/usuarios/" + usuario + "/partituras/mp3/" + nombre;
    	
    }
    
    public String subirIMGUsuario(String archivoLocal, String nombre, String usuario) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/scoregoFiles/usuarios/" + usuario + "/" + nombre;
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
        //indicador de que el archivo ha sido subido con exito
            System.out.println("Archivo subido");
        }
        
        return "https://scorego.ddns.net/scoregoFiles/usuarios/" + usuario + "/" + nombre;
    	
    }
    
    public String subirIMGBanda(String archivoLocal, String nombre, String banda) throws IOException {
        
        this.getConexion();
    	
    	//Archivo que se quiere subir en local
        File firstLocalFile = new File(archivoLocal.toString());

        //Ruta donde se subirá el archivo en el servidor
        String firstRemoteFile = "/public_html/scoregoFiles/bandas/" + banda + "/" + nombre;
        InputStream inputStream = new FileInputStream(firstLocalFile);
		

        System.out.println("Subiendo el archivo...");
        boolean done = this.ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
        //indicador de que el archivo ha sido subido con exito
            System.out.println("Archivo subido");
        }
        
        return "https://scorego.ddns.net/scoregoFiles/bandas/" + banda + "/" + nombre;
    	
    }
    	
    public boolean downloadFile(String rutaRemota, String rutaLocal) {
        
        boolean done = false;
        
        this.getConexion();
        
        //Define la ruta local donde se descargará
        try (FileOutputStream fos = new FileOutputStream(rutaLocal)) {
            
            //Recibe el archivo
            done = this.ftpClient.retrieveFile("/public_html/scoregoFiles/usuarios/si/partituras/pdf/si.pdf", fos);

            if(done){
                System.out.println("The file was downloaded successfully.");
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return done;    
    }

    public void makeDirectory(String dir){
        
        boolean done = false;
        
        this.getConexion();
        
        try {
            //Crea el directorio del usuario
            done = this.ftpClient.makeDirectory("/public_html/scoregoFiles/usuarios/" + dir);
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/usuarios/" + dir + "/partituras");
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/usuarios/" + dir + "/partituras/pdf");
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/usuarios/" + dir + "/partituras/mp3");

            if(done){
                System.out.println("Carpeta del usuario creada.");
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    public void makeDirectoryBanda(String dir){
        
        boolean done = false;
        
        this.getConexion();
        
        try {
            //Crea el directorio del usuario
            done = this.ftpClient.makeDirectory("/public_html/scoregoFiles/bandas/" + dir);
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/bandas/" + dir + "/partituras");
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/bandas/" + dir + "/partituras/pdf");
            this.ftpClient.makeDirectory("/public_html/scoregoFiles/bandas/" + dir + "/partituras/mp3");

            if(done){
                System.out.println("Carpeta del usuario creada.");
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    //Método para cambiar la ruta de los pdfs al renombrarlos
    public String renamePdf(String viejo, String usuario, String partitura) throws IOException{
        
        boolean done = false;
        
        this.getConexion();
        
        String nuevaRuta = "/scoregoFiles/usuarios/" + usuario + "/partituras/pdf/" + partitura + ".pdf";
        
        boolean success = ftpClient.rename("/public_html/" + viejo, "/public_html" + nuevaRuta);
        
            if(done)
                System.out.println("si");
          
         return nuevaRuta;
            
    }
    
    public String renameMp3(String viejo, String usuario, String mp3) throws IOException{
        
        boolean done = false;
        
        this.getConexion();
        
        String nuevaRuta = "/scoregoFiles/usuarios/" + usuario + "/partituras/mp3/" + mp3 + ".mp3";
        
        boolean success = ftpClient.rename("/public_html/" + viejo, "/public_html" + nuevaRuta);
        
            if(done)
                System.out.println("");
            
        return nuevaRuta;
            
            
    }
    
}