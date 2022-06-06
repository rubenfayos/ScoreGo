/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                stmt.setString(3, p.getTítulo());
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
   
    
    
    
    
}
