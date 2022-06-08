package javaproject.clases;

import javaproject.clases.Partitura;
import java.util.ArrayList;
import java.util.Date;

public class Usuario{
    
    //Atributos
    
    private int id;
    private String nombre;
    private String apellidos;
    private String nombreUsuario;
    private String contraseña;
    private String correo;
    private String img;
    private java.sql.Date fechaNacimiento;
    private String nacionalidad;
    private ArrayList<Partitura> partituras;
    private ArrayList<Partitura> partiturasGuardadas;
    private ArrayList<Banda> bandas;
    

    
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public ArrayList<Partitura> getPartituras() {
        return partituras;
    }

    public void setPartituras(ArrayList<Partitura> partituras) {
        this.partituras = partituras;
    }

    public ArrayList<Partitura> getPartiturasGuardadas() {
        return partiturasGuardadas;
    }

    public void setPartiturasGuardadas(ArrayList<Partitura> partiturasGuardadas) {
        this.partiturasGuardadas = partiturasGuardadas;
    }

    public ArrayList<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }
    
    public Usuario(){
        
    }
    //Contructor
    
    public Usuario(String nombre, String apellidos, String nombreUsuario, String contraseña, String correo, java.sql.Date fechaNacimiento, String nacionalidad){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.nombreUsuario=nombreUsuario;
        this.contraseña=contraseña;
        this.correo=correo;
        this.fechaNacimiento=fechaNacimiento;
        this.nacionalidad=nacionalidad;
        this.partituras=new ArrayList<Partitura>();
        this.partiturasGuardadas=new ArrayList<Partitura>();
        this.bandas=new ArrayList<Banda>();
    }
    
    //Métodos

    public void subirPartitura(final Partitura partitura) {
    }

    public void listarPartituras() {
    }

    public void borrarPartitura() {
    }

    public void unirseBanda() {
    }

    public void crearBanda(final Banda banda) {
    }

    public void eliminarBanda() {
    }

    public void editarPartitura() {
    }

    public void editarBanda() {
    }

    public void editarPerfil() {
    }

}
