/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author jere_
 */
public class Persona implements Serializable
{
    private String cedula;
    private String apellidos;
    private String nombres;
    private String direccion;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento; 
    private final long id;

    public Persona(String cedula, String apellidos, String nombres, String direccion, String correo, String telefono, LocalDate fechaNacimiento, long id) {
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
    }
  
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }
  
}
