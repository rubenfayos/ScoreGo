package javaproject.clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javaproject.model.DBUtil;

public class Partitura extends DBUtil{
    
    //Atributos
    
    private int id;
    private String autor;
    private String nombre;
    private Usuario usuario;
    private java.sql.Date fechaSubida;
    private String descripcion;
    private ArrayList<Integer> instrumentos;
    private String src;
    private String mp3;
    
    //Getters & Setters

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Integer> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Integer> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public java.sql.Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(java.sql.Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }    
    
    public Partitura(){
        
    }
    
    public Partitura(String autor, String nombre, String descripcion, int id, Usuario usuario, ArrayList<Integer> instrumentos, String src, String mp3) {
        this.autor = autor;
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.id = id;
        this.usuario = usuario;
        this.instrumentos = instrumentos;
        this.src = src;
        this.mp3 = mp3;
    }

}
