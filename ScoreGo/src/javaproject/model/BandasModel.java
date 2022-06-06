/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javaproject.clases.Banda;
import javaproject.clases.Usuario;

/**
 *
 * @author 1erDAM
 */
public class BandasModel extends DBUtil{
    
    public BandasModel(){
        
    }
    
    public int crearBanda(Banda b){
        
        int comp = 0;
        
            try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO banda(nombre, descripcion, contraseña, administrador, img) VALUES(?,?,?,?,?);");
                stmt.setString(1, b.getNombre());
                stmt.setString(2, b.getDescripcion());
                stmt.setString(3, b.getContraseña());
                stmt.setInt(4, b.getAdministrador().getId());
                stmt.setString(5, b.getImg());
                
                //Inserta la partitura
                comp=stmt.executeUpdate();
                 
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
            
            return comp;
        
    }
    
    public int unirseBanda(String nombre, String contraseña, Usuario u){
        
        int i = 0;
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO usuarios_banda VALUES((Select id from banda WHERE nombre=? AND contraseña= ?), ?);");
                
                stmt.setString(1, nombre);
                stmt.setString(2, contraseña);
                stmt.setInt(3, u.getId());
                
                //Inserta la partitura
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
    
    public Banda listarBanda(String nombre){
        
        Banda b = new Banda();
        
        try{
        
        //Vamos a comprobar que el usuario del login es correcto
        PreparedStatement stmt = this.getConexion().prepareStatement("SELECT * FROM banda WHERE nombre=?");
        stmt.setString(1, nombre);

        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
        
        b.setId(rs.getInt("id"));
        b.setNombre(rs.getString("nombre"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setContraseña(rs.getString("contraseña"));
        b.setImg(rs.getString("img"));
        
        }        
        
                 
        }catch (SQLException e) {
            e.printStackTrace();   
        } 

        finally {
            //Cerramos conexion
            this.cerrarConexion();
        }
        
        return b;
        
    }
    
    public ObservableList<Banda> listarBandas(Usuario u){
        
        ObservableList<Banda> bandas = FXCollections.observableArrayList();
        
        try{
        
        //Vamos a comprobar que el usuario del login es correcto
        PreparedStatement stmt = this.getConexion().prepareStatement("SELECT b.* FROM usuarios_banda ub, banda b WHERE ub.banda = b.id AND ub.usuario=?;");
        stmt.setInt(1, u.getId());

        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
        Banda b = new Banda();
        
        b.setId(rs.getInt("id"));
        b.setNombre(rs.getString("nombre"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setContraseña(rs.getString("contraseña"));
        b.setImg(rs.getString("img"));
        
        bandas.add(b);
        
        }
        
        }catch (SQLException e) {
            e.printStackTrace();   
        } 

        finally {
            //Cerramos conexion
            this.cerrarConexion();
        }
        
        
        return bandas;
    }
    
    public ObservableList<Usuario> listarUsuarios(Banda b){
        
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        
        try{
        
        //Vamos a comprobar que el usuario del login es correcto
        PreparedStatement stmt = this.getConexion().prepareStatement("SELECT u.* FROM usuarios_banda ub, usuario u WHERE u.id=ub.usuario AND ub.banda=?;");
        stmt.setInt(1, b.getId());

        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
        Usuario u = new Usuario();
        
        u.setId(rs.getInt("id"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellidos(rs.getString("apellidos"));
        u.setNacionalidad(rs.getString("nacionalidad"));
        
        usuarios.add(u);
        
        }
        
        }catch (SQLException e) {
            e.printStackTrace();   
        } 

        finally {
            //Cerramos conexion
            this.cerrarConexion();
        }
        
         
        return usuarios;
    }
    
    
}
