/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 * @author Jorge Duran
 * @author Albert Charry
 */
public class personas {
   
    //variables
    private String nombre;
    private String apellido;
    private String imagen;
    private double cedula;
    private String correo;
    private Profesion profesion;
    private String fechaDeNacimiento;
    private int edad;
    
    
    //constructor
    public personas(String nombre, String apellido, String imagen, long cedula, String correo,Profesion profesion, String fechaDeNacimiento, int edad ){
        
        this.nombre = nombre;
        this.apellido =  apellido;
        this.imagen =  imagen;
        this.cedula =  cedula;
        this.correo =  correo;
        this.profesion =  profesion;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.edad = edad;
        
    }
    
    //getter y setters
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }


    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen =  imagen;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public void  setApellido(String Apellido){
        this.apellido = apellido;
    }
    public double getcedula(){
        return cedula;
    }
    public void setCedula(double cedula){
        this.cedula =  cedula;
    }
    public String getCorreo(){
        return correo;
    }  
    public void setCorreo(String Correo){
        this.correo = correo;
    }
    public Profesion getProfesion(){
        return profesion;
    }
    public void setProfesion(Profesion profesion){
        this.profesion = profesion;
    }
    

    
    
    }
    
    
    

