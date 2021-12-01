/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import C.Lista;
import java.io.Serializable;

/**
 *
 * @author jere_
 */
public class Estudiante implements Serializable
{
    private String nombre;
    private int edad;
    private String sexo;
    private int ciclo;
    private Lista<Materia> listaMaterias;

    public Estudiante(String nombre, int edad, String sexo, int ciclo)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.ciclo = ciclo;
    }
    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    
    

    public Lista<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(Lista<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
   
    
    
}
