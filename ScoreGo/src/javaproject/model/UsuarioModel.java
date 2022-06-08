/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

import javaproject.clases.Usuario;
import javaproject.clases.Partitura;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 1erDAM
 */
public class UsuarioModel extends DBUtil{
    
    public int crearUsuario(Usuario u){
        
        int i = 0;
        
        try{
            
            //Creación de usuario añadiendolo a la tabla
            String sql = "";
            PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO usuario(nombreUsuario, nombre, apellidos, contraseña, fechaNacimiento, correo, nacionalidad, img) VALUES (?,?,?,?,?,?,?,?);");
            
            stmt.setString(1, u.getNombreUsuario());
            stmt.setString(2, u.getNombre());
            stmt.setString(3, u.getApellidos());
            stmt.setString(4, u.getContraseña());
            stmt.setDate(5, u.getFechaNacimiento());
            stmt.setString(6, u.getCorreo());
            stmt.setString(7, u.getNacionalidad());
            stmt.setString(8, u.getImg());
            
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
    
    public Usuario iniciarSesion(String nombreUsuario, String contraseña){
        
        Usuario u = new Usuario();
        
        try{
            
            //Vamos a comprobar que el usuario del login es correcto
            PreparedStatement stmt = this.getConexion().prepareStatement("SELECT * FROM usuario WHERE nombreUsuario=? AND contraseña=?");
            
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery();
            
            //Comprueba si hay un usuario con esos credenciales
            if(rs.next()){
                
            //Recoge los datos de usuario para guardarlos como el usuario        
            u = new Usuario(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("nombreUsuario"), rs.getString("contraseña"), rs.getString("correo"), rs.getDate("fechaNacimiento"), rs.getString("correo"));
            u.setId(rs.getInt(1));
            u.setImg(rs.getString("img"));

            }
            
        }catch (SQLException e) {
            e.printStackTrace();   
	} 
        
        finally {
            //Cerramos conexion
            this.cerrarConexion();
        }
        
        return u;
              
    }
    
    public int editarUsuario(Usuario newUsuario, Usuario oldUsuario){
        
        int i = 0;
        
        try{
            
            //Hacemos update con los nuevos datos del usuario sobre el id del usuario
            String sql = "UPDATE usuario SET nombre=?, apellidos=?, fechaNacimiento=?, correo=?, nacionalidad=?, img=?"
                    + "WHERE id=?";
            PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
            stmt.setString(1, newUsuario.getNombre());
            stmt.setString(2, newUsuario.getApellidos());
            stmt.setDate(3, newUsuario.getFechaNacimiento());
            stmt.setString(4, newUsuario.getCorreo());
            stmt.setString(5, newUsuario.getNacionalidad());
            stmt.setString(6, newUsuario.getImg());
            stmt.setInt(7, oldUsuario.getId());
            
            //Ejecutamos el sql y recogemos si el resultado es correcto
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
    
    public int eliminarUsuario(Usuario u){
        
        int i = 0;
        
        try{
            
            //Vamos a comprobar que el usuario del login es correcto
            String sql = "DELETE FROM usuario WHERE id=?;";
            PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
            stmt.setInt(1, u.getId());
            
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
    

     
     
    
}
