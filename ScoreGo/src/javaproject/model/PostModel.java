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

    public void eliminarPost(Post p){
        
        try{
            
                //elimina el post
                String sql  = "DELETE from post WHERE id=1";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, p.getId());
                
          
            
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
                
          
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }
   
    public ObservableList<Post> listarPostBanda(Banda b){
        
        ObservableList<Post> posts = FXCollections.observableArrayList();
        
        try{
            
                PreparedStatement stmt = this.getConexion().prepareStatement("SELECT p.*, u.nombreUsuario FROM POST p, usuario u WHERE banda=? AND p.usuario=u.id");
            
                stmt.setInt(1, b.getId());
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    
                    Post p = new Post();
                    Usuario u = new Usuario();
                    u.setNombreUsuario(rs.getString("nombreUsuario"));
                    
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
