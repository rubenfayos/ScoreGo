package javaproject.clases;

import java.util.Date;

public class Administrador {
    
    //Variables
    
    private String nombre;
    private String apellidos;
    private int id;
    private String contraseña;
    private Date fechaNacimiento;
    private String correo;
    private String nacionalidad;
    
    //Getters & Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public Administrador(){
        
    }
    
    public Administrador(String nombre, String apellidos, String id, String contraseña, String correo, Date fechaNacimiento, String nacionalidad){
        
    }
    
    //Métodos

    public void listarUsuarios() {
    }

    public void listarPartituras() {
    }

    public void eliminarPartituras() {
    }

    public void listarBandas() {
    }

    public void eliminarBanda() {
    }

    public void eliminarUsuario() {
    }

    public void editarPerfil() {
    }

}
