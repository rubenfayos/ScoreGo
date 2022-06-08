
package javaproject.clases;

import javaproject.clases.Banda;
import javaproject.clases.Usuario;

/**
 *
 * @author 1erDAM
 */
public class Post {
    
    //atributos
    private int id;
    private Usuario usuario;
    private Banda banda;
    private String titulo;
    private String texto;
    private String fechaPublicacion;
    
    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String título) {
        this.titulo = título;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    //Costructor

    public Post(Usuario usuario, Banda banda, String titulo, String texto) {
        this.usuario = usuario;
        this.banda = banda;
        this.titulo = titulo;
        this.texto = texto;
    }

    public Post() {
    }
    
    
}
