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
    
    /**
     * Creates a band in the database
     * @param b The band
     * @return an int for the check
     */
    
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
    
    /**
     * Joins the user to a band
     * @param nombre The name of the band
     * @param contraseña The password of the band
     * @param u The user
     * @return an int for the check
     */
    
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
    
    /**
     * Leaves the user from a band in the database
     * @param b The band
     * @param u The user
     * @return an int for the check
     */
    
    public int AbandonarBanda(Banda b, Usuario u){
        
        int i = 0;
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("DELETE FROM usuarios_banda WHERE banda=? AND usuario=?;");
                
                stmt.setInt(1, b.getId());
                stmt.setInt(2, u.getId());
                
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
    
    /**
     * 
     * @param id Id of a band
     * @return A band
     */
    
    public Banda listarBanda(int id){
        
        Banda b = new Banda();
        
        try{
        
        //Vamos a comprobar que el usuario del login es correcto
        PreparedStatement stmt = this.getConexion().prepareStatement("SELECT * FROM banda WHERE id=?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
        Usuario u = new Usuario();
        u.setId(rs.getInt("administrador"));
        b.setAdministrador(u);
        
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
    
    /**
     * 
     * @param u The user
     * @return An observable list with the bands of a user
     */
    
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
    
    /**
     * 
     * @param b The band
     * @return An observable list with all the users of a band
     */
    
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
    
    /**
     * Delete a band in the database
     * @param b The band
     * @return an int for the check
     */
    
    public int eliminarBanda(Banda b){
        
        
        int i = 0;
        
        try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("DELETE FROM banda WHERE id=?;");
                
                stmt.setInt(1, b.getId());
                
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
    
    
    
}
