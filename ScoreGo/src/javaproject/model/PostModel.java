/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

import com.mysql.cj.xdevapi.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javaproject.clases.Banda;
import javaproject.clases.Post;
import javaproject.clases.Usuario;

/**
 * The user model for the database
 * @author Fayos
 */
public class PostModel extends DBUtil{
    
    /**
     * Upload the post
     * @param p The post
     * @param b The Banda
     * @param u The user that uploads the post
     * @return an int for the check
     */
    
    public int subirPost(Post p, Banda b,Usuario u){
        
        int i = 0;
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO post(usuario, banda, titulo, texto) VALUES(?,?,?,?)");
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, b.getId());
                stmt.setString(3, p.getTitulo());
                stmt.setString(4, p.getTexto()); 
                
                i=stmt.executeUpdate();
          
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
        
        return i;
        
    }
    
    /**
     * Delete the post in the database
     * @param p The post
     * @return an int for the check
     */

    public int eliminarPost(Post p){
        
        int i = 0;
        
        try{
            
                //elimina el post
                String sql  = "DELETE from post WHERE id=?";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, p.getId());
                
                i=stmt.executeUpdate();
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
        
        return i;     
    }
    
    /**
     * Modify a post
     * @param np New post
     * @param ap Old post
     * @return an int for the check
     */
    
    public int editarPost(Post np, Post ap){
        
        int i = 0;
        
        try{
            
                PreparedStatement stmt = this.getConexion().prepareStatement("UPDATE post SET titulo=?, texto=? WHERE id=?");
            
                stmt.setString(1, np.getTitulo());
                stmt.setString(2, np.getTexto());
                stmt.setInt(3, ap.getId());
                        
                i = stmt.executeUpdate();
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
        
        return i;    
    }
    
    /**
     * 
     * @param b The band for the posts
     * @return An observable list with the post of the band
     */
   
    public ObservableList<Post> listarPostBanda(Banda b){
        
        ObservableList<Post> posts = FXCollections.observableArrayList();
        
        try{
            
                PreparedStatement stmt = this.getConexion().prepareStatement("SELECT p.*, u.nombreUsuario FROM post p, usuario u WHERE banda=? AND p.usuario=u.id");
            
                stmt.setInt(1, b.getId());
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    Post p = new Post();
                    Usuario u = new Usuario();
                    u.setNombreUsuario(rs.getString("nombreUsuario"));
                    
                    p.setTitulo(rs.getString("titulo"));
                    p.setTexto(rs.getString("texto"));
                    p.setFechaPublicacion(rs.getDate("fechaPublicacion").toString());
                    p.setUsuario(u);
                    
                    posts.add(p);
                    
                }
                
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
        
        return posts;
    }
    
    /**
     * 
     * @param u The user
     * @param b The band
     * @return An observable list with the post of an user in the band
     */
    
    public ObservableList<Post> listarPostUsuario(Usuario u, Banda b){
        
        ObservableList<Post> posts = FXCollections.observableArrayList();
        
        try{
            
                PreparedStatement stmt = this.getConexion().prepareStatement("SELECT p.* FROM post p, banda b WHERE usuario=? AND p.banda=b.id AND b.id=?;");
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, b.getId());
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    Post p = new Post();
                    
                    p.setId(rs.getInt("id"));
                    p.setTitulo(rs.getString("titulo"));
                    p.setTexto(rs.getString("texto"));
                    p.setFechaPublicacion(rs.getString("fechaPublicacion").toString());
                    p.setUsuario(u);
                    
                    posts.add(p);
                    
                }
                
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
        
        return posts;
             
    }
      
}
