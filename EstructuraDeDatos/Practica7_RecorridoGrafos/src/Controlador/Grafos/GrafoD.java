/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Grafos;

import Controlador.EstructurasDinamicas.Cola;
 import Controlador.EstructurasDinamicas.Lista;
import java.io.Serializable;


/**
 *
 * @author jere_
 */
public class GrafoD extends Grafos implements Serializable
{
    private Integer numV;
    protected Integer numA;
    protected Lista<Adyacencia>[] arregloListaAdyancentes;

    public GrafoD(Integer numV) 
    {
        this.numV = numV;
        numA = 0;
        arregloListaAdyancentes = new Lista[this.numV + 1];
        for (int i = 0; i < this.numV; i++)
        {
            arregloListaAdyancentes[i] = new Lista<>();
        }
    }
    
    @Override
    public Integer numVertices()
    {
        return this.numV;
    }

    @Override
    public Integer numAristas()
    {
        return this.numA;
    }

    @Override
    public Boolean existeAristas(Integer i, Integer j) throws Exception
    {
        Boolean existe = false;
        
        if(i <= numV && j <= numV)
        {
            Lista<Adyacencia> listaAdyacencias = arregloListaAdyancentes[i];
            
            for (int k = 0; k < listaAdyacencias.length(); k++)
            {
                Adyacencia aux = listaAdyacencias.getByIndex(k);
                if(aux.getDestino().intValue() == j)
                {
                    existe = true;
                    break;
                }
                
            }
        }else
        {
            System.out.println("El nodo indicado no existe en este grafo");
        }
        
        return existe;
    }

    @Override
    public Double pesArista(Integer i, Integer j) 
    {
        Double peso = Double.NaN;
        
        try 
        {
          if(existeAristas(i, j))
          {
              Lista<Adyacencia> listaAdyacentes = arregloListaAdyancentes[i];
              
              for (int k = 0; k < listaAdyacentes.length(); k++)
              {
                Adyacencia aux = listaAdyacentes.getByIndex(k);
                if(aux.getDestino().intValue() == j)
                {
                    peso = aux.getPeso();
                    break;
                } 
              }
          }
        } catch (Exception e) 
        {
            System.out.println("No se pudo encontrar la arista" + e);
        }
        
        return peso;
    }

    @Override
    public void insertarArista(Integer i, Integer j)
    {
        insertarAristaConPeso(i, j, Double.NaN);
    }

    @Override
    public void insertarAristaConPeso(Integer i, Integer j, Double peso)
    {
       if(i <= numV && j <= numV)
       {
           try
           {
               if(!existeAristas(i, j))
               {
                   numA++;
                   arregloListaAdyancentes[i].add(new Adyacencia(j,peso));
               }
           } catch (Exception ex)
           {
               System.out.println("No se pudo agregar la arista" + ex);
           }
       }
    }

    @Override
    public Lista<Adyacencia> adyacentes(Integer i)
    {
        return arregloListaAdyancentes[i];
    }
 
}
