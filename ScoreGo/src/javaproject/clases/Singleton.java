/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.clases;

/**
 *
 * @author 1erDAM
 */
public class Singleton {
    
    // Static variable reference of single_instance
    // of type Singleton
    private static Singleton single_instance = null;
  
    public Partitura p;
    public Usuario us;
  
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Singleton(){
        us = new Usuario();
    }
  
     public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();
  
        return single_instance;
    }
    
}
