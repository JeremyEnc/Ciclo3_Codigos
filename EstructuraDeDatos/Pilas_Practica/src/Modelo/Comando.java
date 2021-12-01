/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;



/**
 *
 * @author jere_
 */
public class Comando implements Serializable
{
    private String comando;
    private String fechaEjecucion;

    public Comando(String comando, String fechaEjecucion) {
        this.comando = comando;
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(String fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }
    
    
}
