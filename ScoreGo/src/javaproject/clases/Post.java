
package javaproject.clases;

import javaproject.clases.Banda;
import javaproject.clases.Usuario;

/**
 *
 * @author 1erDAM
 */
public class Post {
    
    private int id;
    private Usuario usuario;
    private Banda banda;
    private String título;
    private String texto;
    
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

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    //Costructor

    public Post(Usuario usuario, Banda banda, String título, String texto) {
        this.usuario = usuario;
        this.banda = banda;
        this.título = título;
        this.texto = texto;
    }

    public Post() {
    }
    
    
}
