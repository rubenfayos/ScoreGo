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
 *
 * @author Fayos
 */
public class PostModel extends DBUtil{
    
    public void subirPost(Post p, Banda b,Usuario u){
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO post(usuario, banda, titulo, texto) VALUES(?,?,?,?)");
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, b.getId());
                stmt.setString(3, p.getTitulo());
                stmt.setString(4, p.getTexto()); 
                
                stmt.execute();
          
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }

    public void eliminarPost(Post p,Usuario u,Banda b){
        
        try{
            
                //elimina el post
                String sql  = "DELETE from post WHERE usuario = 1, banda = 2,titulo=3";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, b.getId());
                stmt.setString(3, p.getTitulo());
                
                 stmt.execute();
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }
    public void verPost(Post p,Banda b,Usuario u){
        
        try{
            
                //ve post por la id del usuario
                String sql  = "SELECT u.nombre,b.nombre,p.titulo,p.texto,p.fechaPublicacion\n" +
                              "FROM usuario u,banda b,post p\n" +
                              "WHERE u.id=p.usuario AND b.id=p.banda AND u.id=?";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, u.getId());
                
          stmt.execute();
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }
    public void editarPost(Post p,Banda b,Usuario u){
        
        try{
            
                //ve post por la id del usuario
                String sql  = "UPDATE post\n" +
                                "  SET titulo=? , texto=?\n" +
                                "  WHERE titulo=? or texto=? and usuario=? and banda=?";
                        
                      
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setString(1, p.getTitulo());
                stmt.setString(2, p.getTexto());
                stmt.setString(3, p.getTitulo());
                stmt.setString(4, p.getTexto());
                stmt.setInt(5, u.getId());
                stmt.setInt(6, b.getId());
                        
          stmt.execute();
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }
<<<<<<< HEAD
    
    
    
    
=======
   
    public ObservableList<Post> listarPostBanda(Banda b){
        
        ObservableList<Post> posts = FXCollections.observableArrayList();
        
        try{
            
                PreparedStatement stmt = this.getConexion().prepareStatement("SELECT p.*, u.nombreUsuario FROM POST p, usuario u WHERE banda=? AND p.usuario=u.id");
            
                stmt.setInt(1, b.getId());
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    Post p = new Post();
                    Usuario u = new Usuario();
                    u.setNombre(rs.getString("nombreUsuario"));
                    
                    p.setTítulo(rs.getString("titulo"));
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
      
>>>>>>> 8c0ae1b95c1b21b79f4da9628bc9bad075e2ec15
}
