/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author jere_
 */
public class Paciente 
{
    private int edad;
    private String genero;
    private String nombre;
    private String tratamiento;
    private LocalDate fechaDeNacimiento;
    private String clasificacion;

    public Paciente(String genero, String nombre, LocalDate fechaDeNacimiento) 
    {
        this.genero = genero;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    public Paciente(String genero, String nombre, int edad, String tratamiento, String clasificacion, LocalDate fechaDeNacimiento)
    {
        this.genero = genero;
        this.nombre = nombre;
        this.edad = edad;
        this.tratamiento = tratamiento;
        this.clasificacion = clasificacion;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    
    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
}
