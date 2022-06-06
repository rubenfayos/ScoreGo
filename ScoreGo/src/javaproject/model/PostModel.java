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
    
    public void subirPost(int id_u, int id_b, String titulo, String texto){
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                String sql = "INSERT INTO post(usuario, banda, titulo, texto VALUES(?,?,?,?)";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, id_u);
                stmt.setInt(2, id_b);
                stmt.setString(3, titulo);
                stmt.setString(4, texto); 
          
            
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
