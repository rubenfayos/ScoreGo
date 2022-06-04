/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javaproject.clases.Partitura;
import javaproject.clases.Usuario;
import jdk.nashorn.internal.objects.NativeRegExp;

/**
 *
 * @author 1erDAM
 */
public class PartituraModel extends DBUtil{
    
        public int subirPartitura(Partitura p){
            
            int comp = 0;
        
            try{
            
                //Vamos a comprobar que el usuario del login es correcto
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO partitura(autor, nombre, usuario, src, mp3) VALUES(?,?,?,?,?);");
            
                stmt.setString(1, p.getAutor());
                stmt.setString(2, p.getNombre());
                stmt.setInt(3, p.getUsuario().getId());
                stmt.setString(4, p.getSrc());
                stmt.setString(5, p.getMp3());
                
                //Inserta la partitura
                comp=stmt.executeUpdate();
                
                //Guarda los instrumentos en un arraylist aparte
                ArrayList<Integer> ins = p.getInstrumentos();
                
                //Coge el nuevo id asignado a la partitura
                p=listarPartitura(p.getNombre() , p.getUsuario());
                
                //Insert de instrumentos de la partitura
                PreparedStatement stmt2 = this.getConexion().prepareStatement("INSERT INTO partitura_instrumentos(partitura, instrumento) VALUES (?,?);");

                for(int i : ins){
                    
                    stmt2.setInt(1, p.getId());
                    stmt2.setInt(2, i);
                    comp+=stmt2.executeUpdate();
                }
                
                if(comp > ins.size())
                    comp = 1;
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 
        
            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
             
            return comp;
        
        }
        
        public int guardarPartitura(Partitura p, Usuario u){
            
            int i = 0;
            
            try{
            
                //Insert en usuario-partitura
                PreparedStatement stmt = this.getConexion().prepareStatement("INSERT INTO usuario_partitura VALUES (?,?)");
            
                stmt.setInt(1, u.getId());
                stmt.setInt(2, p.getId()); 
                
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
         
        public void borrarPartitura(Partitura p){
        
            try{
            
                //Vamos a comprobar que el usuario del login es correcto
                String sql = "DELETE FROM partitura WHERE id=?;";
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
        
        public void editarPartitura(Usuario u, Partitura newPartitura, Partitura oldPartitura){
        
        try{
            
            //Hacemos update con los nuevos datos del usuario sobre el id del usuario
            String sql = "UPDATE partitura SET autor=?, usuario=?, instrumentos=?, src=?, mp3=?"
                    + "WHERE id=?";
            PreparedStatement stmt = this.getConexion().prepareStatement(sql);
            
            stmt.setString(1, newPartitura.getAutor());
            stmt.setInt(2, u.getId());
            //stmt.setString(3, newPartitura.getInstrumentos());
            stmt.setString(4, newPartitura.getSrc());
            stmt.setString(5, newPartitura.getMp3());
            stmt.setInt(6, oldPartitura.getId()); 
            
        }catch (SQLException e) {
            e.printStackTrace();   
	} 
        
        finally {
            //Cerramos conexion
            this.cerrarConexion();
        }
        
    }
        
        public Partitura listarPartitura(String nombre, Usuario u){
            
            Partitura p = new Partitura();
            
            try{
            
            //Hacemos update con los nuevos datos del usuario sobre el id del usuario
            PreparedStatement stmt = this.getConexion().prepareStatement("SELECT * FROM partitura WHERE nombre=?");
            
            stmt.setString(1, nombre);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            
                p.setId(rs.getInt("id"));
                p.setAutor(rs.getString("autor"));
                p.setNombre(rs.getString("nombre"));
                p.setUsuario(u);
                p.setMp3(rs.getString("mp3"));
                p.setSrc(rs.getString("src"));
            
            }
            
            p.setInstrumentos(instrumentos(p));
                
                
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 

            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
            
            return p;
       
        }
        
        public ArrayList<Integer> instrumentos(Partitura p){
            
            ArrayList<Integer> ins = new ArrayList<Integer>();
                    
        try{
        
            //Seleccionamos los instrumentos correspondientes a la partitura
            PreparedStatement stmt = this.getConexion().prepareStatement("select i.nombre FROM instrumentos i, partitura p, partitura_instrumentos pi WHERE i.id=pi.instrumento AND p.id=pi.partitura AND p.id=?;");
            
            stmt.setInt(1, p.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                ins.add(rs.getInt(1));	
            }
            
            
        }catch (SQLException e){
            e.printStackTrace();			
        } 
        
        finally {
            //Cerramos conexion
            this.cerrarConexion();
	}
        
        return ins;
        
    }
        
    public Partitura buscarPartitura(int id){
        
        Partitura p = new Partitura();
        
        Usuario u = new Usuario();
            
            try{
            
            //Hacemos update con los nuevos datos del usuario sobre el id del usuario
            PreparedStatement stmt = this.getConexion().prepareStatement("SELECT * FROM partitura WHERE id=?");
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                u.setId(rs.getInt("usuario"));
            
                p.setId(rs.getInt("id"));
                p.setAutor(rs.getString("autor"));
                p.setNombre(rs.getString("nombre"));
                p.setUsuario(u);
                p.setMp3(rs.getString("mp3"));
                p.setSrc(rs.getString("src"));
            
            }
            
            p.setInstrumentos(instrumentos(p));
                
                
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 

            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
            
            return p;
        
    }
        
    public ObservableList<Partitura> listarPartiturasUsuario(Usuario u){
        
        ObservableList<Partitura> partituras = FXCollections.observableArrayList();
            
            try{
            
            //Hacemos update con los nuevos datos del usuario sobre el id del usuario
            PreparedStatement stmt = this.getConexion().prepareStatement("SELECT p.* FROM partitura p, usuario_partitura up WHERE p.id = up.partitura AND up.usuario=?");
            
            stmt.setInt(1, u.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            
                Partitura p = new Partitura();
                p.setId(rs.getInt("id"));
                p.setAutor(rs.getString("autor"));
                p.setNombre(rs.getString("nombre"));
                p.setSrc(rs.getString("src"));
                p.setMp3(rs.getString("mp3"));
                partituras.add(p);
            
            }
                
                
            
            }catch (SQLException e) {
                e.printStackTrace();   
            } 

            finally {
                //Cerramos conexion
                this.cerrarConexion();
            }
            
            return partituras;
       
        }
          
}
