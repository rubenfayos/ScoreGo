package javaproject.clases;

import javaproject.clases.Usuario;
import javaproject.clases.Partitura;
import java.util.ArrayList;

public class Banda {
    
    //Atributos
    
    private int id;
    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Partitura> partituras;
    private String descripcion;
    private String contraseña;
    private Usuario administrador;
    private String img;
    
    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Partitura> getPartituras() {
        return partituras;
    }

    public void setPartituras(ArrayList<Partitura> partituras) {
        this.partituras = partituras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public Banda(){
        
    }
    
    /**
     * 
     * @param nombre nombre de la banda
     * @param partituras arraylist con las partituras de la banda
     * @param descripcion breve descripción de la banda
     * @param contraseña contraseña de la banda
     * @param administrador un usuario el cual administrará la banda
     * 
     */
    
    //constructor de Banda
    public Banda(int id, String nombre, ArrayList<Partitura> partituras, String descripcion, String contraseña, Usuario administrador, String img){
        this.id=id;
        this.nombre=nombre;
        this.usuarios=new ArrayList<Usuario>();
        usuarios.add(administrador);
        this.partituras=new ArrayList<Partitura>();
        this.descripcion=descripcion;
        this.contraseña=contraseña;
        this.administrador=administrador;
        this.img=img;
    }

}
