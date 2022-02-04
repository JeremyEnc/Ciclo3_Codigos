/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Grafos;

import Controlador.EstructurasDinamicas.Cola;
import Controlador.EstructurasDinamicas.Lista;
import java.util.HashMap;

/**
 *
 * @author jere_
 * @param <E>
 */
public class GrafoEtiquetadoD<E> extends GrafoD
{
    
    protected E etiquetas[];
    protected HashMap<E, Integer> dicVertices;
    
    public GrafoEtiquetadoD(Integer numV) 
    {
        super(numV);
        this.etiquetas = (E[])new Object[numV];
        dicVertices = new HashMap<>(numV);
    }
    
    public boolean existeAristaE(E i, E j)
    {
        try 
        {
            return this.existeAristas(obtenerCodigo(i), obtenerCodigo(j));
        } 
        catch (Exception e) 
        {
            return false;
        }
        
    }
    
    
    public void insertarAristaConPesoE(E i, E j, Double peso)
    {
        try 
        {
            this.insertarAristaConPeso(obtenerCodigo(i), obtenerCodigo(j), peso);
        }
        catch (Exception e) 
        {
            
        }
    }
    
    public void insertarAristaE(E i, E j)
    {
        try 
        {
            this.insertarArista(obtenerCodigo(i), obtenerCodigo(j));
        }
        catch (Exception e) 
        {
            
        }
    }
    
    public Lista<Adyacencia> obtenerListaAdyacenciasE(E i)
    {
        return adyacentes(obtenerCodigo(i));
    }
    
    public void colocarEtiquetaVertice(Integer codigo, E etiqueta)
    {
        etiquetas[codigo] = etiqueta;
        dicVertices.put(etiqueta, codigo);
    }
    
    public Integer obtenerCodigo(E etiqueta)
    {
        try 
        {
            return dicVertices.get(etiqueta);
        }
        catch (Exception e) 
        {
            return -1;
        }
        
    }
    
    public E obtenerEtiqueta(Integer codigo)
    {
        return etiquetas[codigo];
    }

    @Override
    public String toString()
    {
        String grafo = "";
        
        for (int i = 0; i < numVertices(); i++) 
        {
            grafo += "Vertice " + i + " E (" + obtenerEtiqueta(i).toString() + ")";
            Lista<Adyacencia> lista = adyacentes(i);
            for (int j = 0; j < lista.length(); j++)
            {
                Adyacencia aux = lista.getByIndex(j);
                if(!aux.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN)))
                {
                    grafo += "  (Destino " + aux.getDestino() + " E (" + obtenerEtiqueta(aux.getDestino()).toString() + ")"+  "||" + "Peso " + aux.getPeso() + ")";  
                }else
                {
                    grafo += "  (Destino " + aux.getDestino() + " E (" + obtenerEtiqueta(aux.getDestino()).toString() + ")";
                }
            }
            grafo += "\n";
        }
                
        return grafo;
    }

}