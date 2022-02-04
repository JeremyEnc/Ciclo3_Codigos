/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Grafos;

import java.io.Serializable;

/**
 *
 * @author jere_
 * @param <E>
 */
public class GrafoEtiquetadoND <E> extends GrafoEtiquetadoD<E> implements Serializable
{
    
    public GrafoEtiquetadoND(Integer numV)
    {
        super(numV);
    }
    
    
    @Override
    public void insertarAristaE(E i, E j)
    {
        insertarAristaConPesoE(i, j, Double.NaN);
    }
    
    @Override
    public void insertarAristaConPesoE(E i, E j, Double peso)
    {
        try 
        {
            this.insertarAristaConPeso(obtenerCodigo(i), obtenerCodigo(j), peso);
            this.insertarAristaConPeso(obtenerCodigo(j), obtenerCodigo(i), peso);
        }
        catch (Exception e) 
        {
            
        }
    }
}
