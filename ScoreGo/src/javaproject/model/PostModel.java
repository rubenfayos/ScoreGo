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
    
    public void subirPost(Post p, Usuario u, Banda b){
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                String sql = "INSERT INTO post(usuario, banda, titulo, texto VALUES(?,?,?,?);";
                PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, u.getId());
                stmt.setString(3, b.getNombre());
                stmt.setString(4, p.getTexto()); 
          
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
    }

    public PostModel() {
    }
    
    
    
    
}
